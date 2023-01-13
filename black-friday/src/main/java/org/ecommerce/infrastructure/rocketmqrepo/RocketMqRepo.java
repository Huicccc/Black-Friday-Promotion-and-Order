package org.ecommerce.infrastructure.rocketmqrepo;

import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.ecommerce.application.order.messageQueue.MqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RocketMqRepo implements MqRepo {

  @Autowired
  RocketMQTemplate rocketMQTemplate;

  @Override
  public void sendMessage(String topic, String body) {
    Message message = new Message(topic, body.getBytes(StandardCharsets.UTF_8));
    try {
      rocketMQTemplate.getProducer().send(message);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sendDelayMessage(String topic, String body, Integer delaySeconds) {
    Message message = new Message(topic, body.getBytes(StandardCharsets.UTF_8));
    message.setDelayTimeLevel(secondsToRocketMqLevel(delaySeconds));
    try {
      rocketMQTemplate.getProducer().send(message);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Integer secondsToRocketMqLevel(Integer seconds) {
    if (seconds <= 1) return 1;
    if (seconds <= 5) return 2;
    if (seconds <= 10) return 3;
    if (seconds <= 30) return 4;
    if (seconds <= 60) return 5;
    else return 6;
  }
}
