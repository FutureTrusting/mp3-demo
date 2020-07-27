package com.fengwenyi.mp3demo.leetcode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.fengwenyi.mp3demo.threadpool.ConcurrentCompletableFuture.sleep;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode2 {


    public static void main(String[] args) throws InterruptedException {

        //applyToEither (申请到任何一个)谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
        CompletableFuture<Integer> f11 = CompletableFuture.supplyAsync(() -> {
            int t = 6;
            try {
                TimeUnit.SECONDS.sleep(t);
                System.out.println(1/0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f111=" + t);
            return t;
        });
        CompletableFuture<Integer> f22 = CompletableFuture.supplyAsync(() -> {
            int t = 8;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f222=" + t);
            return t;
        }).exceptionally(x->0);
        CompletableFuture<Integer> result = f11.applyToEither(f22, t -> {
            System.out.println(t);
            return t * 2;
        });
        // 12 or 18 ?
        System.out.println("integer1>>>>>>>>>>>>>>>>>>>>>>>>>>" + result.join());



        //任务1：洗水壶->烧开水
//        CompletableFuture<String> f1 =
//                CompletableFuture.supplyAsync(()->{
//                    System.out.println("T1:洗水壶...");
//                    sleep(1, TimeUnit.SECONDS);
//
//                    System.out.println("T1:烧开水...");
//                    sleep(15, TimeUnit.SECONDS);
//                    return "白开水";
//                });
////任务2：洗茶壶->洗茶杯->拿茶叶
//        CompletableFuture<String> f2 =
//                CompletableFuture.supplyAsync(()->{
//                    System.out.println("T2:洗茶壶...");
//                    sleep(1, TimeUnit.SECONDS);
//
//                    System.out.println("T2:洗茶杯...");
//                    sleep(2, TimeUnit.SECONDS);
//
//                    System.out.println("T2:拿茶叶...");
//                    sleep(1, TimeUnit.SECONDS);
//                    return "龙井";
//                });
////任务3：任务1和任务2完成后执行：泡茶
//        CompletableFuture<String> f3 =
//                f1.thenCombine(f2, (f11, f22)->{
//                    System.out.println("T1:拿到水:" + f11);
//                    System.out.println("T1:泡茶..."+f22);
//                    return "上茶:" + f11;
//                });
////等待任务3执行结果
//        System.out.println(f3.join());
    }
}
