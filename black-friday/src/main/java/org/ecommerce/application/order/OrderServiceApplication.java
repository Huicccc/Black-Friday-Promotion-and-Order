package org.ecommerce.application.order;

import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.order.OrderService;
import org.ecommerce.domain.order.OrderStatus;
import org.ecommerce.domain.promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceApplication {

  @Autowired
  PromotionService promotionService;

  @Autowired
  OrderService orderService;

  @Transactional
  public OrderDomain createOrder(OrderDomain orderDomain) {
    // 1. lock stock: call promotion service
    boolean isLocked = promotionService.lockStock(orderDomain.getPromotionId());
    // 2.1 OUT_OF_STOCK
    if (!isLocked) {
      orderDomain.setOrderStatus(OrderStatus.OUT_OF_STOCK);
      return orderDomain;
    }
    // 2.2 create order time and set order status to "created"
    return orderService.createOrder(orderDomain);
  }
}
