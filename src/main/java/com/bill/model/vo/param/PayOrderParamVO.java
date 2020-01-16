package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 支付订单vo
 *
 * @author f
 * @date 2020-01-16
 */
@ApiModel(value = "支付订单vo")
public class PayOrderParamVO {

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为null！")
    private Integer orderId;

    @ApiModelProperty(value = "支付金额")
    @NotNull(message = "支付金额不能为null！")
    private BigDecimal amount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
