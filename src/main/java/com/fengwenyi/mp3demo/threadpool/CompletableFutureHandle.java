package com.fengwenyi.mp3demo.threadpool;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Caixin
 * @date 2019/7/9 17:13
 */
public class CompletableFutureHandle {

    public static void main(String[] args) throws Exception {
        String name = null;
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) ->{
            System.out.println(s);
            System.out.println(t.getMessage());
            return s;
        });
        System.out.println(future.get()); // Hello, Stranger!
    }
}