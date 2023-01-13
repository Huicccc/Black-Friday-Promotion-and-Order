package org.ecommerce.domain.promotionLog;

public interface PromotionLogRepository {

  public void createPromotionLog(PromotionLogDomain promotionLogDomain);

  public PromotionLogDomain getByOrderIdAndOperation(Long orderId, String operationName);
}
