package org.ecommerce.infrastructure.jooqrepo;

import org.ecommerce.domain.promotionLog.PromotionLogDomain;
import org.ecommerce.domain.promotionLog.PromotionLogRepository;
import org.ecommerce.domain.promotionLog.util.OperationName;
import org.ecommerce.infrastructure.jooq.tables.PromotionLog;
import org.ecommerce.infrastructure.jooq.tables.records.PromotionLogRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JooqPromotionLogDomainRepo implements PromotionLogRepository,
    RecordDomainMapping<PromotionLogRecord, PromotionLogDomain> {

  @Autowired
  DSLContext dslContext;

  private static final PromotionLog PROMOTIONLOG_T = new PromotionLog();

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void createPromotionLog(PromotionLogDomain promotionLogDomain) {
    dslContext.executeInsert(toRecord(promotionLogDomain));
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public PromotionLogDomain getByOrderIdAndOperation(Long orderId, String operationName) {
    return dslContext.selectFrom(PROMOTIONLOG_T).where(
            PROMOTIONLOG_T.ORDER_NUMBER.eq(orderId)
                .and(PROMOTIONLOG_T.OPERATION_NAME.eq(operationName)))
        .fetchOptional(this::toDomain).orElse(null);
  }

  @Override
  public PromotionLogDomain toDomain(PromotionLogRecord promotionLogRecord) {
    return PromotionLogDomain.builder()
        .orderNumber(promotionLogRecord.getOrderNumber())
        .promotionId(promotionLogRecord.getPromotionId())
        .userId(promotionLogRecord.getUserId())
        .operationName(OperationName.valueOf(promotionLogRecord.getOperationName()))
        .createTime(promotionLogRecord.getCreateTime())
        .build();
  }

  @Override
  public PromotionLogRecord toRecord(PromotionLogDomain promotionLogDomain) {
    return new PromotionLogRecord(
        promotionLogDomain.getOrderNumber(),
        promotionLogDomain.getUserId(),
        promotionLogDomain.getPromotionId(),
        promotionLogDomain.getOperationName().toString(),
        promotionLogDomain.getCreateTime()
    );
  }
}
