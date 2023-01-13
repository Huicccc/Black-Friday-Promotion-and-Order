package org.ecommerce.domain.order;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Transactional(propagation = Propagation.REQUIRED)
  public OrderDomain createOrder(OrderDomain orderDomain) {
    orderDomain.setCreateTime(LocalDateTime.now());
    orderDomain.setOrderStatus(OrderStatus.CREATED);
    orderRepository.createOrder(orderDomain);
    return orderDomain;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public OrderDomain getOrderById(long id) {
    return orderRepository.getOrderById(id);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public OrderDomain updateOrder(OrderDomain orderDomain) {
    orderRepository.updateOrder(orderDomain);
    return orderDomain;
  }

}
