package com.bill.model.enums;

/**
 * 结果枚举
 *
 * @author f
 * @date 2018-04-23
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功！"),
    /**
     * 服务器错误
     */
    ERROR(-1, "服务器错误！"),
    /**
     * 没有相关数据
     */
    NO_DATA(1, "数据不存在！"),
    /**
     * token验证失败，请尝试重新登录
     */
    NO_TOKEN(2, "token验证失败，请尝试重新登录！"),
    /**
     * redis key null
     */
    KEY_NONE(3, "key 不能为空");

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
