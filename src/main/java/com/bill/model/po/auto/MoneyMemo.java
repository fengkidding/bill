package com.bill.model.po.auto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MoneyMemo {
    private Integer moneyMemoId;

    private Integer memberId;

    private BigDecimal amount;

    private Byte moneyType;

    private String description;

    private Byte isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Integer getMoneyMemoId() {
        return moneyMemoId;
    }

    public void setMoneyMemoId(Integer moneyMemoId) {
        this.moneyMemoId = moneyMemoId;
    }

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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}