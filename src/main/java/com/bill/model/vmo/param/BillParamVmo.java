package com.bill.model.vmo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 入账参数
 *
 * @author f
 * @date 2019-03-10
 */
@ApiModel(value = "入账参数")
public class BillParamVmo {

    @ApiModelProperty(value = "下单用户", example = "f")
    @NotBlank(message = "下单用户不能为空！")
    private String billUser;

    @ApiModelProperty(value = "资产金额")
    @NotNull(message = "资产金额不能为null！")
    private BigDecimal assetsMoney;

    @ApiModelProperty(value = "权益金额")
    @NotNull(message = "权益金额不能为null！")
    private BigDecimal rightsMoney;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为null！")
    private Integer productOrderId;

    public String getBillUser() {
        return billUser;
    }

    public void setBillUser(String billUser) {
        this.billUser = billUser;
    }

    public BigDecimal getAssetsMoney() {
        return assetsMoney;
    }

    public void setAssetsMoney(BigDecimal assetsMoney) {
        this.assetsMoney = assetsMoney;
    }

    public BigDecimal getRightsMoney() {
        return rightsMoney;
    }

    public void setRightsMoney(BigDecimal rightsMoney) {
        this.rightsMoney = rightsMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }
}