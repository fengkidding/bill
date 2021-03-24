package com.bill.model.vo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 根据订单id查询券码列表view vo
 *
 * @author f
 * @date 2020-02-18
 */
@ApiModel(value = "根据订单id查询券码列表view vo")
public class ListCouponVO {

    @ApiModelProperty(value = "券码id")
    private Integer couponId;

    @ApiModelProperty(value = "券码号")
    private String couponCode;

    @ApiModelProperty(value = "券码状态：0未使用，1已使用，2已过期，3已退款")
    private Byte couponStatus;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "产品id")
    private Integer productId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Byte getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Byte couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
