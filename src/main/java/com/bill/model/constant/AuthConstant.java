package com.bill.model.constant;

/**
 * 鉴权验证常量
 *
 * @author f
 * @date 2019-11-07
 */
public class AuthConstant {

    /**
     * id
     */
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户名
     */
    public static final String USER_NAME = "userName";
    
    /**
     * token名称
     */
    public static final String COOKIE_NAME = "token";

    /**
     * 一周
     */
    public static final long LONG_SESSION = 604800000;

    /**
     * 一小时
     */
    public static final long SHORT_SESSION = 3600000;

    /**
     * 请求唯一id
     */
    public static final String TRACE_ID = "trace_id";

    private AuthConstant() {
    }
}
