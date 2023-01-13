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

@Component
@Slf4j
@RocketMQMessageListener(topic = "revert-stock", consumerGroup = "revert-stock-group")
public class RevertStockSub implements RocketMQListener<MessageExt>{

  @Autowired
  PromotionService promotionService;

  @Autowired
  PromotionLogService promotionLogService;

  @Override
  public void onMessage(MessageExt messageExt) {
    String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
    OrderDomain orderDomain = JSON.parseObject(messageBody, OrderDomain.class);
    log.info("PromotionApp: received revert-stock message");

    // check idempotency
    PromotionLogDomain savedLog = promotionLogService.getPromotionDomain(
        orderDomain.getOrderNumber(),
        OperationName.REVERT_STOCK.toString());
    if (!Objects.isNull(savedLog)) {
      return;
    }

    promotionService.revertStock(orderDomain.getPromotionId());
    promotionLogService.createPromotionLog(createLog(orderDomain));
  }

  private PromotionLogDomain createLog(OrderDomain orderDomain) {
    return PromotionLogDomain.builder()
        .createTime(LocalDateTime.now())
        .promotionId(orderDomain.getPromotionId())
        .orderNumber(orderDomain.getOrderNumber())
        .operationName(OperationName.REVERT_STOCK)
        .userId(orderDomain.getUserId())
        .build();
  }
}
