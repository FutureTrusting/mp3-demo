package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 * 懒汉模式
 */
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton2 = null;

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
