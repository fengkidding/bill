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
    NO_DATA(10001, "数据不存在！"),
    /**
     * token验证失败，请尝试重新登录
     */
    NO_TOKEN(10002, "token验证失败，请尝试重新登录！"),
    /**
     * redis key null
     */
    KEY_NONE(10003, "key 不能为空！"),
    /**
     * 熔断
     */
    FALL_BACK(10004, "服务异常，触发熔断！"),
    /**
     * 用户名或密码不正确！
     */
    MEMBER_NAME_PASSWORD_ERROR(10005, "用户名或密码不正确！"),
    /**
     * 用户未登陆！
     */
    MEMBER_LOGIN_ERROR(10006, "用户未登陆！"),
    /**
     * 扣除用户余额失败
     */
    DEDUCT_REMAININGSUM_ERROR(10007, "扣除用户余额失败！"),
    /**
     * 产品不存在
     */
    PRODUCT_NONE_ERROR(10008, "产品不存在！"),
    /**
     * 订单不存在
     */
    ORDER_NONE_ERROR(10009, "订单不存在！"),
    /**
     * 券码不存在
     */
    COUPON_NONE_ERROR(10010, "券码不存在！"),
    /**
     * 券码不可用
     */
    COUPON_UNUSE_ERROR(10011, "券码不可用！"),
    /**
     * 操作超时
     */
    LOCK_TIME_OUT_ERROR(10012, "操作超时！"),
    /**
     * 适配器参数错误
     */
    ADAPTER_PARAM_ERROR(10013, "适配器参数错误！"),
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
