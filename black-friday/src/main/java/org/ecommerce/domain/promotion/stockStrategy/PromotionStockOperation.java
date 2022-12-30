package org.ecommerce.domain.promotion.stockStrategy;

public interface PromotionStockOperation {

  boolean lockStock(String id);

  boolean deductStock(String id);

  boolean revertStock(String id);
}
