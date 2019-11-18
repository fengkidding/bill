package com.bill.model.vo.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 保存商品参数
 *
 * @author f
 * @date 2019-03-10
 */
@ApiModel(value = "保存商品参数")
public class ProductSaveParamVO {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品库存")
    private Integer total;

    @ApiModelProperty(value = "商品价格")
    @NotNull(message = "商品价格不能为null！")
    private BigDecimal price;

    @ApiModelProperty(value = "商品分类id")
    @NotNull(message = "商品分类id不能为null！")
    private Integer classificationId;

    @ApiModelProperty(value = "过期时间",example = "2019-11-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}