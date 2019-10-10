package com.bill.common.util;

import java.util.concurrent.*;

/**
 * 双重锁线程池
 *
 * @author f
 * @date 2018-04-23
 */
public class ThreadPoolUtils {

    /**
     * 防止实例化
     */
    private ThreadPoolUtils() {

    }

    /**
     * 内部类创建线程池
     *
     * @return
     */
    private static class GetPool {
        static BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        private static final ExecutorService executorService = new ThreadPoolExecutor(2, 20, 30,
                TimeUnit.MICROSECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 获取线程池
     *
     * @return
     */
    public static final ExecutorService getExecutorService() {
        return GetPool.executorService;
    }

}
