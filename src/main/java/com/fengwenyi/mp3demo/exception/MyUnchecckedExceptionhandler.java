package com.fengwenyi.mp3demo.exception;

/**
 * @author : Caixin
 * @date 2019/5/27 10:52
 */
public class MyUnchecckedExceptionhandler  implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获到异常：" + e);
    }
}
