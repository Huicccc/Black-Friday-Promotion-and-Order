package org.ecommerce.infrastructure.rocketmqrepo;

import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test_topic", consumerGroup = "test_group")
public class TestMessageReceiver implements RocketMQListener<MessageExt> {

  @Override
  public void onMessage(MessageExt messageExt) {
    // parse to string, if needed, parse to Object by FastJson2
    String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
    System.out.println(messageBody);
  }
}
