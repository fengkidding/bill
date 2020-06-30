package com.bill.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁工具类
 *
 * @author f
 * @date 2020-06-19
 */
public class LockUtils {

    private static Object index = 0;

    /**
     * index + 1
     *
     * @param i
     */
    public static void addIndex(int i) {
        new Thread(() -> {
            index = (Integer) index + i;
        }).start();
    }

    public static void main(String[] args) {
        LockUtils.addIndex(1);
        LockUtils.addIndex(1);

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            LockUtils.addIndex(1);
        } finally {
            lock.unlock();
        }
        LockUtils.addIndex(1);
        LockUtils.addIndex(1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}
