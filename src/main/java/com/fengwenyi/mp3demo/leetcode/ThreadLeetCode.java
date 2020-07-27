package com.fengwenyi.mp3demo.leetcode;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = 11;
            System.out.println("t1=" + t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }));
//        System.out.println("thenCompose result : " + f.join());


        //thenApply 串行化
        //任务①②③却是串行执行的，②依赖①的执行结果，③依赖②的执行结果
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            return "N";
        }).thenApply(s -> {
            System.out.println("2");
            return s + "c";
        }).thenApply(y -> {
            System.out.println("3");
            return (y + "a").toUpperCase();
        });
//        System.out.println(thenApply.join());

        //thenAccept 接收任务的处理结果，并消费处理，无返回结果
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C";
        }).thenAccept(x -> {
            System.out.println(x);
            System.out.println(x + "AI");
        });
        Void aVoid = completableFuture.join();
        System.out.println(aVoid);

        //thenRun 方法 不会把计算的结果传给 thenRun 方法。只是处理玩任务后，执行 thenAccept 的后续操作
        CompletableFuture<Void> completable2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("333");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C";
        }).thenRun(() -> {
            System.out.println("AI");
        });
        Void aVoid1 = completable2.get();
        System.out.println(aVoid1);

        //thenCombine  其中任务3要等待任务1和任务2都完成后才能开始
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(8);
            return 8;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(2);
            return 2;
        });
        CompletableFuture<Integer> future3 = future1.thenCombine(future2, (t, u) -> t + u);
        System.out.println(future3);


        //thenAcceptBoth (然后接受两者)这个方法很奇怪，必须调用join才能进行下一步处理
        CompletableFuture<Integer> f12 = CompletableFuture.supplyAsync(() -> {
            int t = 1;
            System.out.println("f111=" + t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f111===" + t);
            return t;
        });
        CompletableFuture<Integer> f23 = CompletableFuture.supplyAsync(() -> {
            int t = 2;
            System.out.println("f222=" + t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f222===" + t);
            return t;
        });
        CompletableFuture<Void> voidCompletableFuture = f12.thenAcceptBoth(f23, (t, u) -> {
            System.out.println("f111==" + t + ";f222==" + u + ";");
        });
        Void aVoid2 = voidCompletableFuture.join();
        System.out.println("thenAcceptBoth>>>>>>>>>>>"+aVoid2);



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
        }).exceptionally(x->0);
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


        //runAfterEither (之后运行)两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        //此方法很怪，不调用get什么都不返回，调用哪个不知道哪个快
        CompletableFuture<Integer> f111 = CompletableFuture.supplyAsync(() -> {
            int t = 3;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f111=" + t);
            return t;
        });
        CompletableFuture<Integer> f222 = CompletableFuture.supplyAsync(() -> {
            int t = 13;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f222=" + t);
            return t;
        });

        CompletableFuture<Void> completableFuture1 = f111.runAfterEither(f222, () -> System.out.println("上面有一个已经完成了。"));
        //Integer integer111 = f111.get();
        //Integer integer222 = f222.get();


        //runAfterBoth (在两者之后运行)两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
        //都有同样的问题，不调用get获取不到
        CompletableFuture<Integer> f120 = CompletableFuture.supplyAsync(() -> {
            int t = 3;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f12=" + t);
            return t;
        });

        CompletableFuture<Integer> f230 = CompletableFuture.supplyAsync(() -> {
            int t = 13;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f23=" + t);
            return t;
        });

        f120.runAfterBoth(f230, () -> System.out.println("上面两个任务都执行完成了。"));





    }
}
