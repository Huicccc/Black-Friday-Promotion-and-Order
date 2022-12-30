package org.ecommerce.domain.promotion.stockStrategy;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oversell")
@Slf4j
public class OverSellStrategy implements PromotionStockOperation{
  @Autowired
  PromotionRepository promotionRepository;

  @Override
  public boolean lockStock(String id) {
    // find promotion by id
    PromotionDomain promotionDomain = promotionRepository.getPromotionByPromotionId(id);
    // available stock -1, lock stock + 1
    if (promotionDomain.getAvailableStock() > 0) {
      log.info("oversell locked");
      promotionDomain.setAvailableStock(promotionDomain.getAvailableStock() - 1);
      promotionDomain.setLockStock(promotionDomain.getLockStock() + 1);
      // update promotion with available_stock and lock_stock
      promotionRepository.updatePromotion(promotionDomain);
      return true;
    }
    log.info("oversell sold out");
    return false;
  }

  @Override
  public boolean deductStock(String id) {
    return false;
  }

  @Override
  public boolean revertStock(String id) {
    return false;
  }
}
