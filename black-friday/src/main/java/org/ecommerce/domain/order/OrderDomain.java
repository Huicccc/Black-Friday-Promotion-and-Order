package org.ecommerce.domain.order;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDomain {

  private Long orderNumber;

  private OrderStatus orderStatus;

  private String promotionId;

  private String promotionName;

  private String userId;

  private Integer orderAmount;

  private LocalDateTime createTime;

  private LocalDateTime payTime;
}
