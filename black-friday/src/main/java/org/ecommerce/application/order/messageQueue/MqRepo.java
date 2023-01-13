package org.ecommerce.application.order.messageQueue;

public interface MqRepo {
  public void sendMessage(String topic, String body);

  public void sendDelayMessage(String topic, String body, Integer delaySeconds);

}
