package org.ecommerce.domain.promotion;

import java.util.List;

public interface PromotionRepository {

  PromotionDomain createPromotion(PromotionDomain promotionDomain);

  PromotionDomain getPromotionByPromotionId(String promotionId);

  List<PromotionDomain> getPromotionByPromotionStatus(Integer status);

  PromotionDomain updatePromotion(PromotionDomain promotionDomain);

}
