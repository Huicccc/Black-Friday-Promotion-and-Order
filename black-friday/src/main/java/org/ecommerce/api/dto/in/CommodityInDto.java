package org.ecommerce.api.dto.in;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommodityInDto {

  @NotNull(message = "Commodity name cannot be null")
  private String commodity_name;

  @NotNull(message = "Commodity description cannot be null")
  private String description;

  @NotNull(message = "Commodity price cannot be null")
  private Integer price;

  private String image_url;
}
