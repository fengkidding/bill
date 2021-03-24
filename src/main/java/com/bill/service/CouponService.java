package com.bill.service;

import com.bill.model.po.auto.Coupon;
import com.bill.model.vo.param.UseCouponVO;

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

    /**
     * 根据订单id查询券码列表
     *
     * @param orderId 订单id
     * @return
     */
    List<Coupon> listCoupon(Integer orderId);

    /**
     * 根据code查询券码
     *
     * @param code
     * @return
     */
    Coupon getCouponByCode(String code);

    /**
     * 消费使用券码
     *
     * @param useCouponVO
     */
    void useCoupon(UseCouponVO useCouponVO);
}
