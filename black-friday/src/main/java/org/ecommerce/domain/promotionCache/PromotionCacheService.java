package org.ecommerce.domain.promotionCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionCacheService {

  @Autowired
  PromotionCacheRepository promotionCacheRepository;

  public void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain) {
    promotionCacheRepository.setPromotionCacheDomain(promotionCacheDomain);
  }

  public PromotionCacheDomain getPromotionCacheDomainById(String id) {
    return promotionCacheRepository.getPromotionCacheById(id);
  }

}
