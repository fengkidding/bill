package com.bill.common.log.model;

/**
 * 日志级别
 *
 * @author f
 * @date 2019-11-13
 */
public enum LogLevel {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR");

    private final String text;

    LogLevel(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}

