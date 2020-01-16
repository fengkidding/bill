package com.bill.model.vo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 统计金额备忘vo
 *
 * @author f
 * @date 2020-01-16
 */
@ApiModel(value = "统计金额备忘vo")
public class MoneyMemoListVO {

    @ApiModelProperty(value = "用户id")
    private Integer memberId;

    @ApiModelProperty(value = "金额")
    private BigDecimal amountSum;

    @ApiModelProperty(value = "类型：0资产，1负债")
    private Byte moneyType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "记录")
    private String remark;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(BigDecimal amountSum) {
        this.amountSum = amountSum;
    }

    public Byte getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Byte moneyType) {
        this.moneyType = moneyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}