package com.bill.common.log.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 性能日志
 *
 * @author f
 * @date 2019-11-13
 */
public class PerformanceLog extends AbstractLog {

    @JSONField(
            name = "error_code"
    )
    private String errorCode;

    @JSONField(
            name = "business_code"
    )
    private String businessCode;

    @JSONField(
            name = "elapsed_time"
    )
    private long elapsedTime;

    private long startTime = 0L;

    private long timeConsuming = 0L;

    private String className;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.timeConsuming = System.currentTimeMillis() - this.startTime;
        this.elapsedTime = this.timeConsuming;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getBusinessCode() {
        return this.businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public PerformanceLog() {
        super.setLogType(LogConstant.PERFORMANCE_LOG);
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(long timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

