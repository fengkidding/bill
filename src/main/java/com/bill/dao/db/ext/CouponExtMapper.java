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
}