package com.bill.model.vmo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页vmo
 *
 * @param <T>
 * @author f
 * @date 2019-03-17
 */
@ApiModel(value = "分页vmo")
public class PageVmo<T> {

    @ApiModelProperty(value = "总条数")
    private Long total;

    @ApiModelProperty(value = "分页数据")
    private T data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
