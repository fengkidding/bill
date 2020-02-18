package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 消费使用券码vo
 *
 * @author f
 * @date 2020-02-18
 */
@ApiModel(value = "消费使用券码vo")
public class UseCouponVO {

    @ApiModelProperty(value = "券码")
    @NotNull(message = "券码不能为null！")
    private String couponCode;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
