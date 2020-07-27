package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.model.PersonVO;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/7/3 13:47
 */
public class StreamController {


    public static void main(String[] args) {

        // 构建一个 Person 集合
        List<PersonVO> persons =
                Arrays.asList(
                        new PersonVO("Max", "18"),
                        new PersonVO("Peter", "23"),
                        new PersonVO("Pamela", "23"),
                        new PersonVO("David", "12"));

        Collector<PersonVO, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier 供应器
                        (j, p) -> j.add(p.getFirstName().toUpperCase()),  // accumulator 累加器
                        (j1, j2) -> j1.merge(j2),               // combiner 组合器
                        StringJoiner::toString);                // finisher 终止器

        String names = persons
                .stream()
                .collect(personNameCollector); // 传入自定义的收集器

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID

        persons
                .stream()
                .reduce((p1, p2) -> Integer.valueOf(p1.getLastName()) > Integer.valueOf(p2.getLastName()) ? p1 : p2)
                .ifPresent(System.out::println);    // Pamela


//        Map<String, String> map = persons
//                .stream()
//                .collect(Collectors.toMap(
//                        p -> p.getLastName(),
//                        p -> p.getFirstName(),
//                        (name1, name2) -> name1 + ";" + name2)); // 对于同样 key 的，将值拼接
//        System.out.println(map);
// {18=Max, 23=Peter;Pamela, 12=David}


//        String phrase = persons
//                .stream()
//                .filter(p -> Integer.parseInt(p.getLastName()) >= 18) // 过滤出年龄大于等于18的
//                .map(p -> p.getFirstName()) // 提取名字
//                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age.")); // 以 In Germany 开头，and 连接各元素，再以 are of legal age. 结束
//
//        System.out.println(phrase);
// In Germany Max and Peter and Pamela are of legal age.


//        IntSummaryStatistics ageSummary =
//                persons
//                        .stream()
//                        .collect(Collectors.summarizingInt(p -> Integer.parseInt(p.getLastName()))); // 生成摘要统计
//
//        System.out.println(ageSummary);
// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}


//        List<PersonVO> filtered =
//                persons
//                        .stream() // 构建流
//                        .filter(p -> p.getFirstName().startsWith("P")) // 过滤出名字以 P 开头的
//                        .collect(Collectors.toList()); // 生成一个新的 List
//
//        System.out.println(filtered);    // [Peter, Pamela]

//        Map<String, List<PersonVO>> personsByAge =
//                persons
//                .stream()
//                .collect(Collectors.groupingBy(p -> p.getLastName())); // 以年龄为 key,进行分组
//
//        personsByAge
//                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));


//        Double averageAge = persons
//                .stream()
//                .collect(Collectors.averagingInt(p -> Integer.valueOf(p.getLastName()))); // 聚合出平均年龄
//        System.out.println(averageAge);     // 19.0


//        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        //通过构造一个新的流，来避开流不能被复用的限制, 这也是取巧的一种方式。
//        Supplier<StreamUtil<String>> streamSupplier = () -> StreamUtil.of("d2", "a2", "b1", "b3", "c")
//                .filter(s -> s.startsWith("a"));
//
//        streamSupplier.get().forEach(System.out::println);
//        streamSupplier.get().forEach(System.out::println);
//
//        StreamUtil<String> stringStream = streamSupplier.get();
//        boolean anyMatch = stringStream.anyMatch(s ->true);

//        streamSupplier.get().anyMatch(s -> true);   // ok
//        streamSupplier.get().noneMatch(s -> true);  // ok


//        StreamUtil<String> stream =
//                StreamUtil.of("d2", "a2", "b1", "b3", "c")
//                        .filter(s -> s.startsWith("a"));
//        stream.anyMatch(s -> true);    // ok
//        stream.noneMatch(s -> true);   // exception


//        StreamUtil.of("d2", "a2", "b1", "b3", "c")
//                .filter(s -> {
//                    System.out.println("filter: " + s);
//                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
//                })
//                .sorted((s1, s2) -> {
//                    System.out.printf("sort: %s; %s\n", s1, s2);
//                    return s1.compareTo(s2); // 排序
//                })
//                .map(s -> {
//                    System.out.println("map: " + s);
//                    return s.toUpperCase(); // 转大写
//                })
//                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出


//        StreamUtil.of("d2", "a2", "b1", "b3", "c")
//                .filter(s -> {
//                    System.out.println("filter: " + s);
//                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
//                })
//                .map(s -> {
//                    System.out.println("map: " + s);
//                    return s.toUpperCase(); // 转大写
//                })
//                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出


//        Arrays.asList("d2", "a2", "b1", "b3", "c")
////                .stream()
////                .map(s->{
////                    System.out.println("map: " + s);
////                    return s.toUpperCase(); // 转大写
////                })
////                .filter(s->{
////                    System.out.println("filter: " + s);
////                    return s.startsWith("A");
////                })
////                .forEach(s->System.out.println("forEach: " + s));


//         StreamUtil.of("d2", "a2", "b1", "b3", "c")
//                .map(s -> {
//                    System.out.println("map: " + s);
//                    return s.toUpperCase(); // 转大写
//                })
//                .anyMatch(s -> {
//                    System.out.println("anyMatch: " + s);
//                    return s.startsWith("A"); // 过滤出以 A 为前缀的元素
//                });


//        Arrays.asList("d2", "a2", "b1", "b3", "c")
//                .stream()
//                .filter(s->{
//                    System.out.println("filter: " + s);
//                    return true;
//                }).collect(Collectors.toList());


//        Arrays.asList("d2", "a2", "b1", "b3", "c")
//                .stream()
//                .filter(s->{
//                    System.out.println("filter: " + s);
//                    return true;
//                }).forEach(s->System.out.println("forEach: " + s));


//        List<String> stringList = Arrays.asList(1.0, 2.0, 3.0)
//                .stream()
//                .mapToInt(Double::intValue)
//                .mapToObj(i -> "a" + i)
//                .collect(Collectors.toList());
//        System.out.println(stringList);
//
//        StreamUtil.of(1.0, 2.0, 3.0)
//                .mapToInt(Double::intValue) // double 类型转 int
//                .mapToObj(i -> "a" + i) // 对值拼接前缀 a
//                .forEach(System.out::println); // for 循环打印

// a1
// a2
// a3


//        IntStream.range(1, 4)
//                .mapToObj(i -> "a" + i) // for 循环 1->4, 拼接前缀 a
//                .forEach(System.out::println); // for 循环打印


//        Arrays.asList("a1", "a2", "a3").stream()
//                .map(s -> s.substring(1)) // 对每个字符串元素从下标1位置开始截取
//                .mapToInt(Integer::parseInt) // 转成 int 基础类型类型流
//                .max() // 取最大值
//                .ifPresent(System.out::println);  // 不为空则输出
// 3


//        StreamUtil<String> stringStream = StreamUtil.of("a1", "a2", "a3");
//        IntStream stream = Arrays.stream(new int[]{1, 2, 3});


//        Arrays.stream(new int[] {1, 2, 3})
//                .map(n -> 2 * n + 1) // 对数值中的每个对象执行 2*n + 1 操作
//                .average() //求平均值
//                .ifPresent(System.out::println);  // 如果值不为空，则输出
// 5.0

//        IntStream.range(1, 4)
//                .forEach(System.out::println); // 相当于 for (int i = 1; i < 4; i++) {}

// 1
// 2
// 3


//        StreamUtil.of("a1", "a2", "a3")
//                .findFirst()
//                .ifPresent(System.out::println);  // a1

//        Arrays.asList("a1", "a2", "a3")
//                .stream() // 创建流
//                .findFirst() // 找到第一个元素
//                .ifPresent(System.out::println);  // 如果存在，即输出
// a1


//        myList
//                .stream() // 创建流
//                .filter(s -> s.startsWith("c")) // 执行过滤，过滤出以 c 为前缀的字符串
//                .map(String::toUpperCase) // 转换成大写
//                .sorted() // 排序
//                .forEach(System.out::println); // for 循环打印
        // C1
        // C2

    }
}
