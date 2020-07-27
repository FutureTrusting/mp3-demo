package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 * 饿汉模式
 */
public class Singleton1 {

    private Singleton1() {
    }

    private static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }
}
