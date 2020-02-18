package com.bill.model.conversion;

import com.bill.model.po.auto.Coupon;
import com.bill.model.vo.view.ListCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 券码转换类
 *
 * @author f
 * @date 2020-02-18
 */
@Mapper(componentModel = "spring")
public interface CouponConversion {

    CouponConversion COUPON_CONVERSION = Mappers.getMapper(CouponConversion.class);

    /**
     * 券码数据
     *
     * @param coupon
     * @return
     */
    ListCouponVO poToVo(Coupon coupon);

    /**
     * 券码数据
     *
     * @param coupons
     * @return
     */
    List<ListCouponVO> poToVo(List<Coupon> coupons);

}
