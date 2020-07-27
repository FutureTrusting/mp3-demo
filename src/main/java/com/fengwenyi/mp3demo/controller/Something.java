package com.fengwenyi.mp3demo.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : Caixin
 * @date 2019/7/3 10:30
 */
public class Something {

    public static class Test {
        AtomicLong count =new AtomicLong(0);
        void add10K() {
            int idx = 0;
            while(idx++ < 10000) {
                count.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) {

        ConcurrentSkipListMap skipListMap = new ConcurrentSkipListMap<String,String>();
        skipListMap.put("1", "2");
        System.out.println(skipListMap);

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();



    }
}
