package com.fengwenyi.mp3demo.java8.collectionstream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.fengwenyi.mp3demo.java8.collectionstream.Collector.*;

/**
 * @author : Caixin
 * @date 2019/7/19 11:24
 */
public class CollectorHarness {
    public static void main(String[] args) {
        /**
         *  6.6.2 比较收集器的性能
         *  三位一分
         *  3位1千，
         *  4位1万，
         *  5位10万，
         *  6位100万
         *  7位1000万
         *  8位1亿
         */
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) { //运行测试10次
            long start = System.nanoTime();
            partitionPrimes(1_000_000); //将前一百万个自然数按质数和非质数分区
            long duration = (System.nanoTime() - start) / 1_000_000; //取运行时间的毫秒值
            if (duration < fastest) { //检查这个执行是否是最快的一个
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");


        long fastest3 = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) { //运行测试10次
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000); //将前一百万个自然数按质数和非质数分区
            long duration = (System.nanoTime() - start) / 1_000_000; //取运行时间的毫秒值
            if (duration < fastest3) { //检查这个执行是否是最快的一个
                fastest3 = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest3 + " msecs");
        //还不错！这意味着开发自定义收集器并不是白费工夫，
        // 原因有二：第一，你学会了如何在需要的时候实现自己的收集器；
        // 第二，你获得了大约32%的性能提升。
        //最后还有一点很重要，就像代码清单6-5中的 ToListCollector 那样，也可以通过把实现
        //PrimeNumbersCollector 核心逻辑的三个函数传给 collect 方法的重载版本来获得同样的结果：
        
        long fastest4 = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) { //运行测试10次
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector2(1_000_000); //将前一百万个自然数按质数和非质数分区
            long duration = (System.nanoTime() - start) / 1_000_000; //取运行时间的毫秒值
            if (duration < fastest4) { //检查这个执行是否是最快的一个
                fastest4 = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest4 + " msecs");
    }

    private static boolean isPrime2(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    //你看，这样就可以避免为实现 Collector 接口创建一个全新的类；得到的代码更紧凑，
    // 虽然可能可读性会差一点，可重用性会差一点。
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2(Integer n) {
        Map<Boolean, List<Integer>> hashMap = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(() -> new HashMap<Boolean, List<Integer>>() {{ //供应源Supplier
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());
                        }},
                        (acc, candidate) -> {
                            acc.get(isPrime2(acc.get(true), candidate))//累加器
                                    .add(candidate);
                        },
                        (map1, map2) -> {  //组合器
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        }
                );
        return hashMap;
    }

//    6.7 小结
//    以下是你应从本章中学到的关键概念。
//              collect 是一个终端操作，它接受的参数是将流中元素累积到汇总结果的各种方式（称为收集器）。
//              预定义收集器包括将流元素归约和汇总到一个值，例如计算最小值、最大值或平均值。这些收集器总结在表6-1中。
//              预定义收集器可以用 groupingBy 对流中元素进行分组，或用 partitioningBy 进行分区。
//              收集器可以高效地复合起来，进行多级分组、分区和归约。
//              你可以实现 Collector 接口中定义的方法来开发你自己的收集器。






}
