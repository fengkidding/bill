package com.bill.model.po.auto;

import java.time.LocalDateTime;

public class ProductBill {
    private Integer id;

    private String billUser;

    private Long assetsMoney;

    private String assetsRemark;

    private Long rightsMoney;

    private String rightsRemark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer classificationId;

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

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}