package com.bill.service;

import com.bill.model.po.auto.Coupon;

import java.util.List;

/**
 * 券码service
 *
 * @author f
 * @date 2020-02-18
 */
public interface CouponService {

    /**
     * 生成券码
     *
     * @param orderId
     */
    void increment(Integer orderId);

    /**
     * 保存券码
     *
     * @param coupons
     */
    void save(List<Coupon> coupons);
}
