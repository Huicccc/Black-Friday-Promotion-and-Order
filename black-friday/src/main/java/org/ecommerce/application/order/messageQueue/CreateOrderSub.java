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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "create-order", consumerGroup = "create-order-group")
public class CreateOrderSub implements RocketMQListener<MessageExt> {

  @Autowired
  OrderService orderService;

  @Autowired
  MqRepo mqRepo;

  @Value("${order.delay-time}")
  Integer delaySeconds;

  @Override
  public void onMessage(MessageExt messageExt) {
    // parse to string, if needed, parse to Object by FastJson2
    String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
    OrderDomain orderDomain = JSON.parseObject(messageBody, OrderDomain.class);
    log.info("OderApp: received create order message");

    // 0. idempotent check
    OrderDomain existOrder = orderService.getOrderById(orderDomain.getOrderNumber());
    if (!Objects.isNull(existOrder)) return;
    // 1. create order
    OrderDomain savedOrderDomain = orderService.createOrder(orderDomain);
    // 2. send "lock-stock" message, ps: orderDomain without createTime
    mqRepo.sendMessage("lock-stock", JSON.toJSONString(savedOrderDomain));
    log.info("OrderApp: sent lock-stock message");
    // 3. send "pay-stock" message
    mqRepo.sendDelayMessage("pay-check", JSON.toJSONString(savedOrderDomain), delaySeconds);
    log.info("OrderApp: sent delay pay-check message");
  }
}
