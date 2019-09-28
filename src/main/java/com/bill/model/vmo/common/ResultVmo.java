package com.bill.model.vmo.common;


import com.bill.model.enums.ResultEnum;

/**
 * 结果vmo
 *
 * @author f
 * @date 2018-04-23
 */
public class ResultVmo<T> {

    private Integer code = ResultEnum.SUCCESS.getCode();

    private String msg;

    private T data;

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
