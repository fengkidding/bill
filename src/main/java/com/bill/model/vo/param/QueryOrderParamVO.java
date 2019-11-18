package com.bill.model.vo.param;

import com.bill.model.vo.common.PageParamVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询订单列表参数
 *
 * @author f
 * @date 2019-03-17
 */
@ApiModel(value = "分页查询订单列表参数")
public class QueryOrderParamVO extends PageParamVO {

    @ApiModelProperty(value = "分类id")
    private Integer classificationId;

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}
