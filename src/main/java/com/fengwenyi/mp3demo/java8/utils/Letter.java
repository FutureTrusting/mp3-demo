package com.fengwenyi.mp3demo.java8.utils;

import com.fengwenyi.mp3demo.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author : Caixin
 * @date 2019/7/12 10:55
 */
@RestController
public class Letter {

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }

    @GetMapping("/app")
    public void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(10);
        // 创建Result对象r
        Result r = new Result();
        r.setName("Qq");
        List<Integer> integers = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> stringFuture = executor.submit(new Task(r));
            String s = stringFuture.get();

        }
        System.out.println(integers);
    }

    public static void main(String[] args) {

        Map<String, String> stringHashMap = new HashMap<>();
        String s = stringHashMap.get("111");
        System.out.println(s);
    }




    public class Task implements Callable<String>{
        private Result result;
        Task(Result result) {
           this.result=result;
        }

        @Override
        public String call() throws Exception {
            System.out.println("call()方法被自动调用,干活！！！" + Thread.currentThread().getName());
            //一个模拟耗时的操作
            for (int i = 999999; i > 0; i--) {
                ;
            }
            return"call()方法被自动调用，任务的结果是：" + result + "    " + Thread.currentThread().getName();
        }
    }















}
