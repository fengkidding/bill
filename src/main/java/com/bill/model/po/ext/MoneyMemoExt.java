package com.bill.model.po.ext;

import java.math.BigDecimal;

/**
 * 金额备忘po ext
 *
 * @author f
 * @date 2020-01-16
 */
public class MoneyMemoExt {

    private Integer memberId;

    private BigDecimal amountSum;

    private Byte moneyType;

    private String description;

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