package org.ecommerce.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommodityOutDto {

  private String commodity_id;

  private String commodity_name;

  private String description;

  private Integer price;

  private String image_url;
}
