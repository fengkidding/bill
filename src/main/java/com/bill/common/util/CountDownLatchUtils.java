package com.bill.common.util;

import com.bill.common.log.LogBackUtils;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch线程调度
 */
public class CountDownLatchUtils {

    /**
     * 执行
     */
    public static void exec() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("First batch executed!");
                countDownLatch.countDown();
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("Second batch executed!");
                } catch (InterruptedException e) {
                    LogBackUtils.error("CountDownLatchUtils error", e);
                }
            }).start();
        }

        System.out.println("Wait for first batch finish");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            LogBackUtils.error("sleep error", e);
        }
        countDownLatch.countDown();
    }

    private CountDownLatchUtils() {
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchUtils.exec();
    }
}
