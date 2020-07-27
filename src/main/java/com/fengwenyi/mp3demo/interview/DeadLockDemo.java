package com.fengwenyi.mp3demo.interview;

import org.w3c.dom.ls.LSResourceResolver;

import java.util.concurrent.TimeUnit;

/**
 * @author ECHO
 */
public class DeadLockDemo {

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "get resource1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + "wait to get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "get resource2");
                }
            }
        }, "thread-1").start();

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "get resource1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + "wait to get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "get resource2");
                }
            }
        }, "thread-2    ").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + "get resource2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + "wait to get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "get resource1");
                }
            }
        }, "thread-2").start();
    }
}
