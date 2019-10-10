package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商品分类vo
 *
 * @author f
 * @date 2019-10-10
 */
@ApiModel(value = "商品分类vo")
public class ClassificationVO {

    @ApiModelProperty(value = "分类id")
    private Integer id;

    @ApiModelProperty(value = "分类key")
    @NotBlank(message = "分类key不能为空！")
    private String classification;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "分类名称不能为空！")
    private String classificationName;

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
        this.classification = classification;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }
}
