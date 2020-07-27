package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 * 静态内部类实现
 */
public class Singleton4 {
    private Singleton4() {
    }

    private static Singleton4 singleton4 = null;

    static {
        singleton4 = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return singleton4;
    }
}
