package com.bill.config;

/**
 * 配置常量（不变的）
 *
 * @author f
 * @date 2019-11-13
 */
public final class FinalEnvConfig {

    private static String appName;

    private static String env;

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        FinalEnvConfig.appName = appName;
    }

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        FinalEnvConfig.env = env;
    }
}

