package com.bill.common.util;

import java.math.BigDecimal;

/**
 * 余额工具类
 *
 * @author f
 * @date 2019-03-03
 */
public class RemainingSumUtils {

    /**
     * 将余额转换成元
     *
     * @param remainingSum
     * @return
     */
    public static BigDecimal getYuan(Long remainingSum) {
        BigDecimal bigDecimal = new BigDecimal(remainingSum);
        bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100));
        return bigDecimal;
    }

    /**
     * 将余额转换成分
     *
     * @param remainingSum
     * @return
     */
    public static Long getFen(BigDecimal remainingSum) {
        Long longValue = remainingSum.multiply(BigDecimal.valueOf(100)).longValue();
        return longValue;
    }
}
