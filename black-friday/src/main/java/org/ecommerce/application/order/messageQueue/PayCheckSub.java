package org.ecommerce.application.order.messageQueue;

import com.alibaba.fastjson.JSON;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.order.OrderService;
import org.ecommerce.domain.order.OrderStatus;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RocketMQMessageListener(topic = "pay-check", consumerGroup = "pay-check-group")
public class PayCheckSub implements RocketMQListener<MessageExt> {

  @Autowired
  OrderService orderService;

  @Autowired
  PromotionStockCacheService promotionStockCacheService;

  @Autowired
  MqRepo mqRepo;

  @Override
  @Transactional
  public void onMessage(MessageExt messageExt) {
    String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
    OrderDomain orderDomain = JSON.parseObject(messageBody, OrderDomain.class);
    log.info("OrderApp: received pay-check message");

    Long orderId = orderDomain.getOrderNumber();
    OrderDomain currentOrder = orderService.getOrderById(orderId);
    if (Objects.isNull(currentOrder)) {
      throw new RuntimeException("Order doesn't exist");
    }

    if (currentOrder.getOrderStatus().equals(OrderStatus.CREATED)) {
      // 1. update order status to overtime
      currentOrder.setOrderStatus(OrderStatus.OVERTIME);
      orderService.updateOrder(currentOrder);
      // 2. cache revert available_stock
      // TODO: microservice
      promotionStockCacheService.revertStock(currentOrder.getPromotionId());
      // 3. database revert stock (available_stock, lock_stock)
      mqRepo.sendMessage("revert-stock", JSON.toJSONString(currentOrder));
      log.info("OrderApp: sent revert-stock message");
    }

  }
}