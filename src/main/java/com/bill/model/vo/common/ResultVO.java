package com.bill.model.vo.common;


/**
 * 结果
 *
 * @author f
 * @date 2018-04-23
 */
public class ResultVO<T> {

    private Integer code;

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
