package com.bill.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 用户余额参数vmo
 *
 * @author f
 * @date 2019-03-03
 */
@ApiModel(value = "用户余额参数vmo")
public class ConsumerUserSumDto {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为null！")
    private Integer memberId;

    @ApiModelProperty(value = "用户余额")
    @NotNull(message = "用户余额不能为null！")
    private Long remainingSum;

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
}
