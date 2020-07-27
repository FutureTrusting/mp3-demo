package com.fengwenyi.mp3demo.leetcode;


import com.fengwenyi.mp3demo.vo.StudentVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode5 {


    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> f3= CompletableFuture.supplyAsync(() -> {
            int t = 4;
            System.out.println(t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t;
        });

        CompletableFuture<Integer> f1= CompletableFuture.supplyAsync(() -> {
            int t = 4;
            System.out.println(t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t;
        });

        CompletableFuture<Integer> f2= CompletableFuture.supplyAsync(() -> {
            int t = 6;
            System.out.println(t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t;
        });

        final List<CompletableFuture<Integer>> futures = Arrays.asList(f1,f2,f3);
        CompletableFuture<List<Integer>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(toList())
                );
        futures.forEach(f -> f.whenComplete((t, ex) -> {
            if (ex != null) {
                result.completeExceptionally(ex);
            }
        }));

        StopWatch info = new StopWatch("开始任务>>>>>>>>>>>>>>");
        info.start();
        result.join().forEach(System.err::println);
        info.stop();
        System.err.println(info.prettyPrint());

    }
}
