package org.ecommerce.domain.promotionLog;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecommerce.domain.promotionLog.util.OperationName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionLogDomain {

  private Long orderNumber;

  private String promotionId;

  private OperationName operationName;

  private String userId;

  private LocalDateTime createTime;
}
