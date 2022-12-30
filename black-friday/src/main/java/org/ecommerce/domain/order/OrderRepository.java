package org.ecommerce.domain.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

  void createOrder(OrderDomain orderDomain);

  OrderDomain getOrderById(long id);

  void updateOrder(OrderDomain orderDomain);
}
