package com.bill.controller;

import com.bill.annotation.Metric;
import com.bill.common.util.AuthContextUtils;
import com.bill.model.conversion.CouponConversion;
import com.bill.model.po.auto.Coupon;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.UseCouponVO;
import com.bill.model.vo.view.ListCouponVO;
import com.bill.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 券码api
 *
 * @author f
 * @date 2020-02-18
 */
@Api(tags = {"券码api"})
@RestController
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    /**
     * 根据订单id查询券码列表接口
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "根据订单id查询券码列表接口")
    @Metric(type = "Controller",name="listCoupon")
    @GetMapping(value = "/list-coupon/{orderId}")
    public ResultVO<List<ListCouponVO>> listCoupon(@PathVariable("orderId") Integer orderId) {
        List<Coupon> list = couponService.listCoupon(orderId);
        return super.resultSuccess(CouponConversion.COUPON_CONVERSION.poToVo(list));
    }

    /**
     * 消费使用券码接口
     *
     * @param useCouponVO
     * @return
     */
    @ApiOperation(value = "消费使用券码接口")
    @ApiImplicitParam(name = "memberId", value = "memberId", required = false, dataType = "int", paramType = "header")
    @PostMapping(value = "/use-coupon")
    public ResultVO useCoupon(@RequestBody @Valid UseCouponVO useCouponVO) {
        Integer memberId = AuthContextUtils.getLoginMemberId();
        couponService.useCoupon(useCouponVO);
        return super.resultSuccess();
    }
}
