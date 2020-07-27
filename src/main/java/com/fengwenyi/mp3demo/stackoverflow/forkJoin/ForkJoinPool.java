package com.fengwenyi.mp3demo.stackoverflow.forkJoin;

import com.google.gson.Gson;

public class ForkJoinPool {
    public static void main(String[] args) {
        java.util.concurrent.ForkJoinPool joinPool = new java.util.concurrent.ForkJoinPool(10);
        java.util.concurrent.ForkJoinPool forkJoinPool = java.util.concurrent.ForkJoinPool.commonPool();
        System.err.println(new Gson().toJson(joinPool));
        System.err.println(new Gson().toJson(forkJoinPool));
    }
}
