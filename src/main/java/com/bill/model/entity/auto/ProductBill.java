package com.bill.model.entity.auto;

import java.time.LocalDateTime;

public class ProductBill {
    private Integer id;

    private String billUser;

    private Integer productOrderId;

    private Long assetsMoney;

    private String assetsRemark;

    private Long rightsMoney;

    private String rightsRemark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillUser() {
        return billUser;
    }

    public void setBillUser(String billUser) {
        this.billUser = billUser == null ? null : billUser.trim();
    }

    public Integer getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getAssetsMoney() {
        return assetsMoney;
    }

    public void setAssetsMoney(Long assetsMoney) {
        this.assetsMoney = assetsMoney;
    }

    public String getAssetsRemark() {
        return assetsRemark;
    }

    public void setAssetsRemark(String assetsRemark) {
        this.assetsRemark = assetsRemark == null ? null : assetsRemark.trim();
    }

    public Long getRightsMoney() {
        return rightsMoney;
    }

    public void setRightsMoney(Long rightsMoney) {
        this.rightsMoney = rightsMoney;
    }

    public String getRightsRemark() {
        return rightsRemark;
    }

    public void setRightsRemark(String rightsRemark) {
        this.rightsRemark = rightsRemark == null ? null : rightsRemark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }
}