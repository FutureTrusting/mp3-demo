package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 */
public class SynchronizedTest {
    public void test1() {
        // synchronized 锁的是当前对象
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
            }
        }
    }

    public synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
    }


    public void testStatic1() {
        // synchronized 锁的是当前对象
        synchronized (SynchronizedTest.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
            }
        }
    }

    public static synchronized void testStatic2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        final SynchronizedTest myt2 = new SynchronizedTest();
        final SynchronizedTest myt3 = new SynchronizedTest();
        //new 两个对象是互不影响的，判断synchronized 锁定的是new 实例化的对象
//        new Thread(() -> myt2.test1(), "test1").start();
//        new Thread(() -> myt2.test2(), "test2").start();
//
//        new Thread(() -> myt3.test1(), "test11").start();
//        new Thread(() -> myt3.test2(), "test22").start();

//  因为静态方法是所有对象实例共用的，所以对应着synchronized修饰的静态方法的锁也是唯一的，所以抽象出来个类锁
//  修饰类所有的new 实例化出来都需要获取锁资源
        final SynchronizedTest myt4 = new SynchronizedTest();
        final SynchronizedTest myt5 = new SynchronizedTest();
        new Thread(() -> myt4.testStatic1(), "testStatic1").start();
        new Thread(() -> myt5.testStatic1(), "testStatic11").start();
        new Thread(() -> SynchronizedTest.testStatic2(), "testStatic2").start();

    }
}
