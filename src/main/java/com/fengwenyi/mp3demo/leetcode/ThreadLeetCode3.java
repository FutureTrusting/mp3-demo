package com.fengwenyi.mp3demo.leetcode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.thread.ThreadUtil.sleep;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode3 {


    public static void main(String[] args) throws InterruptedException {

        //任务1：洗水壶->烧开水
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                    return "白开水";
                });
//任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });

//任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2,(f11, f22)->{
                    System.out.println("T1:拿到水:" + f11);
                    System.out.println("T1:泡茶..."+f22);
                    return "上茶:" + f11;
                });
//等待任务3执行结果
        System.out.println(f3.join());
    }
}
