package com.bill.common.util;

import com.bill.common.log.LogBackUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier线程调度，可重复执行
 */
public class CyclicBarrierUtils {

    /**
     * 执行
     */
    public static void exec() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("Action...GO again!");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                LogBackUtils.error("sleep error", e);
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("Executed");
                        cyclicBarrier.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private CyclicBarrierUtils() {
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrierUtils.exec();
    }
}
