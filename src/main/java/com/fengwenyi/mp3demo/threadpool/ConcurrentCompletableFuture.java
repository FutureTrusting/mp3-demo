package com.fengwenyi.mp3demo.threadpool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author : Caixin
 * @date 2019/7/9 15:52
 */
public class ConcurrentCompletableFuture {

      //使用默认线程池
//    static CompletableFuture<Void>
//    runAsync(Runnable runnable)
//    static <U> CompletableFuture<U>
//    supplyAsync(Supplier<U> supplier)
      //可以指定线程池
//    static CompletableFuture<Void>
//    runAsync(Runnable runnable, Executor executor)
//    static <U> CompletableFuture<U>
//    supplyAsync(Supplier<U> supplier, Executor executor)


    /**
     * 看到这里，相信你应该就能理解异步编程最近几年为什么会大火了，因为优化性能是互联网大厂的一个核心需求啊。Java在1.8版本提供了CompletableFuture来支持异步编程，CompletableFuture有可能是你见过的最复杂的工具类了，不过功能也着实让人感到震撼。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 下面的示例代码展示了如何使用applyToEither()方法来描述一个OR汇聚关系。
         */

        CompletableFuture<String> f4 =
                CompletableFuture.supplyAsync(() -> {
                    int t = getRandom(5, 10);
                    System.out.println("f4沉睡了多少秒" + t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f5 =
                CompletableFuture.supplyAsync(() -> {
                    int t = getRandom(5, 10);
                    System.out.println("f5沉睡了多少秒" + t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f6 =
                f4.applyToEither(f5, s -> s);

        System.out.println("聚合取得那个值" + f6.join());


        /**
         * 通过下面的示例代码，你可以看一下thenApply()方法是如何使用的。首先通过supplyAsync()启动一个异步流程，之后是两个串行操作，整体看起来还是挺简单的。不过，虽然这是一个异步流程，但任务①②③却是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
         */
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> "Hello World")      //①
                        .thenApply(s -> {
                            System.out.println(s);
                            return s + " QQ";
                        })  //②
                        .thenApply(b -> {
                            System.out.println(b);
                            return b.toUpperCase();
                        });//③
        String join = f0.join();
        System.out.println(join);


        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
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
                f1.thenCombine(f2, (aVoid, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
//等待任务3执行结果
        System.out.println(f3.join());




    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

}
