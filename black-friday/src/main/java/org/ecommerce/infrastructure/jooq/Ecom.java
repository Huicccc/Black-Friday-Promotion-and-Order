/*
 * This file is generated by jOOQ.
 */
package org.ecommerce.infrastructure.jooq;


import java.util.Arrays;
import java.util.List;
import org.ecommerce.infrastructure.jooq.tables.Commodity;
import org.ecommerce.infrastructure.jooq.tables.Orders;
import org.ecommerce.infrastructure.jooq.tables.Promotion;
import org.ecommerce.infrastructure.jooq.tables.User;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ecom extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ecom</code>
     */
    public static final Ecom ECOM = new Ecom();

    /**
     * The table <code>ecom.commodity</code>.
     */
    public final Commodity COMMODITY = Commodity.COMMODITY;

    /**
     * The table <code>ecom.orders</code>.
     */
    public final Orders ORDERS = Orders.ORDERS;

    /**
     * The table <code>ecom.promotion</code>.
     */
    public final Promotion PROMOTION = Promotion.PROMOTION;

    /**
     * The table <code>ecom.user</code>.
     */
    public final User USER = User.USER;

    /**
     * No further instances allowed
     */
    private Ecom() {
        super("ecom", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Commodity.COMMODITY,
            Orders.ORDERS,
            Promotion.PROMOTION,
            User.USER);
    }
}
