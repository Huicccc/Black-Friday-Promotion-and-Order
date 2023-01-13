package org.ecommerce.infrastructure.jooqrepo;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.domain.promotion.PromotionDomain;
import org.ecommerce.domain.promotion.PromotionRepository;
import org.ecommerce.domain.promotion.stockStrategy.PromotionStockOperation;
import org.ecommerce.infrastructure.jooq.tables.Promotion;
import org.ecommerce.infrastructure.jooq.tables.records.PromotionRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository("optimistic-lock")
public class JoopPromotionDomainRepo implements PromotionRepository, PromotionStockOperation,
    RecordDomainMapping<PromotionRecord, PromotionDomain> {

  @Autowired
  DSLContext dslContext;

  private static final Promotion PROMOTION_T = new Promotion();

  @Override
  public PromotionDomain createPromotion(PromotionDomain promotionDomain) {
    dslContext.executeInsert(toRecord(promotionDomain));
    return promotionDomain;
  }

  @Override
  public PromotionDomain getPromotionByPromotionId(String promotionId) {
    return dslContext.selectFrom(PROMOTION_T).where(PROMOTION_T.PROMOTION_ID.eq(promotionId))
        .fetchOptional(this::toDomain).orElse(null);
  }

  @Override
  public List<PromotionDomain> getPromotionByPromotionStatus(Integer status) {
    return dslContext.selectFrom(PROMOTION_T).where(PROMOTION_T.STATUS.eq(status))
        .fetch(this::toDomain);
  }

  @Override
  public PromotionDomain updatePromotion(PromotionDomain promotionDomain) {
    dslContext.executeUpdate(toRecord(promotionDomain));
    return promotionDomain;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean lockStock(String id) {
    /**
     * update promotion
     * set available_stock = available_stock - 1, lock_stock = lock_stock + 1
     * where id = promotion_id and available_stock > 0
     */
    log.info("optimistic-lock try to lock");
    int isLocked =
        dslContext.update(PROMOTION_T)
            .set(PROMOTION_T.AVAILABLE_STOCK, PROMOTION_T.AVAILABLE_STOCK.subtract(1))
            .set(PROMOTION_T.LOCK_STOCK, PROMOTION_T.LOCK_STOCK.add(1))
            .where(PROMOTION_T.PROMOTION_ID.eq(id)).and(PROMOTION_T.AVAILABLE_STOCK.greaterThan(0L))
            .execute();
    return isLocked == 1;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean deductStock(String id) {
    /**
     * update promotion
     * set lock_stock = lock_stock - 1
     * where id = promotion_id and available_stock > 0
     */
    log.info("optimistic-lock try to deduct");
    // Returns 1 if the record was stored to the database. 0 if storing was not necessary.
    int isLocked =
        dslContext.update(PROMOTION_T)
            .set(PROMOTION_T.LOCK_STOCK, PROMOTION_T.LOCK_STOCK.subtract(1))
            .where(PROMOTION_T.PROMOTION_ID.eq(id)).and(PROMOTION_T.LOCK_STOCK.greaterThan(0L))
            .execute();
    return isLocked == 1;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean revertStock(String id) {
    /**
     * update promotion
     * set available_stock = available_stock + 1, lock_stock = lock_stock - 1
     * where id = promotion_id and available_stock > 0
     */
    log.info("optimistic-lock try to revert");
    int isLocked =
        dslContext.update(PROMOTION_T)
            .set(PROMOTION_T.AVAILABLE_STOCK, PROMOTION_T.AVAILABLE_STOCK.add(1))
            .set(PROMOTION_T.LOCK_STOCK, PROMOTION_T.LOCK_STOCK.subtract(1))
            .where(PROMOTION_T.PROMOTION_ID.eq(id)).and(PROMOTION_T.LOCK_STOCK.greaterThan(0L))
            .execute();
    return isLocked == 1;
  }

  @Override
  public PromotionDomain toDomain(PromotionRecord record) {
    return PromotionDomain.builder()
        .promotionId(record.getPromotionId())
        .promotionName(record.getPromotionName())
        .commodityId(record.getCommodityId())
        .originalPrice(record.getOriginalPrice())
        .promotionalPrice(record.getPromotionPrice())
        .startTime(record.getStartTime())
        .endTime(record.getEndTime())
        .status(record.getStatus())
        .totalStock(record.getTotalStock())
        .availableStock(record.getAvailableStock())
        .lockStock(record.getLockStock())
        .imageUrl(record.getImageUrl())
        .build();
  }

  @Override
  public PromotionRecord toRecord(PromotionDomain domain) {
    return new PromotionRecord(
        domain.getPromotionId(),
        domain.getPromotionName(),
        domain.getCommodityId(),
        domain.getOriginalPrice(),
        domain.getPromotionalPrice(),
        domain.getStartTime(),
        domain.getEndTime(),
        domain.getStatus(),
        domain.getTotalStock(),
        domain.getAvailableStock(),
        domain.getLockStock(),
        domain.getImageUrl()
    );
  }
}
