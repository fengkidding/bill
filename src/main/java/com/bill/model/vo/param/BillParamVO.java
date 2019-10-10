package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 入账参数
 *
 * @author f
 * @date 2019-03-10
 */
@ApiModel(value = "入账参数")
public class BillParamVO {

    @ApiModelProperty(value = "用户", example = "f")
    @NotBlank(message = "用户不能为空！")
    private String billUser;

    @ApiModelProperty(value = "金额")
    @NotNull(message = "金额不能为null！")
    private BigDecimal money;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "分类id")
    @NotNull(message = "分类id不能为null！")
    private Integer classificationId;

    public String getBillUser() {
        return billUser;
    }

    public void setBillUser(String billUser) {
        this.billUser = billUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}