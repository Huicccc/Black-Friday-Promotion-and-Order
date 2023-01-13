package org.ecommerce.infrastructure.jooqrepo;

import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.order.OrderRepository;
import org.ecommerce.domain.order.OrderStatus;
import org.ecommerce.infrastructure.jooq.tables.Orders;
import org.ecommerce.infrastructure.jooq.tables.records.OrdersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JooqOrderDomainRepo implements OrderRepository {

  @Autowired
  DSLContext dslContext;

  public static final Orders ORDERS_T = new Orders();

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void createOrder(OrderDomain orderDomain) {
    dslContext.executeInsert(toRecord(orderDomain));

  }

  public OrderDomain toDomain(OrdersRecord record) {
    return OrderDomain.builder()
        .orderNumber(record.getOrderNumber())
        .orderStatus(OrderStatus.cachedStatus.get(record.getOrderStatus()))
        .promotionId(record.getPromotionId())
        .promotionName(record.getPromotionName())
        .userId(record.getUserId())
        .orderAmount(record.getOrderAmount())
        .createTime(record.getCreateTime())
        .payTime(record.getPayTime())
        .build();
  }

  public OrdersRecord toRecord(OrderDomain domain) {
    return new OrdersRecord(
        domain.getOrderNumber(),
        domain.getOrderStatus().code,
        domain.getPromotionId(),
        domain.getPromotionName(),
        domain.getUserId(),
        domain.getOrderAmount(),
        domain.getCreateTime(),
        domain.getPayTime()
    );
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public OrderDomain getOrderById(long id) {
    return dslContext.selectFrom(ORDERS_T).where(ORDERS_T.ORDER_NUMBER.eq(id))
        .fetchOptional(this::toDomain).orElse(null);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void updateOrder(OrderDomain orderDomain) {
    dslContext.executeUpdate(toRecord(orderDomain));
  }
}
