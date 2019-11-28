package com.bill.model.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结果
 *
 * @author f
 * @date 2018-04-23
 */
@ApiModel(value = "结果vo")
public class ResultVO<T> {

    @ApiModelProperty(value = "状态码：200成功，-1系统错误，400校验失败")
    private Integer code;

    @ApiModelProperty(value = "信息")
    private String msg;

    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
