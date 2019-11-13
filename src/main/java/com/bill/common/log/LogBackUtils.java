package com.bill.common.log;

import com.bill.common.log.factory.LogMsgFactory;
import com.bill.common.log.model.ApplicationLog;
import com.bill.common.log.model.LogLevel;
import com.bill.common.log.model.PerformanceLog;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author f
 * @date 2019-10-20
 */
public class LogBackUtils {

    /**
     * 性能日志
     */
    private static final Logger performanceLog = LoggerFactory.getLogger("performance");

    /**
     * 普通日志
     */
    private static final Logger applicationLog = LoggerFactory.getLogger("application");

    /**
     * 打印性能日志
     *
     * @param log
     */
    public static void performance(PerformanceLog log) {
        try {
            log.setClassName(getClassName());
            performanceLog.info(log.toJsonString());
        } catch (Exception var2) {
        }
    }

    /**
     * 错误日志
     *
     * @param msg
     */
    public static void error(String msg) {
        try {
            ApplicationLog log = LogMsgFactory.getApplicationLog(LogLevel.ERROR, msg);
            log.setClassName(getClassName());
            applicationLog.error(log.toJsonString());
        } catch (Exception var2) {
        }
    }

    /**
     * 错误日志
     *
     * @param msg
     * @param obj
     */
    public static void error(String msg, Object... obj) {
        String logMsg = LogBackUtils.defaultFormat(msg, obj);
        LogBackUtils.error(logMsg);
    }

    /**
     * 警告日志
     *
     * @param msg
     */
    public static void warn(String msg) {
        try {
            ApplicationLog log = LogMsgFactory.getApplicationLog(LogLevel.WARN, msg);
            log.setClassName(getClassName());
            applicationLog.warn(log.toJsonString());
        } catch (Exception var2) {
        }
    }

    /**
     * 警告日志
     *
     * @param msg
     * @param obj
     */
    public static void warn(String msg, Object... obj) {
        String logMsg = LogBackUtils.defaultFormat(msg, obj);
        LogBackUtils.warn(logMsg);
    }

    /**
     * info日志
     *
     * @param msg
     */
    public static void info(String msg) {
        try {
            ApplicationLog log = LogMsgFactory.getApplicationLog(LogLevel.INFO, msg);
            log.setClassName(getClassName());
            applicationLog.info(log.toJsonString());
        } catch (Exception var2) {
        }
    }

    /**
     * info日志
     *
     * @param msg
     * @param obj
     */
    public static void info(String msg, Object... obj) {
        String logMsg = LogBackUtils.defaultFormat(msg, obj);
        LogBackUtils.info(logMsg);
    }

    /**
     * debug日志
     *
     * @param msg
     */
    public static void debug(String msg) {
        try {
            ApplicationLog log = LogMsgFactory.getApplicationLog(LogLevel.DEBUG, msg);
            log.setClassName(getClassName());
            applicationLog.debug(log.toJsonString());
        } catch (Exception var2) {
        }
    }

    /**
     * debug日志
     *
     * @param msg
     * @param obj
     */
    public static void debug(String msg, Object... obj) {
        String logMsg = LogBackUtils.defaultFormat(msg, obj);
        LogBackUtils.debug(logMsg);
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

    /**
     * 格式化日志
     *
     * @param format
     * @param argArray
     * @return
     */
    public static String defaultFormat(String format, Object... argArray) {
        String msg = "";
        try {
            if (StringUtils.isEmpty(format)) {
                StringBuilder sb = new StringBuilder();
                if (argArray != null) {
                    Object[] var4 = argArray;
                    int var5 = argArray.length;

                    for (int var6 = 0; var6 < var5; ++var6) {
                        Object arg = var4[var6];
                        if (arg != null) {
                            sb.append(String.format(":%s;", arg.toString()));
                        }
                    }
                }
                msg = sb.toString();
            } else {
                msg = String.format(format, argArray);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }
        return msg;
    }

}
