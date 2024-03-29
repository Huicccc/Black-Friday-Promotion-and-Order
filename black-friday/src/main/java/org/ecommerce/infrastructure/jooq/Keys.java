/*
 * This file is generated by jOOQ.
 */
package org.ecommerce.infrastructure.jooq;


import org.ecommerce.infrastructure.jooq.tables.Commodity;
import org.ecommerce.infrastructure.jooq.tables.Orders;
import org.ecommerce.infrastructure.jooq.tables.Promotion;
import org.ecommerce.infrastructure.jooq.tables.PromotionLog;
import org.ecommerce.infrastructure.jooq.tables.User;
import org.ecommerce.infrastructure.jooq.tables.records.CommodityRecord;
import org.ecommerce.infrastructure.jooq.tables.records.OrdersRecord;
import org.ecommerce.infrastructure.jooq.tables.records.PromotionLogRecord;
import org.ecommerce.infrastructure.jooq.tables.records.PromotionRecord;
import org.ecommerce.infrastructure.jooq.tables.records.UserRecord;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * blackfriday.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CommodityRecord> KEY_COMMODITY_PRIMARY = Internal.createUniqueKey(Commodity.COMMODITY, DSL.name("KEY_commodity_PRIMARY"), new TableField[] { Commodity.COMMODITY.COMMODITY_ID }, true);
    public static final UniqueKey<OrdersRecord> KEY_ORDERS_PRIMARY = Internal.createUniqueKey(Orders.ORDERS, DSL.name("KEY_orders_PRIMARY"), new TableField[] { Orders.ORDERS.ORDER_NUMBER }, true);
    public static final UniqueKey<PromotionRecord> KEY_PROMOTION_PRIMARY = Internal.createUniqueKey(Promotion.PROMOTION, DSL.name("KEY_promotion_PRIMARY"), new TableField[] { Promotion.PROMOTION.PROMOTION_ID }, true);
    public static final UniqueKey<PromotionLogRecord> KEY_PROMOTION_LOG_PRIMARY = Internal.createUniqueKey(PromotionLog.PROMOTION_LOG, DSL.name("KEY_promotion_log_PRIMARY"), new TableField[] { PromotionLog.PROMOTION_LOG.ORDER_NUMBER, PromotionLog.PROMOTION_LOG.OPERATION_NAME }, true);
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER, DSL.name("KEY_user_PRIMARY"), new TableField[] { User.USER.USER_ID }, true);
    public static final UniqueKey<UserRecord> KEY_USER_USER_NAME = Internal.createUniqueKey(User.USER, DSL.name("KEY_user_user_name"), new TableField[] { User.USER.USER_NAME }, true);
}
