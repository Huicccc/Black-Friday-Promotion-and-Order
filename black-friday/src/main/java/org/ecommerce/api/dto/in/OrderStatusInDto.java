package org.ecommerce.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusInDto {
  private Long orderNumber;
  public Integer existStatus;
  public Integer expectStatus;

}
