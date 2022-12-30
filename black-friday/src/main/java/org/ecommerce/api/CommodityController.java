package org.ecommerce.api;

import java.util.Objects;
import java.util.UUID;
import org.ecommerce.api.dto.in.CommodityInDto;
import org.ecommerce.api.dto.out.CommodityOutDto;
import org.ecommerce.api.util.Response;
import org.ecommerce.api.util.ResponseUtil;
import org.ecommerce.domain.commodity.CommodityDomain;
import org.ecommerce.domain.commodity.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

  @Autowired
  CommodityService commodityService;

  @PostMapping
  public ResponseEntity<Response> createNewCommodity(
      @Validated @RequestBody CommodityInDto commodityInDto) {
    CommodityDomain commodityDomain = createCommodityDomain(commodityInDto);
    if (Objects.nonNull(commodityDomain)) {
      CommodityDomain savedCommodityDomain = commodityService.registerCommodity(commodityDomain);
      CommodityOutDto commodityOutDto = createCommodityOutDto(savedCommodityDomain);
      return ResponseEntity.status(ResponseUtil.SUCCESS)
          .body(Response.builder().result(commodityOutDto).build());
    }
    return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
        .body(Response.builder().msg(ResponseUtil.COMMODITY_INFO_INVALID).build());
  }

  private CommodityOutDto createCommodityOutDto(CommodityDomain savedCommodityDomain) {
    return CommodityOutDto.builder()
        .commodity_id(savedCommodityDomain.getCommodityId())
        .commodity_name(savedCommodityDomain.getCommodityName())
        .description(savedCommodityDomain.getDescription())
        .price(savedCommodityDomain.getPrice())
        .image_url(savedCommodityDomain.getImageUrl())
        .build();
  }

  private CommodityDomain createCommodityDomain(CommodityInDto commodityInDto) {
    return CommodityDomain.builder()
        .commodityId(UUID.randomUUID().toString())
        .commodityName(commodityInDto.getCommodity_name())
        .price(commodityInDto.getPrice())
        .description(commodityInDto.getDescription())
        .imageUrl(commodityInDto.getImage_url())
        .build();
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Response> getCommodityById(@PathVariable("id") String id) {
    CommodityDomain commodityDomain = commodityService.getCommodityById(id);
    if (Objects.isNull(commodityDomain)) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
          .body(Response.builder().msg(String.format(ResponseUtil.COMMODITY_ID_WRONG, id)).build());
    }
    return ResponseEntity.status(ResponseUtil.SUCCESS)
        .body(Response.builder().result(commodityDomain).build());
  }
}
