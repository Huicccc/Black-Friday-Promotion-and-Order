package org.ecommerce.api.dto.out;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderOutDto {

  private Long orderNumber;

  private Integer orderStatus;

  private String promotionId;

  private String promotionName;

  private String userId;

  private Integer orderAmount;

  private LocalDateTime createTime;

  private LocalDateTime payTime;
}
