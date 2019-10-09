package com.bill.model.vmo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 保存商品参数
 *
 * @author f
 * @date 2019-03-10
 */
@ApiModel(value = "保存商品参数")
public class ProductSaveParamVmo {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品类别")
    private String productType;

    @ApiModelProperty(value = "商品库存")
    private Integer total;

    @ApiModelProperty(value = "商品价格")
    @NotNull(message = "商品价格不能为null！")
    private BigDecimal price;

    @ApiModelProperty(value = "商品分类id")
    @NotNull(message = "商品分类id不能为null！")
    private Integer classificationId;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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