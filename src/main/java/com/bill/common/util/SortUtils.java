package com.bill.common.util;

import java.util.PriorityQueue;

/**
 * 排序
 */
public class SortUtils {

    /**
     * top k,堆顶排序
     *
     * @param nums 需要排序的数组
     * @param k    top几
     */
    public static int[] top(int[] nums, int k) {
        //队列默认自然顺序排列，小顶堆，不必重写compare
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
                //如果堆顶元素 < 新数，则删除堆顶，加入新数入堆,保持堆中存储着大值
            } else if (pq.peek() < num) {
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 11, 10, 1, 4, 6, 3, 12, 14, 6, 8, 13, 16, 64, 7, 9, 32, 43, 21};

        int[] result = SortUtils.top(nums, 5);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
