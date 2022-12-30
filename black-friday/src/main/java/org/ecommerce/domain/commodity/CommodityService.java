package org.ecommerce.domain.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {

  @Autowired
  CommodityDomainRepository commodityDomainRepository;

  public CommodityDomain registerCommodity(CommodityDomain commodityDomain) {
    return commodityDomainRepository.createCommodity(commodityDomain);
  }

  public CommodityDomain getCommodityById(String id) {
    return commodityDomainRepository.getCommodityById(id);
  }
}
