package org.ecommerce.infrastructure;

import java.util.Optional;
import org.ecommerce.domain.commodity.CommodityDomain;
import org.ecommerce.domain.commodity.CommodityDomainRepository;
import org.ecommerce.infrastructure.jooq.tables.Commodity;
import org.ecommerce.infrastructure.jooq.tables.records.CommodityRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommodityDomainRepo implements CommodityDomainRepository {

  public static Commodity COMMODITY_T = new Commodity();

  @Autowired
  DSLContext dslContext;

  @Override
  public CommodityDomain createCommodity(CommodityDomain commodityDomain) {
    dslContext.executeInsert(toRecord(commodityDomain));
    return commodityDomain;
  }

  @Override
  public CommodityDomain getCommodityById(String id) {
    Optional<CommodityDomain> commodityDomainOptional = dslContext.selectFrom(COMMODITY_T)
        .where(COMMODITY_T.COMMODITY_ID.eq(id)).fetchOptional(this::toDomain);
    return commodityDomainOptional.orElse(null);
  }

  private CommodityDomain toDomain(CommodityRecord commodityRecord) {
    return CommodityDomain.builder()
        .commodityId(commodityRecord.getCommodityId())
        .commodityName(commodityRecord.getCommodityName())
        .description(commodityRecord.getDescription())
        .price(commodityRecord.getPrice())
        .imageUrl(commodityRecord.getImageUrl())
        .build();
  }

  private CommodityRecord toRecord(CommodityDomain commodityDomain) {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setCommodityId(commodityDomain.getCommodityId());
    commodityRecord.setCommodityName(commodityDomain.getCommodityName());
    commodityRecord.setDescription(commodityDomain.getDescription());
    commodityRecord.setPrice(commodityDomain.getPrice());
    commodityRecord.setImageUrl(commodityDomain.getImageUrl());
    return commodityRecord;
  }
}
