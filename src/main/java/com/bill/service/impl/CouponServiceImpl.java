package com.bill.service.impl;

import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.RandomNoUtils;
import com.bill.dao.db.ext.CouponExtMapper;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import com.bill.model.po.auto.Coupon;
import com.bill.model.po.auto.ProductOrder;
import com.bill.service.CouponService;
import com.bill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 券码service
 *
 * @author f
 * @date 2020-02-18
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponExtMapper couponExtMapper;

    @Autowired
    private OrderService orderService;

    /**
     * 生成券码
     *
     * @param orderId
     */
    @Override
    public void increment(Integer orderId) {
        if (CheckBeanUtils.checkNotNullZero(orderId)) {
            ProductOrder productOrder = orderService.getProductOrder(orderId);
            if (null == productOrder) {
                throw new ServiceException(ResultEnum.ORDER_NONE_ERROR);
            }

            List<Coupon> coupons = new LinkedList<>();
            for (int i = 0; i < productOrder.getTotal(); i++) {
                Coupon coupon = new Coupon();
                coupon.setCouponCode(String.valueOf(RandomNoUtils.nextId()));
                coupon.setOrderId(orderId);
                coupon.setProductId(productOrder.getProductId());
                coupons.add(coupon);
            }
            this.save(coupons);
        } else {
            throw new ServiceException(ResultEnum.ORDER_NONE_ERROR);
        }
    }

    /**
     * 保存券码
     *
     * @param coupons
     */
    @Override
    public void save(List<Coupon> coupons) {
        couponExtMapper.insertBatch(coupons);
    }

}
