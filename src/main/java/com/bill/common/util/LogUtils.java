package com.bill.common.util;

import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author f
 * @date 2019-10-20
 */
public class LogUtils {

    /**
     * 错误日志
     *
     * @param msg
     */
    public static void error(String msg) {
        LoggerFactory.getLogger(getClassName()).error(msg);
    }

    /**
     * 错误日志
     *
     * @param msg
     * @param obj
     */
    public static void error(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).error(msg, obj);
    }

    /**
     * 警告日志
     *
     * @param msg
     */
    public static void warn(String msg) {
        LoggerFactory.getLogger(getClassName()).error(msg);
    }

    /**
     * 警告日志
     *
     * @param msg
     * @param obj
     */
    public static void warn(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).error(msg, obj);
    }

    /**
     * info日志
     *
     * @param msg
     */
    public static void info(String msg) {
        LoggerFactory.getLogger(getClassName()).info(msg);
    }

    /**
     * info日志
     *
     * @param msg
     * @param obj
     */
    public static void info(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).info(msg, obj);
    }

    /**
     * debug日志
     *
     * @param msg
     */
    public static void debug(String msg) {
        LoggerFactory.getLogger(getClassName()).debug(msg);
    }

    /**
     * debug日志
     *
     * @param msg
     * @param obj
     */
    public static void debug(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).debug(msg, obj);
    }

    /**
     * 获取调用 error,info,debug静态类的类名
     */
    private static String getClassName() {
        return new SecurityManager() {
            public String getClassName() {
                return getClassContext()[3].getName();
            }
        }.getClassName();
    }

}
