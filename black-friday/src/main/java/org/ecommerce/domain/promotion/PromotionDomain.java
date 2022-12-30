package org.ecommerce.domain.promotion;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionDomain {

  String promotionId;

  String promotionName;

  String commodityId;

  Integer originalPrice;

  Integer promotionalPrice;

  LocalDateTime startTime;

  LocalDateTime endTime;

  Integer status;

  Long totalStock;

  Long availableStock;

  Long lockStock;

  String imageUrl;
}
