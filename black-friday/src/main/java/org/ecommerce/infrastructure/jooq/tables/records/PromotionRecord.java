/*
 * This file is generated by jOOQ.
 */
package org.ecommerce.infrastructure.jooq.tables.records;


import java.time.LocalDateTime;

import org.ecommerce.infrastructure.jooq.tables.Promotion;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PromotionRecord extends UpdatableRecordImpl<PromotionRecord> implements Record12<String, String, String, Integer, Integer, LocalDateTime, LocalDateTime, Integer, Long, Long, Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ecom.promotion.promotion_id</code>.
     */
    public void setPromotionId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ecom.promotion.promotion_id</code>.
     */
    public String getPromotionId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ecom.promotion.promotion_name</code>.
     */
    public void setPromotionName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ecom.promotion.promotion_name</code>.
     */
    public String getPromotionName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ecom.promotion.commodity_id</code>.
     */
    public void setCommodityId(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ecom.promotion.commodity_id</code>.
     */
    public String getCommodityId() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ecom.promotion.original_price</code>.
     */
    public void setOriginalPrice(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>ecom.promotion.original_price</code>.
     */
    public Integer getOriginalPrice() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>ecom.promotion.promotion_price</code>.
     */
    public void setPromotionPrice(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>ecom.promotion.promotion_price</code>.
     */
    public Integer getPromotionPrice() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>ecom.promotion.start_time</code>.
     */
    public void setStartTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>ecom.promotion.start_time</code>.
     */
    public LocalDateTime getStartTime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>ecom.promotion.end_time</code>.
     */
    public void setEndTime(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>ecom.promotion.end_time</code>.
     */
    public LocalDateTime getEndTime() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>ecom.promotion.status</code>.
     */
    public void setStatus(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>ecom.promotion.status</code>.
     */
    public Integer getStatus() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>ecom.promotion.total_stock</code>.
     */
    public void setTotalStock(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>ecom.promotion.total_stock</code>.
     */
    public Long getTotalStock() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>ecom.promotion.available_stock</code>.
     */
    public void setAvailableStock(Long value) {
        set(9, value);
    }

    /**
     * Getter for <code>ecom.promotion.available_stock</code>.
     */
    public Long getAvailableStock() {
        return (Long) get(9);
    }

    /**
     * Setter for <code>ecom.promotion.lock_stock</code>.
     */
    public void setLockStock(Long value) {
        set(10, value);
    }

    /**
     * Getter for <code>ecom.promotion.lock_stock</code>.
     */
    public Long getLockStock() {
        return (Long) get(10);
    }

    /**
     * Setter for <code>ecom.promotion.image_url</code>.
     */
    public void setImageUrl(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>ecom.promotion.image_url</code>.
     */
    public String getImageUrl() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<String, String, String, Integer, Integer, LocalDateTime, LocalDateTime, Integer, Long, Long, Long, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<String, String, String, Integer, Integer, LocalDateTime, LocalDateTime, Integer, Long, Long, Long, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Promotion.PROMOTION.PROMOTION_ID;
    }

    @Override
    public Field<String> field2() {
        return Promotion.PROMOTION.PROMOTION_NAME;
    }

    @Override
    public Field<String> field3() {
        return Promotion.PROMOTION.COMMODITY_ID;
    }

    @Override
    public Field<Integer> field4() {
        return Promotion.PROMOTION.ORIGINAL_PRICE;
    }

    @Override
    public Field<Integer> field5() {
        return Promotion.PROMOTION.PROMOTION_PRICE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Promotion.PROMOTION.START_TIME;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Promotion.PROMOTION.END_TIME;
    }

    @Override
    public Field<Integer> field8() {
        return Promotion.PROMOTION.STATUS;
    }

    @Override
    public Field<Long> field9() {
        return Promotion.PROMOTION.TOTAL_STOCK;
    }

    @Override
    public Field<Long> field10() {
        return Promotion.PROMOTION.AVAILABLE_STOCK;
    }

    @Override
    public Field<Long> field11() {
        return Promotion.PROMOTION.LOCK_STOCK;
    }

    @Override
    public Field<String> field12() {
        return Promotion.PROMOTION.IMAGE_URL;
    }

    @Override
    public String component1() {
        return getPromotionId();
    }

    @Override
    public String component2() {
        return getPromotionName();
    }

    @Override
    public String component3() {
        return getCommodityId();
    }

    @Override
    public Integer component4() {
        return getOriginalPrice();
    }

    @Override
    public Integer component5() {
        return getPromotionPrice();
    }

    @Override
    public LocalDateTime component6() {
        return getStartTime();
    }

    @Override
    public LocalDateTime component7() {
        return getEndTime();
    }

    @Override
    public Integer component8() {
        return getStatus();
    }

    @Override
    public Long component9() {
        return getTotalStock();
    }

    @Override
    public Long component10() {
        return getAvailableStock();
    }

    @Override
    public Long component11() {
        return getLockStock();
    }

    @Override
    public String component12() {
        return getImageUrl();
    }

    @Override
    public String value1() {
        return getPromotionId();
    }

    @Override
    public String value2() {
        return getPromotionName();
    }

    @Override
    public String value3() {
        return getCommodityId();
    }

    @Override
    public Integer value4() {
        return getOriginalPrice();
    }

    @Override
    public Integer value5() {
        return getPromotionPrice();
    }

    @Override
    public LocalDateTime value6() {
        return getStartTime();
    }

    @Override
    public LocalDateTime value7() {
        return getEndTime();
    }

    @Override
    public Integer value8() {
        return getStatus();
    }

    @Override
    public Long value9() {
        return getTotalStock();
    }

    @Override
    public Long value10() {
        return getAvailableStock();
    }

    @Override
    public Long value11() {
        return getLockStock();
    }

    @Override
    public String value12() {
        return getImageUrl();
    }

    @Override
    public PromotionRecord value1(String value) {
        setPromotionId(value);
        return this;
    }

    @Override
    public PromotionRecord value2(String value) {
        setPromotionName(value);
        return this;
    }

    @Override
    public PromotionRecord value3(String value) {
        setCommodityId(value);
        return this;
    }

    @Override
    public PromotionRecord value4(Integer value) {
        setOriginalPrice(value);
        return this;
    }

    @Override
    public PromotionRecord value5(Integer value) {
        setPromotionPrice(value);
        return this;
    }

    @Override
    public PromotionRecord value6(LocalDateTime value) {
        setStartTime(value);
        return this;
    }

    @Override
    public PromotionRecord value7(LocalDateTime value) {
        setEndTime(value);
        return this;
    }

    @Override
    public PromotionRecord value8(Integer value) {
        setStatus(value);
        return this;
    }

    @Override
    public PromotionRecord value9(Long value) {
        setTotalStock(value);
        return this;
    }

    @Override
    public PromotionRecord value10(Long value) {
        setAvailableStock(value);
        return this;
    }

    @Override
    public PromotionRecord value11(Long value) {
        setLockStock(value);
        return this;
    }

    @Override
    public PromotionRecord value12(String value) {
        setImageUrl(value);
        return this;
    }

    @Override
    public PromotionRecord values(String value1, String value2, String value3, Integer value4, Integer value5, LocalDateTime value6, LocalDateTime value7, Integer value8, Long value9, Long value10, Long value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PromotionRecord
     */
    public PromotionRecord() {
        super(Promotion.PROMOTION);
    }

    /**
     * Create a detached, initialised PromotionRecord
     */
    public PromotionRecord(String promotionId, String promotionName, String commodityId, Integer originalPrice, Integer promotionPrice, LocalDateTime startTime, LocalDateTime endTime, Integer status, Long totalStock, Long availableStock, Long lockStock, String imageUrl) {
        super(Promotion.PROMOTION);

        setPromotionId(promotionId);
        setPromotionName(promotionName);
        setCommodityId(commodityId);
        setOriginalPrice(originalPrice);
        setPromotionPrice(promotionPrice);
        setStartTime(startTime);
        setEndTime(endTime);
        setStatus(status);
        setTotalStock(totalStock);
        setAvailableStock(availableStock);
        setLockStock(lockStock);
        setImageUrl(imageUrl);
    }
}
