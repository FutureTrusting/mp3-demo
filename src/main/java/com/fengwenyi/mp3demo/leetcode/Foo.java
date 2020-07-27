package com.fengwenyi.mp3demo.leetcode;

import io.swagger.models.auth.In;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * @author : Caixin
 * @date 2019/7/26 16:34
 */
public class Foo {

    public void one() { print("one"); }
    public void two() { print("two"); }
    public void three() { print("three"); }

    public static void main(String[] args) {

        ThreadPoolUtil.cachedThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("sleep 2 s ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        IntStream.rangeClosed(0,10000).forEach(System.out::println);

        System.out.println("sleep end ");
    }
}
