package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 * 单例对象 使用volatile + 双重检测机制 -> 禁止指令重排
 */
public class Singleton3 {
    private Singleton3() {
    }

    private volatile static Singleton3 singleton3 = null;

    public static Singleton3 getInstance() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                if (singleton3 == null) {
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}
