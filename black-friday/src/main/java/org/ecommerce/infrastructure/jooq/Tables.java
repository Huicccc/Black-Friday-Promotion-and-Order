/*
 * This file is generated by jOOQ.
 */
package org.ecommerce.infrastructure.jooq;


import org.ecommerce.infrastructure.jooq.tables.Commodity;
import org.ecommerce.infrastructure.jooq.tables.Orders;
import org.ecommerce.infrastructure.jooq.tables.Promotion;
import org.ecommerce.infrastructure.jooq.tables.PromotionLog;
import org.ecommerce.infrastructure.jooq.tables.User;


/**
 * Convenience access to all tables in blackfriday.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>blackfriday.commodity</code>.
     */
    public static final Commodity COMMODITY = Commodity.COMMODITY;

    /**
     * The table <code>blackfriday.orders</code>.
     */
    public static final Orders ORDERS = Orders.ORDERS;

    /**
     * The table <code>blackfriday.promotion</code>.
     */
    public static final Promotion PROMOTION = Promotion.PROMOTION;

    /**
     * The table <code>blackfriday.promotion_log</code>.
     */
    public static final PromotionLog PROMOTION_LOG = PromotionLog.PROMOTION_LOG;

    /**
     * The table <code>blackfriday.user</code>.
     */
    public static final User USER = User.USER;
}
