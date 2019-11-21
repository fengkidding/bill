package com.bill.common.log.model;

/**
 * 日志常量
 *
 * @author f
 * @date 2019-11-07
 */
public class LogConstant {

    /**
     * 请求唯一id
     */
    public static final String TRACE_ID = "trace_id";

    /**
     * 性能日志
     */
    public static final String PERFORMANCE = "performance";

    /**
     * 普通日志
     */
    public static final String APPLICATION = "application";

    /**
     * 性能日志type
     */
    public static final String PERFORMANCE_LOG = "performanceLog";

    /**
     * 普通日志type
     */
    public static final String APPLICATION_LOG = "applicationLog";

    private LogConstant() {

    }
}
