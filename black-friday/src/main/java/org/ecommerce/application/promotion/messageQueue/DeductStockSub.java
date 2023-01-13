package org.ecommerce.application.promotion.messageQueue;

import com.alibaba.fastjson.JSON;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.ecommerce.domain.order.OrderDomain;
import org.ecommerce.domain.promotion.PromotionService;
import org.ecommerce.domain.promotionLog.PromotionLogDomain;
import org.ecommerce.domain.promotionLog.PromotionLogService;
import org.ecommerce.domain.promotionLog.util.OperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RocketMQMessageListener(topic = "deduct-stock", consumerGroup = "deduct-stock-group")
public class DeductStockSub implements RocketMQListener<MessageExt> {

  @Autowired
  PromotionService promotionService;

  @Autowired
  PromotionLogService promotionLogService;

  @Override
  @Transactional
  public void onMessage(MessageExt messageExt) {
    String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
    OrderDomain orderDomain = JSON.parseObject(messageBody, OrderDomain.class);
    log.info("received deduct-stock message");

    // check idempotency
    PromotionLogDomain savedLog = promotionLogService.getPromotionDomain(
        orderDomain.getOrderNumber(),
        OperationName.DEDUCT_STOCK.toString());
    if (!Objects.isNull(savedLog)) {
      return;
    }

    promotionService.deductStock(orderDomain.getPromotionId());
    promotionLogService.createPromotionLog(createLog(orderDomain));
  }

  private PromotionLogDomain createLog(OrderDomain orderDomain) {
    return PromotionLogDomain.builder()
        .createTime(LocalDateTime.now())
        .promotionId(orderDomain.getPromotionId())
        .orderNumber(orderDomain.getOrderNumber())
        .operationName(OperationName.DEDUCT_STOCK)
        .userId(orderDomain.getUserId())
        .build();
  }
}
