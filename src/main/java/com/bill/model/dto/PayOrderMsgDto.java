package com.bill.model.dto;

/**
 * 支付订单mq
 *
 * @author f
 * @date 2019-03-03
 */
public class PayOrderMsgDto {

    /**
     * 收钱用户id
     */
    private Integer memberId;

    /**
     * 用户余额
     */
    private Long remainingSum;

    /**
     * 订单id
     */
    private Integer orderId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Long getRemainingSum() {
        return remainingSum;
    }

    public void setRemainingSum(Long remainingSum) {
        this.remainingSum = remainingSum;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
