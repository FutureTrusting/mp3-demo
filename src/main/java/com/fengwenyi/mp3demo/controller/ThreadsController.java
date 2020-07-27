package com.fengwenyi.mp3demo.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : Caixin
 * @date 2019/7/3 16:20
 */
public class ThreadsController {




    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());



        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown

        // 创建一个只包含一个线程的线程池
        pool.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

//        Runnable runnable = () -> {
//            try {
//                String name = Thread.currentThread().getName();
//                System.out.println("Foo " + name);
//                // 休眠一秒
//                TimeUnit.SECONDS.sleep(1);
//                System.out.println("Bar " + name);
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();

    }
}
