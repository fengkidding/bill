package com.bill.model.entity.auto;

import java.time.LocalDateTime;

public class Classification {
    private Integer id;

    private String classification;

    private String classificationName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName == null ? null : classificationName.trim();
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
}