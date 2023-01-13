package org.ecommerce.domain.promotionLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionLogService {

  @Autowired
  PromotionLogRepository promotionLogRepository;

  @Transactional(propagation = Propagation.REQUIRED)
  public PromotionLogDomain createPromotionLog(PromotionLogDomain domain) {
    promotionLogRepository.createPromotionLog(domain);
    return domain;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public PromotionLogDomain getPromotionDomain(Long orderId, String operationName) {
    return promotionLogRepository.getByOrderIdAndOperation(orderId, operationName);
  }
}
