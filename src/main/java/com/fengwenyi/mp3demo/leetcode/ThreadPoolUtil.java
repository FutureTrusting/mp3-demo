package com.fengwenyi.mp3demo.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xinLi
 * @Description:服务启动创建线程池
 * @date: 2017/9/27
 */
public class ThreadPoolUtil {


    private ThreadPoolUtil() {

    }
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static int maximumPoolSize = corePoolSize * 16 + 1;
    private static int workQueueSize = maximumPoolSize;
    private static long keepAliveTime = 10L;
    /**
     * corePoolSize 核心线程池大小
     * maximumPoolSize 最大线程池大小----最大线程数一般设为2N+1最好，N是CPU核数
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(5)====用ThreadPoolExecutor自定义线程池，看线程的用途，
     * 如果任务量不大，可以用无界队列，如果任务量非常大，要用有界队列，防止OOM
     * threadFactory 新建线程工厂====定制的线程工厂
     */
    private static final ExecutorService THREA_POOL = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(workQueueSize),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );
    public static ExecutorService cachedThreadPool() {
        return THREA_POOL;
    }

}
