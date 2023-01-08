package org.ecommerce.domain.promotionStockCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionStockCacheService {

  @Autowired
  PromotionStockCacheRepository promotionStockCacheRepository;

  public boolean lockStock(String id) {
    return promotionStockCacheRepository.lockStockCache(id);
  }

  public Long getStock(String id) {
    return promotionStockCacheRepository.getStockCache(id);
  }

  public void setStock(String promotionId, Long availableStock) {
    promotionStockCacheRepository.setStockCache(promotionId, availableStock);
  }

  public boolean revertStock(String id) {
    return promotionStockCacheRepository.revertStockCache(id);
  }
}
