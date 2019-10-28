package com.bill.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户余额参数vmo
 *
 * @author f
 * @date 2019-03-03
 */
@ApiModel(value = "用户余额参数vmo")
public class ConsumerUserSumBO {

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空！")
    private String userName;

    @ApiModelProperty(value = "用户余额")
    @NotNull(message = "用户余额不能为null！")
    private Long remainingSum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRemainingSum() {
        return remainingSum;
    }

    public void setRemainingSum(Long remainingSum) {
        this.remainingSum = remainingSum;
    }
}
