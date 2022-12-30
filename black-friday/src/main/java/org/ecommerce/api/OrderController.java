package org.ecommerce.api;

import java.util.Objects;
import org.ecommerce.api.dto.in.OrderInDto;
import org.ecommerce.api.dto.out.OrderOutDto;
import org.ecommerce.api.util.ResponseUtil;
import org.ecommerce.api.util.SnowFlake;
import org.ecommerce.application.order.OrderServiceApplication;
import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.order.OrderService;
import org.ecommerce.domain.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  SnowFlake snowFlake;

  @Autowired
  OrderServiceApplication orderServiceApplication;

  @Autowired
  OrderService orderService;

  @PostMapping
  public ResponseEntity createOrder(@RequestBody OrderInDto orderInDto) {
    // create OrderDomain: call orderServiceApplication
    OrderDomain createdOrder = orderServiceApplication.createOrder(toDomain(orderInDto));

    if (createdOrder.getOrderStatus().equals(OrderStatus.CREATED)) {
      return ResponseEntity.status(ResponseUtil.SUCCESS).body(toOrderOutDto(createdOrder));
    }

    return ResponseEntity.status(ResponseUtil.INTERNAL_ERROR).body(toOrderOutDto(createdOrder));
  }

  private OrderDomain toDomain(OrderInDto orderInDto) {
    return OrderDomain.builder()
        .orderNumber(snowFlake.nextId())
        .userId(orderInDto.getUserId())
        .promotionId(orderInDto.getPromotionId())
        .promotionName(orderInDto.getPromotionName())
        .orderAmount(orderInDto.getOrderAmount())
        .orderStatus(OrderStatus.READY)
        .build();
  }

  private OrderOutDto toOrderOutDto(OrderDomain orderDomain) {
    return OrderOutDto.builder()
        .orderNumber(orderDomain.getOrderNumber())
        .userId(orderDomain.getUserId())
        .promotionId(orderDomain.getPromotionId())
        .promotionName(orderDomain.getPromotionName())
        .orderAmount(orderDomain.getOrderAmount())
        .orderStatus(orderDomain.getOrderStatus().code)
        .createTime(orderDomain.getCreateTime())
        .payTime(orderDomain.getPayTime())
        .build();
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<OrderOutDto> getOrderById(@PathVariable("id") long id) {
    OrderDomain orderDomain = orderService.getOrderById(id);
    if (Objects.isNull(orderDomain)) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(null);
    }
    return ResponseEntity.status(ResponseUtil.SUCCESS).body(toOrderOutDto(orderDomain));
  }
}
