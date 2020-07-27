package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.model.Bar;
import com.fengwenyi.mp3demo.model.Foo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/7/3 10:37
 */
public class Lambda4 {


    public static void main(String[] args) {

        List<Foo> foos = new ArrayList<>();
        // 创建 foos 集合
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        // 创建 bars 集合
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.getBars().add(new Bar("Bar" + i + " <- " + f.getName()))));

        //获取到的每个foo 的子集bar
//        foos.stream()
//                .flatMap(f -> f.getBars().stream())
//                .forEach(s-> System.out.println(s.getName()));
//                .collect(Collectors.toList());
//        System.out.println(barList);


//        IntStream.range(1, 4)
//                .mapToObj(i -> new Foo("Foo" + i))
//                .peek(f -> IntStream.range(1, 4)
//                        .mapToObj(i -> new Bar("Bar" + i + " <- "+f.getName()))
//                        .forEach(f.getBars()::add))
//                .flatMap(f -> f.getBars().stream())
//                .forEach(b -> System.out.println(b.getName()));


//        Arrays.asList("a1", "a2", "b1", "c2", "c1")
//                .parallelStream()
//                .filter(s -> {
//                    System.out.format("filter: %s [%s]\n",
//                            s, Thread.currentThread().getName());
//                    return true;
//                })
//                .map(s -> {
//                    System.out.format("map: %s [%s]\n",
//                            s, Thread.currentThread().getName());
//                    return s.toUpperCase();
//                })
//                .forEach(s -> System.out.format("forEach: %s [%s]\n",
//                        s, Thread.currentThread().getName()));


        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));



    }




    // 静态变量
//    static int outerStaticNum;
//    // 成员变量
//    int outerNum;
//
//    void testScopes() {
//
//        Converter<Integer, String> stringConverter1 = (from) -> {
//            // 对成员变量赋值
//            outerNum = 23;
//            return String.valueOf(from);
//        };
//
//        Converter<Integer, String> stringConverter2 = (from) -> {
//            // 对静态变量赋值
//            outerStaticNum = 72;
//            return String.valueOf(from);
//        };
//    }



}
