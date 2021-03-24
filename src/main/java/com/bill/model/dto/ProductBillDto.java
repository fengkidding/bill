package com.bill.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 账单dto
 *
 * @author f
 * @date 2020-06-30
 */
public class ProductBillDto {

    @ExcelProperty(value = "分类id", index = 0)
    private Integer classificationId;

    @ExcelProperty(value = "资产简介", index = 1)
    private String assetsRemark;

    @ExcelProperty(value = "权益简介", index = 2)
    private String rightsRemark;

    public String getAssetsRemark() {
        return assetsRemark;
    }

    public void setAssetsRemark(String assetsRemark) {
        this.assetsRemark = assetsRemark == null ? null : assetsRemark.trim();
    }

    public String getRightsRemark() {
        return rightsRemark;
    }

    public void setRightsRemark(String rightsRemark) {
        this.rightsRemark = rightsRemark == null ? null : rightsRemark.trim();
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}