package com.bill.common.log.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 日志抽象
 *
 * @author f
 * @date 2019-11-13
 */
public abstract class AbstractLog implements Cloneable {

    @JSONField(
            name = "log_version"
    )
    private String logVersion;

    @JSONField(
            name = "log_time"
    )
    private String logTime;

    @JSONField(
            name = "log_type"
    )
    private String logType;

    @JSONField(
            name = "app_name"
    )
    private String appName;

    @JSONField(
            name = "trace_id"
    )
    private String traceId;

    @JSONField(
            name = "docker_name"
    )
    private String dockerName;

    @JSONField(
            name = "server_ip"
    )
    private String serverIp;

    @JSONField(
            name = "method_name"
    )
    private String methodName;

    private String env;

    @JSONField(
            serialize = false
    )
    private ValueFilter filter;

    @JSONField(
            name = "client_ip"
    )
    private String clientIp;

    public ValueFilter getFilter() {
        return this.filter;
    }

    public void setFilter(ValueFilter filter) {
        this.filter = filter;
    }

    public String getLogVersion() {
        return this.logVersion;
    }

    public void setLogVersion(String logVersion) {
        this.logVersion = logVersion;
    }

    public String getLogTime() {
        return this.logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDockerName() {
        return this.dockerName;
    }

    public void setDockerName(String dockerName) {
        this.dockerName = dockerName;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getLogType() {
        return this.logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getEnv() {
        return this.env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String toJsonString() {
        return JSON.toJSONString(this, this.filter, new SerializerFeature[]{SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNullStringAsEmpty});
    }
}
