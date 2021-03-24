package com.bill.common.util;

import com.bill.model.constant.CommonConstant;
import com.bill.model.constant.NumberConstant;
import org.apache.commons.lang.StringUtils;

/**
 * 检查对象工具类
 *
 * @author f
 * @date 2019-02-19
 */
public class CheckBeanUtils {

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

    /**
     * 校验long是否为null，是否为0
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Long value) {
        if (null == value || value.equals(NumberConstant.LONG_ZERO)) {
            return false;
        }
        return true;
    }

    /**
     * 校验string是否为null，是否为空字符串，是否为null字符串
     *
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        if (StringUtils.isEmpty(string) || CommonConstant.STRING_NULL.equals(string)) {
            return false;
        }
        return true;
    }
}
