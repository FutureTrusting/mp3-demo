package com.fengwenyi.mp3demo.java8.expression;

/**
 * @author : Caixin
 * @date 2019/7/11 13:44
 */
public class MeaningOfThis {

    /**
     * 匿名类谜题
     * 下面的代码执行时会有什么样的输出呢， 4 、 5 、 6 还是 42 ？
     */

    public final int value = 4;

    public void doIt() {
        int value = 6;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(value);
            }
        };
        runnable.run();

        Runnable r = new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
