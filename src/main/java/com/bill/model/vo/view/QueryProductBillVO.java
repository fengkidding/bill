package com.bill.model.vo.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 查询商品账单
 *
 * @author f
 * @date 2019-03-17
 */
@ApiModel(value = "查询商品账单")
public class QueryProductBillVO {

    @ApiModelProperty(value = "分类id")
    private Integer classificationId;

    @ApiModelProperty(value = "资产简介")
    private String assetsRemark;

    @ApiModelProperty(value = "权益简介")
    private String rightsRemark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public String getAssetsRemark() {
        return assetsRemark;
    }

    public void setAssetsRemark(String assetsRemark) {
        this.assetsRemark = assetsRemark;
    }

    public String getRightsRemark() {
        return rightsRemark;
    }

    public void setRightsRemark(String rightsRemark) {
        this.rightsRemark = rightsRemark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}