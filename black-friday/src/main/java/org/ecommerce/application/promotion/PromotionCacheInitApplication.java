package org.ecommerce.application.promotion;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.application.promotion.util.PromotionCacheDomainMapping;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionService;
import org.ecommerce.domain.promotionCache.PromotionCacheService;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PromotionCacheInitApplication implements ApplicationRunner {

  @Autowired
  PromotionService promotionService;

  @Autowired
  PromotionCacheService promotionCacheService;

  @Autowired
  PromotionStockCacheService promotionStockCacheService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("---- Init active promotion stock into cache ----");
    List<PromotionDomain> activePromotions = promotionService.getPromotionByStatus(1);
    activePromotions.stream().forEach(promotionDomain -> {
      // cache active promotion(cache) domain
      promotionCacheService.setPromotionCacheDomain(
          PromotionCacheDomainMapping.domainToCache(promotionDomain));
      // cache active promotion stock info
      promotionStockCacheService.setStock(promotionDomain.getPromotionId(),
          promotionDomain.getAvailableStock());
    });
    // cache active promotion stock info

  }
}
