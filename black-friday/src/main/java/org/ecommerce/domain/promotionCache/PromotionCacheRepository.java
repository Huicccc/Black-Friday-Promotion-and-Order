package org.ecommerce.domain.promotionCache;

import org.springframework.stereotype.Repository;

@Repository
public interface PromotionCacheRepository {

  void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain);

  PromotionCacheDomain getPromotionCacheById(String id);
}
