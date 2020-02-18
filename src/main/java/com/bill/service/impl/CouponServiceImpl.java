package com.bill.service.impl;

import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.RandomNoUtils;
import com.bill.dao.db.ext.CouponExtMapper;
import com.bill.model.constant.CommonConstant;
import com.bill.model.constant.NumberConstant;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import com.bill.model.po.auto.Coupon;
import com.bill.model.po.auto.ProductOrder;
import com.bill.model.vo.param.UseCouponVO;
import com.bill.service.CouponService;
import com.bill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    /**
     * 根据订单id查询券码列表
     *
     * @param orderId 订单id
     * @return
     */
    @Override
    public List<Coupon> listCoupon(Integer orderId) {
        List<Coupon> list = couponExtMapper.listCoupon(orderId);
        if (CollectionUtils.isEmpty(list)) {
            return CommonConstant.EMPTY_LIST;
        } else {
            return list;
        }
    }

    /**
     * 根据code查询券码
     *
     * @param code
     * @return
     */
    @Override
    public Coupon getCouponByCode(String code) {
        List<Coupon> list = couponExtMapper.listCouponByCode(code);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 消费使用券码
     *
     * @param useCouponVO
     */
    @Override
    public void useCoupon(UseCouponVO useCouponVO) {
        Coupon coupon = this.getCouponByCode(useCouponVO.getCouponCode());
        if (null != coupon) {
            if (NumberConstant.BYTE_ZERO.equals(coupon.getCouponStatus())) {
                Coupon couponUpdate = new Coupon();
                couponUpdate.setCouponId(coupon.getCouponId());
                couponUpdate.setCouponStatus(NumberConstant.BYTE_ONE);
                couponExtMapper.updateByPrimaryKeySelective(couponUpdate);
            } else {
                throw new ServiceException(ResultEnum.COUPON_UNUSE_ERROR);
            }
        } else {
            throw new ServiceException(ResultEnum.COUPON_NONE_ERROR);
        }
    }

}
