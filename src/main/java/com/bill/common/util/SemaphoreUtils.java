package com.bill.common.util;

import com.bill.common.log.LogBackUtils;

import java.util.concurrent.Semaphore;

/**
 * 信号器工具类
 */
public class SemaphoreUtils {

    public static volatile Semaphore semaphore;

    /**
     * 执行逻辑,信号器防止争抢，一次执行一个，执行完毕执行下一个
     *
     * @param x
     */
    public static void exec(int x) {
        for (int i = 0; i < x; i++) {
            new Thread(() -> {
                try {
                    System.out.println("------------- is waiting for a permit!");
                    SemaphoreUtils.getSemaphore().acquire();
                    Thread.sleep(500);
                    System.out.println("executed!");
                } catch (Exception e) {
                    LogBackUtils.error("SemaphoreUtils exec error", e);
                } finally {
                    System.out.println("released a permit!");
                    SemaphoreUtils.getSemaphore().release();
                }
            }).start();
        }
    }

    /**
     * 获取semaphore
     *
     * @return
     */
    private static Semaphore getSemaphore() {
        if (null == semaphore) {
            synchronized (SemaphoreUtils.class) {
                if (null == semaphore) {
                    semaphore = new Semaphore(1);
                }
            }
        }
        return semaphore;
    }

    private SemaphoreUtils() {
    }

    public static void main(String[] args) {
        System.out.println("GO----------------!");
        SemaphoreUtils.exec(20);
    }

}
