package com.bill.dao.db.ext;

import com.bill.dao.db.auto.CouponMapper;
import com.bill.model.po.auto.Coupon;

import java.util.List;

/**
 * 券码扩展mapper
 *
 * @author f
 * @date 2020-02-18
 */
public interface CouponExtMapper extends CouponMapper {

    /**
     * 批量保存
     *
     * @param coupons
     */
    void insertBatch(List<Coupon> coupons);

    /**
     * 根据订单id查询券码列表
     *
     * @param orderId
     * @return
     */
    List<Coupon> listCoupon(Integer orderId);

    /**
     * 根据code查询券码
     *
     * @param code
     * @return
     */
    List<Coupon> listCouponByCode(String code);
}