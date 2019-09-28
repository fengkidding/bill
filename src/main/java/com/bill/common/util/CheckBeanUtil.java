package com.bill.common.util;

/**
 * 检查对象工具类
 *
 * @author f
 * @date 2019-02-19
 */
public class CheckBeanUtil {

    /**
     * 校验integer是否为null，是否为0
     *
     * @param integer
     * @return
     */
    public static boolean checkNotNullZero(Integer integer) {
        if (null == integer || integer.equals(0)) {
            return false;
        }
        return true;
    }
}
