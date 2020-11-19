package com.bill.manager.download.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 *
 * @author f
 * @date 2018-05-23
 */
public class CalculationUtils {

    /**
     * 两个double相加算法
     *
     * @param d
     * @param d1
     * @return
     */
    public static Double addDouble(Double d, Double d1) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        return bigDecimal.add(bigDecimal1).doubleValue();
    }

    /**
     * 两个double相加乘
     *
     * @param d
     * @param d1
     * @return
     */
    public static Double mulDouble(Double d, Double d1) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        return bigDecimal.multiply(bigDecimal1).doubleValue();
    }

    /**
     * 返回list2中不在list1里的元素
     *
     * @param list1 列表1
     * @param list2 列表2
     * @return 列表2中数据，去除了列表1含有的数据
     */
    public static List<Integer> listDifferent(List<Integer> list1, List<Integer> list2) {
        if (null == list1 || list1.isEmpty()) {
            return list2;
        }
        if (null == list2 || list2.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(10);
        list1.forEach(item -> map.put(item, 1));
        list2.forEach(item -> {
            if (null == map.get(item)) {
                result.add(item);
            }
        });
        return result;
    }

    /**
     * 判断数字不为null同时大于0
     *
     * @param id
     * @return
     */
    public static boolean isNotEmpty(Integer id) {
        boolean result = false;
        if (null != id && id > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 获取连个参数比值
     *
     * @param molecule
     * @param denominator
     * @return
     */
    public static BigDecimal getPercentage(int molecule, int denominator) {
        BigDecimal bigMolecule = BigDecimal.valueOf(molecule);
        BigDecimal bigDenominator = BigDecimal.valueOf(denominator);
        return bigMolecule.divide(bigDenominator, 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
    }
}
