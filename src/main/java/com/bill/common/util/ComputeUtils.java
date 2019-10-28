package com.bill.common.util;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 计算工具类
 *
 * @author f
 * @date 2019-03-03
 */
public class ComputeUtils {

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

    /**
     * 快速排序，分而治之
     *
     * @param sortList
     * @return
     */
    public static List<Map<Integer, Integer>> fastSort(List<Map<Integer, Integer>> sortList) {
        //基线条件
        if (CollectionUtils.isEmpty(sortList) || sortList.size() < 2) {
            return sortList;
        } else {
            //选择基准值
            Map<Integer, Integer> pivot = sortList.get(0);
            Integer reference = 0;
            for (Map.Entry<Integer, Integer> map : pivot.entrySet()) {
                reference = map.getValue();
            }

            //小于基准值的集合
            List<Map<Integer, Integer>> less = new LinkedList<>();
            //大于基准值的集合
            List<Map<Integer, Integer>> greater = new LinkedList<>();

            //判断大小
            for (int i = 1; i < sortList.size(); i++) {
                Map<Integer, Integer> item = sortList.get(i);
                for (Integer value : item.values()) {
                    if (value.intValue() <= reference.intValue()) {
                        less.add(item);
                    } else if (value.intValue() > reference.intValue()) {
                        greater.add(item);
                    }
                }
            }
            List<Map<Integer, Integer>> result = new LinkedList<>();
            //递归排序
            result.addAll(ComputeUtils.fastSort(less));
            result.add(pivot);
            result.addAll(ComputeUtils.fastSort(greater));
            return result;
        }
    }

    /**
     * 快速排序，分而治之倒序
     *
     * @param sortList
     * @return
     */
    public static List<Map<Integer, Integer>> fastSortDesc(List<Map<Integer, Integer>> sortList) {
        //基线条件
        if (CollectionUtils.isEmpty(sortList) || sortList.size() < 2) {
            return sortList;
        } else {
            //选择基准值
            Map<Integer, Integer> pivot = sortList.get(0);
            Integer reference = 0;
            for (Map.Entry<Integer, Integer> map : pivot.entrySet()) {
                reference = map.getValue();
            }

            //小于基准值的集合
            List<Map<Integer, Integer>> less = new LinkedList<>();
            //大于基准值的集合
            List<Map<Integer, Integer>> greater = new LinkedList<>();

            //判断大小
            for (int i = 1; i < sortList.size(); i++) {
                Map<Integer, Integer> item = sortList.get(i);
                for (Integer value : item.values()) {
                    if (value.intValue() >= reference.intValue()) {
                        less.add(item);
                    } else if (value.intValue() < reference.intValue()) {
                        greater.add(item);
                    }
                }
            }
            List<Map<Integer, Integer>> result = new LinkedList<>();
            //递归排序
            result.addAll(ComputeUtils.fastSortDesc(less));
            result.add(pivot);
            result.addAll(ComputeUtils.fastSortDesc(greater));
            return result;
        }
    }

    /**
     * 快速排序test
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Map<Integer, Integer>> sortList = new LinkedList<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 4);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(4, 7);
        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(2, 1);
        Map<Integer, Integer> map4 = new HashMap<>();
        map4.put(1, 93);
        Map<Integer, Integer> map5 = new HashMap<>();
        map5.put(67, 2);
        Map<Integer, Integer> map6 = new HashMap<>();
        map6.put(12, 42);
        sortList.add(map1);
        sortList.add(map2);
        sortList.add(map3);
        sortList.add(map4);
        sortList.add(map5);
        sortList.add(map6);
        System.out.println(sortList);
        sortList = ComputeUtils.fastSortDesc(sortList);
        System.out.println(sortList);
    }
}
