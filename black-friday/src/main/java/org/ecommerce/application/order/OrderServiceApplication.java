package org.ecommerce.application.order;

import com.alibaba.fastjson.JSON;
import java.time.LocalDateTime;
import java.util.Objects;
import org.ecommerce.application.order.messageQueue.MqRepo;
import org.ecommerce.application.promotion.PromotionServiceApplication;
import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.order.OrderService;
import org.ecommerce.domain.order.OrderStatus;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionService;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceApplication {

  @Autowired
  PromotionService promotionService;

  @Autowired
  OrderService orderService;

  @Autowired
  PromotionServiceApplication promotionServiceApplication;

  @Autowired
  PromotionStockCacheService promotionStockCacheService;

  @Autowired
  MqRepo mqRepo;

  @Transactional
  public OrderDomain createOrder(OrderDomain orderDomain) {
    // 1. check promotion existing
    PromotionDomain promotionDomain = promotionService.getPromotionById(
        orderDomain.getPromotionId());
    if (Objects.isNull(promotionDomain)) {
      orderDomain.setOrderStatus(OrderStatus.ITEM_ERROR);
      return orderDomain;
    }
    // 2. lock stock: call promotion service
    boolean isLocked = promotionService.lockStock(orderDomain.getPromotionId());
    // 3.1 OUT_OF_STOCK
    if (!isLocked) {
      orderDomain.setOrderStatus(OrderStatus.OUT_OF_STOCK);
      return orderDomain;
    }
    // 3.2 create order time and set order status to "created"
    return orderService.createOrder(orderDomain);
  }

  public OrderDomain createBuyNowOrder(OrderDomain orderDomain) {
    // 1. check promotion existing
    PromotionDomain promotionDomain = promotionServiceApplication.getPromotionById(
        orderDomain.getPromotionId());
    if (Objects.isNull(promotionDomain)) {
      orderDomain.setOrderStatus(OrderStatus.ITEM_ERROR);
      return orderDomain;
    }
    // 2. lock stock in cache
    boolean isLocked = promotionStockCacheService.lockStock(orderDomain.getPromotionId());
    // 3.1 OUT_OF_STOCK
    if (!isLocked) {
      orderDomain.setOrderStatus(OrderStatus.OUT_OF_STOCK);
      return orderDomain;
    }
    // 3.2 Asynchronize send message to MessageQueue to handle create order logic
    mqRepo.sendMessage("create-order", JSON.toJSONString(orderDomain));
    return orderDomain;
  }

  @Transactional
  public OrderDomain payBuyNowOrder(Long orderNumber) {
    OrderDomain orderDomain = orderService.getOrderById(orderNumber);
    if (Objects.isNull(orderDomain)) {
      return null;
    }
    // check order status is created
    if (orderDomain.getOrderStatus().equals(OrderStatus.CREATED)) {
      boolean payed = pay();
      if (payed) {
        // 1. update order status, date
        orderDomain.setOrderStatus(OrderStatus.PAYED);
        orderDomain.setPayTime(LocalDateTime.now());
        orderService.updateOrder(orderDomain);
        // 2. mq send "deduct-stock message"
        mqRepo.sendMessage("deduct-stock", JSON.toJSONString(orderDomain));
        return orderDomain;
      }
      return orderDomain;
    }
    return orderDomain;
  }

  public boolean pay() {
    return true;
  }
}
