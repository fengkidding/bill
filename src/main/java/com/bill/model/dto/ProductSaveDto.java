package com.bill.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 保存商品dto
 *
 * @author f
 * @date 2019-03-10
 */
public class ProductSaveDto {

    @ExcelProperty(value = "用户id", index = 0)
    private Integer memberId;

    @ExcelProperty(value = "商品名称", index = 1)
    private String productName;

    @ExcelProperty(value = "商品库存", index = 2)
    private Integer total;

    @ExcelProperty(value = "商品价格", index = 3)
    private BigDecimal price;

    @ExcelProperty(value = "商品分类id", index = 4)
    private Integer classificationId;

    @ExcelProperty(value = "过期时间", index = 5)
    private Date expiredTime;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
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