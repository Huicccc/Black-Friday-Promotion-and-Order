package org.ecommerce.application.promotion.util;

import java.util.UUID;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotionCache.PromotionCacheDomain;

public class PromotionCacheDomainMapping {
  public static PromotionCacheDomain domainToCache(PromotionDomain promotionDomain) {
    return PromotionCacheDomain.builder()
        .promotionId(promotionDomain.getPromotionId())
        .promotionName(promotionDomain.getPromotionName())
        .commodityId(promotionDomain.getCommodityId())
        .startTime(promotionDomain.getStartTime())
        .endTime(promotionDomain.getEndTime())
        .originalPrice(promotionDomain.getOriginalPrice())
        .promotionalPrice(promotionDomain.getPromotionalPrice())
        .totalStock(promotionDomain.getTotalStock())
        .availableStock(promotionDomain.getAvailableStock())
        .lockStock(promotionDomain.getLockStock())
        .imageUrl(promotionDomain.getImageUrl())
        .status(promotionDomain.getStatus())
        .build();
  }

  public static PromotionDomain cacheToDomain(PromotionCacheDomain promotionCacheDomain) {
    return PromotionDomain.builder()
        .promotionId(UUID.randomUUID().toString())
        .promotionName(promotionCacheDomain.getPromotionName())
        .commodityId(promotionCacheDomain.getCommodityId())
        .startTime(promotionCacheDomain.getStartTime())
        .endTime(promotionCacheDomain.getEndTime())
        .originalPrice(promotionCacheDomain.getOriginalPrice())
        .promotionalPrice(promotionCacheDomain.getPromotionalPrice())
        .totalStock(promotionCacheDomain.getTotalStock())
        .availableStock(promotionCacheDomain.getAvailableStock())
        .lockStock(promotionCacheDomain.getLockStock())
        .imageUrl(promotionCacheDomain.getImageUrl())
        .status(promotionCacheDomain.getStatus())
        .build();
  }
}
