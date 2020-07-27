package com.fengwenyi.mp3demo.controller;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : Caixin
 * @date 2019/7/8 14:12
 */
public class Test {


    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    private static long count = 0;

    class SafeCalc {


        long value = 0L;
        long get() {
            return value;
        }
        synchronized void addOne() {
            value += 1;
        }
    }

    private void add10K() {

        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long calc = calc();
        System.out.println(calc);
    }

    public static long calc() throws InterruptedException {

        final Test test = new Test();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }
}
