package org.ecommerce.application.promotion;

import java.util.Objects;
import org.ecommerce.application.promotion.util.PromotionCacheDomainMapping;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionService;
import org.ecommerce.domain.promotionCache.PromotionCacheDomain;
import org.ecommerce.domain.promotionCache.PromotionCacheService;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceApplication {

  @Autowired
  PromotionService promotionService;

  @Autowired
  PromotionCacheService promotionCacheService;

  @Autowired
  PromotionStockCacheService promotionStockCacheService;


  public PromotionDomain getPromotionById(String id) {
    // try to hit cache
    PromotionCacheDomain cached = promotionCacheService.getPromotionCacheDomainById(id);
    // no hit: access db, set cache
    if (Objects.isNull(cached)) {
      PromotionDomain promotionDomain = promotionService.getPromotionById(id);
      if (Objects.isNull(promotionDomain)) {
        return null;
      }
      promotionCacheService.setPromotionCacheDomain(
          PromotionCacheDomainMapping.domainToCache(promotionDomain));
      return promotionDomain;
    }
    // hit: available_stock may not accurate, cover available_stock by promotionStockCache
    Long availableStock = promotionStockCacheService.getStock(id);
    cached.setAvailableStock(availableStock);
    return PromotionCacheDomainMapping.cacheToDomain(cached);
  }

}
