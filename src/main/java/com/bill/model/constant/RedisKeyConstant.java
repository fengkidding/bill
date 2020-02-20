package com.bill.model.constant;

/**
 * redis key常量
 *
 * @author f
 * @date 2019-04-21
 */
public class RedisKeyConstant {

    /**
     * 产品列表
     */
    public static final String PRODUCT_LIST_KEY = "productListKey_";

    /**
     * 产品列表,时间长key
     */
    public static final String PRODUCT_LIST_LONG_KEY = "productListLongKey_";

    /**
     * 产品列表锁
     */
    public static final String PRODUCT_LIST_LOCK_KEY = "productListLockKey_";

    /**
     * 使用券码锁
     */
    public static final String USE_COUPON_LOCK_KEY = "useCouponLockKey_";

    private RedisKeyConstant() {

    }
}
