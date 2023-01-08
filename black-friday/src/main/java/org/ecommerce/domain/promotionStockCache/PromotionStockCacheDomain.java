package org.ecommerce.domain.promotionStockCache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionStockCacheDomain {
  private String promotionId;
  private String availableStock;

  public static final String PREFIX = "PROMOTION_";

  public static final String SUFFIX = "_STOCK";

  public static String createPromotionStockKey(String promotionId) {
    return PREFIX + promotionId + SUFFIX;

  }

}
