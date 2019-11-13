package com.bill.common.log.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 普通日志
 *
 * @author f
 * @date 2019-11-13
 */
public class ApplicationLog extends AbstractLog {

    private String level;

    private long threadId;

    private String context;

    private String exception;

    @JSONField(
            name = "log_message"
    )
    private String logMessage;

    @JSONField(
            name = "stack_message"
    )
    private String stackMessage;

    private String className;

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getLogMessage() {
        return this.logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getStackMessage() {
        return this.stackMessage;
    }

    public void setStackMessage(String stackMessage) {
        this.stackMessage = stackMessage;
    }

    public long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public ApplicationLog() {
        super.setLogType("applicationLog");
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
