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
    SUCCESS(200, "成功！"),
    /**
     * 服务器错误
     */
    ERROR(-1, "服务器错误！"),
    /**
     * 没有相关数据
     */
    NO_DATA(401, "数据不存在！"),
    /**
     * token验证失败，请尝试重新登录
     */
    NO_TOKEN(402, "token验证失败，请尝试重新登录！"),
    /**
     * redis key null
     */
    KEY_NONE(403, "key 不能为空！"),
    /**
     * 熔断
     */
    FALL_BACK(404, "服务异常，触发熔断！"),
    /**
     * 用户名或密码不正确！
     */
    MEMBER_NAME_PASSWORD_ERROR(405, "用户名或密码不正确！"),
    /**
     * 用户未登陆！
     */
    MEMBER_LOGIN_ERROR(406, "用户未登陆！"),
    /**
     * 扣除用户余额失败
     */
    DEDUCT_REMAININGSUM_ERROR(407, "扣除用户余额失败！"),
    /**
     * 产品不存在
     */
    PRODUCT_NONE_ERROR(408, "产品不存在！"),
    /**
     * 订单不存在
     */
    ORDER_NONE_ERROR(409, "订单不存在！"),
    /**
     * 校验失败
     */
    VALIDATE_ERROR(400, "校验失败！");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
