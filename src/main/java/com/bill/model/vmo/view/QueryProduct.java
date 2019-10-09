package com.bill.model.vmo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 查询商品信息vmo
 *
 * @author f
 * @date 2019-03-17
 */
@ApiModel(value = "查询商品信息vmo")
public class QueryProduct {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "库存")
    private Integer total;

    @ApiModelProperty(value = "已售数量")
    private Integer totalSold;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品分类id")
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

    public Integer getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Integer totalSold) {
        this.totalSold = totalSold;
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