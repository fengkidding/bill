package com.bill.model.vmo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 统计账单
 *
 * @author f
 * @date 2019-03-23
 */
@ApiModel(value = "统计账单")
public class StatisticsBillVmo {

    @ApiModelProperty(value = "订单类型")
    private String type;

    @ApiModelProperty(value = "账单总金额")
    private BigDecimal billMoney;

    @ApiModelProperty(value = "资产金额")
    private BigDecimal assetsMoney;

    @ApiModelProperty(value = "权益金额")
    private BigDecimal rightsMoney;

    public BigDecimal getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(BigDecimal billMoney) {
        this.billMoney = billMoney;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
