package org.ecommerce.domain.commodity;

public interface CommodityDomainRepository {

  CommodityDomain createCommodity(CommodityDomain commodityDomain);

  CommodityDomain getCommodityById(String id);
}
