package org.ecommerce.domain.promotion.stockStrategy;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sync")
@Slf4j
public class SynchronizedStrategy implements PromotionStockOperation {

  @Autowired
  PromotionRepository promotionRepository;

  @Override
  public boolean lockStock(String id) {
    synchronized (this) {
      // find promotion by id
      PromotionDomain promotionDomain = promotionRepository.getPromotionByPromotionId(id);
      // available stock -1, lock stock + 1
      if (promotionDomain.getAvailableStock() > 0) {
        log.info("sync locked");
        promotionDomain.setAvailableStock(promotionDomain.getAvailableStock() - 1);
        promotionDomain.setLockStock(promotionDomain.getLockStock() + 1);
        // update promotion with available_stock and lock_stock
        promotionRepository.updatePromotion(promotionDomain);
        return true;
      }
      log.info("sync sold out");
      return false;
    }
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
