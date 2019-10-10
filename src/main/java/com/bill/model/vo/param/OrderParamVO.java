package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 用户下单参数
 *
 * @author f
 * @date 2019-03-10
 */
@ApiModel(value = "用户下单参数")
public class OrderParamVO {

    @ApiModelProperty(value = "下单用户", example = "f")
    @NotBlank(message = "下单用户不能为空！")
    private String orderUser;

    @ApiModelProperty(value = "产品id")
    @NotNull(message = "产品id不能为null！")
    private Integer productId;

    @ApiModelProperty(value = "购买数量", example = "1")
    @NotNull(message = "购买数量不能为null！")
    private Integer total;

    @ApiModelProperty(value = "备注")
    private String remark;

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}