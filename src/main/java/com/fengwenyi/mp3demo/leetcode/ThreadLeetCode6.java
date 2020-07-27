package com.fengwenyi.mp3demo.leetcode;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author : Caixin
 * @date 2019/7/26 16:33
 */
public class ThreadLeetCode6 {


    public static void main(String[] args){
        List<Integer> integerList = new ArrayList<>();
        IntStream.rangeClosed(1,990000)
                .boxed()
                .forEach(x->{
                    integerList.add(x);
                });
        System.out.println("arrayList.size()>>>>>>>"+integerList.size());

//        CopyOnWriteArrayList<Integer> writeArrayList = new CopyOnWriteArrayList<>();
//        List<Integer> arrayList = new ArrayList<>();

        // 定义一个计数器
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        stopWatch.start("任务一");
//        #### 注意，parallelStream无序会改变下标java.lang.ArrayIndexOutOfBoundsException
        List<Integer> reduce = integerList.parallelStream()
                .map(x -> {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(x);
                    return integers;
                })
                .sequential()
                .reduce(new ArrayList<>(), (all, item) -> {
                    all.addAll(item);
                    return all;
                });

        reduce.forEach(System.out::println);
        System.out.println("reduce.size()>>>>>>>"+reduce.size());
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
    }
}
