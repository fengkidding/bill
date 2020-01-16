package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 保存金额备忘vo
 *
 * @author f
 * @date 2020-01-16
 */
@ApiModel(value = "保存金额备忘vo")
public class MoneyMemoSaveParamVO {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为null！")
    private Integer memberId;

    @ApiModelProperty(value = "金额")
    @NotNull(message = "金额不能为null")
    private BigDecimal amount;

    @ApiModelProperty(value = "描述")
    @NotNull(message = "描述不能为null")
    private String description;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}