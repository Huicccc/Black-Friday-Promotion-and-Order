package org.ecommerce.domain.promotion;

import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.domain.promotion.stockStrategy.PromotionStockOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PromotionService {

  @Autowired
  PromotionRepository promotionRepository;

  @Resource(name = "${promotion.stock-strategy}")
  PromotionStockOperation promotionStockOperation;


  public PromotionDomain createPromotion(PromotionDomain promotionDomain) {
    return promotionRepository.createPromotion(promotionDomain);
  }

  public PromotionDomain getPromotionById(String promotionId) {
    return promotionRepository.getPromotionByPromotionId(promotionId);
  }

  public List<PromotionDomain> getPromotionByStatus(Integer status) {
    return promotionRepository.getPromotionByPromotionStatus(status);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public boolean lockStock(String id) {
    return promotionStockOperation.lockStock(id);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public boolean deductStock(String id) {
    return promotionStockOperation.deductStock(id);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public boolean revertStock(String id) {
    return promotionStockOperation.revertStock(id);
  }
}
