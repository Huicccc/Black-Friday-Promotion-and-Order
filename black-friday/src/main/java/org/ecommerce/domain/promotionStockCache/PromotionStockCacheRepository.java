package org.ecommerce.domain.promotionStockCache;

public interface PromotionStockCacheRepository {

  boolean lockStockCache(String id);

  boolean revertStockCache(String id);

  public Long getStockCache(String id);

  void setStockCache(String id, Long stock);

}
