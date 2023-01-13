package org.ecommerce.infrastructure.rocketmqrepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TestMessageReceiverTest {

  @Autowired
  RocketMqRepo rocketMqRepo;

  @Test
  void onMessage() {
    rocketMqRepo.sendMessage("test_topic", "Hello, RocketMQ!");
  }
}