package org.ecommerce.api.dto.in;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionInDto {

  String promotionName;

  String commodityId;

  Integer originalPrice;

  Integer promotionalPrice;

  LocalDateTime startTime;

  LocalDateTime endTime;

  // 0 pre, 1 on going, 2 out date
  Integer status;

  Long totalStock;

  Long availableStock;

  Long lockStock;

  String imageUrl;
}
