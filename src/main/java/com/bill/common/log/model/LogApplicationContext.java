package com.bill.common.log.model;

/**
 * 当前程序信息
 *
 * @author f
 * @date 2019-11-13
 */
public class LogApplicationContext {

    private String url;

    private String params;

    private String method;

    public LogApplicationContext() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
