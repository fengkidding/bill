package com.bill.model.constant;

/**
 * redis缓存时间
 *
 * @author f
 * @date 2019-04-21
 */
public class RedisCatchConstant {

    /**
     * 产品列表缓存时间
     */
    public static final long PRODUCT_LIST_CATCH = 1200;

    /**
     * 产品列表缓存时间,长key
     */
    public static final long PRODUCT_LIST_LONG_CATCH = 1500;

    /**
     * 产品列表锁过期时间
     */
    public static final long PRODUCT_LIST_LOCK_CATCH = 2000;

    /**
     * 产品列表锁睡眠时间
     */
    public static final long PRODUCT_LIST_LOCK_SLEEP = 300;

    /**
     * 使用券码锁过期时间
     */
    public static final long USE_COUPON_LOCK_CATCH = 2000;

    /**
     * 使用券码锁睡眠时间
     */
    public static final long USE_COUPON_LOCK_SLEEP = 300;

    private RedisCatchConstant() {

    }
}
