package com.fengwenyi.mp3demo.leetcode;


import com.google.common.base.Stopwatch;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.thread.ThreadUtil.sleep;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode4 {


    public static void main(String[] args) throws InterruptedException {
        //runAfterBoth (在两者之后运行)两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
        //都有同样的问题，不调用get获取不到
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        stopWatch.start("任务一");

        CompletableFuture<Integer> f120 = CompletableFuture.supplyAsync(() -> {
            int t = 6;
            System.out.println(t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f12=" + t);
            return t;
        });

        CompletableFuture<Integer> f230 = CompletableFuture.supplyAsync(() -> {
            int t = 4;
            System.out.println(t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f23=" + t);
            return t;
        });
        CompletableFuture<Void> voidCompletableFuture = f120.runAfterBoth(f230, () -> System.out.println("上面两个任务都执行完成了。"));
        voidCompletableFuture.join();
        stopWatch.stop();
        String result = stopWatch.prettyPrint();
        System.err.println(result);

    }
}
