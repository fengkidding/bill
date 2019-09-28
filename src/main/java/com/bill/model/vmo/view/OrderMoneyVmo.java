package com.bill.model.vmo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 订单统计
 *
 * @author f
 * @date 2019-07-02
 */
@ApiModel(value = "订单统计金额")
public class OrderMoneyVmo {

    @ApiModelProperty(value = "订单类型")
    private String type;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderMoney;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }
}
