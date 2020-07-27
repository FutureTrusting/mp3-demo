package com.fengwenyi.mp3demo.stackoverflow.java8;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamReduce {


    public static void main(String[] args) {
        List<List<String>> strings = new ArrayList<>();
        strings.add(Arrays.asList("1", "2", "3", "4"));
        strings.add(Arrays.asList("1", "2", "3", "4"));
        // 非并行流 reduce 三个参数
        Integer reduce1 = strings.stream()
                .flatMap(Collection::stream)
                .reduce(0,
                    (acc, e) ->{
                       // e 指遍历List的值  acc指第二个参数每次进行计算后的值
                       System.err.println("串行流acc=======>>>{}" + acc);
                       System.err.println("串行流e=======>>>{}" + e);
                       return acc + Integer.parseInt(e);
                    },
                    (u, t) -> {
                    // 非并行流，不会执行第三个参数
                    System.out.println("串行流u----:" + u);
                    System.out.println("串行流t----:" + t);
                    // 这里的返回值并没有影响返回结果
                    return null;
                });
        System.out.println("reduce1:" + reduce1);
        // 并行流 reduce 三个参数
        Integer reduce2 = strings.parallelStream()
                .flatMap(Collection::stream)
                .reduce(0,
                    (acc, e) -> {
                       System.err.println("并行流acc=======>>>{}" + acc);
                       System.err.println("并行流e=======>>>{}" + e);
                       return acc + Integer.parseInt(e);
                    },
                    (u, t) -> {
                    // u，t分别为并行流每个子任务的结果
                    System.err.println("并行流u----:" + u);
                    System.err.println("并行流t----:" + t);
                    return u + t;
                });
        System.out.println("reduce2:" + reduce2);
        reducingTest(strings);

        LinkedList<BigDecimal> values = new LinkedList<>();
        values.add(BigDecimal.valueOf(.1));
        values.add(BigDecimal.valueOf(1.1));
        values.add(BigDecimal.valueOf(2.1));
        values.add(BigDecimal.valueOf(.1));
        // Classical Java approach
        BigDecimal sum = BigDecimal.ZERO;
        for(BigDecimal value : values) {
            System.out.println(value);
            sum = sum.add(value);
        }
        System.out.println("Sum = " + sum);
        // Java 8 approach
        values.forEach(System.err::println);
        System.err.println("Sum = " + values.stream().reduce((x, y) -> x.add(y)).get());
        //fix Avoiding NoSuchElementException in Java 8 streams
        System.out.println("Sum = " + values.stream().reduce((x, y) -> x.add(y)).orElse(BigDecimal.ZERO));
    }

    public static void reducingTest(List<List<String>> list){
        List<String> list2 = Arrays.asList("Alice", "Bob", "Charlie");
        //reduce 只有一个参数 操作运算
        String orElse = list2.stream().reduce((t, u) ->{
            System.err.println("reduce3 t" + t);
            System.err.println("reduce3 u" + u);
           return t + " and " + u;
        }).orElse("");
        System.err.println("reduce3" + orElse);

        Optional<Integer> collect = list.stream()
                .flatMap(Collection::stream)
                .limit(4)
                .map(String::length)
                .collect(Collectors.reducing(Integer::sum));
        Integer collect1 = list.stream()
                .flatMap(Collection::stream)
                .limit(3)
                .map(String::length)
                .collect(Collectors.reducing(0, Integer::sum));

        Integer collect2 = list.stream()
                .flatMap(Collection::stream)
                .limit(4)
                .collect(Collectors.reducing(0, String::length, Integer::sum));

        System.out.println(collect);
        System.out.println(collect1);
        System.out.println(collect2);
    }
}
