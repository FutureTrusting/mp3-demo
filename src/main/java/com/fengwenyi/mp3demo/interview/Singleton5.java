package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 * 枚举模式：最安全
 */
public class Singleton5 {

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private Singleton5 singleton5;

        Singleton() {
            singleton5 = new Singleton5();
        }

            public Singleton5 getInstance() {
            return singleton5;
        }

    }
}
