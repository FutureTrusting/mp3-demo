package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class GroupingByCollectingAndThen {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Person One", 1, 18));
        persons.add(new Person("Person Two", 1, 20));
        persons.add(new Person("Person Three", 1, 30));
        persons.add(new Person("Person Four", 2, 30));
        persons.add(new Person("Person Five", 2, 29));
        persons.add(new Person("Person Six", 3, 18));

//        Map<Integer, Data> result = persons.stream().collect(
//                Collectors.groupingBy(person -> person.group, multiCollector)
//        );
        Map<Integer, Data> result2 = persons.stream()
                .collect(Collectors.groupingBy(Person::getGroup,
                        //summarizingDouble(Person::getAge) 一次性得到元素个数、总和、均值、最大值、最小值
                        Collectors.collectingAndThen(summarizingDouble(Person::getAge),
                                dss ->{
                                    // dss 元素个数、总和、均值、最大值、最小值 对象
                                    // DoubleSummaryStatistics{count=3, sum=68.000000, min=18.000000, average=22.666667, max=30.000000}
                                    // DoubleSummaryStatistics{count=2, sum=59.000000, min=29.000000, average=29.500000, max=30.000000}
                                    // DoubleSummaryStatistics{count=1, sum=18.000000, min=18.000000, average=18.000000, max=18.000000}
                                    System.err.println("dss" + dss);
                                    return new Data((long) dss.getAverage(),
                                            (long) dss.getSum());
                                }
                        )));
        Map<Integer, Data> result = persons.stream()
                .collect(Collectors.groupingBy(Person::getGroup,
                        Collectors.collectingAndThen(summarizingDouble(Person::getAge),
                                dss -> new Data((long) dss.getAverage(),
                                        (long) dss.getSum())
                        )));

        //转换函数返回的类型
        Integer collect = persons.stream()
                .collect(collectingAndThen(toList(), List::size));

        // Collectors.collectingAndThen(W,M) 返回一个收集器
        // W：要转换的收集器 M：转换函数
         persons.stream()
                .collect(groupingBy(Person::getGroup,
                 collectingAndThen(maxBy(comparing(Person::getGroup)),
                         Optional::get
                 )));

        List<TimePeriodCalc> periodCalcs = Arrays.asList(
                new TimePeriodCalc(12d, 10d, "A"),
                new TimePeriodCalc(6d, 5d, "A"),
                new TimePeriodCalc(2d, 16d, "A")
        );


        Map<String, AbstractMap.SimpleEntry<Double, Double>> map = periodCalcs.stream()
                .collect(Collectors.groupingBy(TimePeriodCalc::getAtDate,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> {
                                    double occupancy = list.stream().collect(
                                            Collectors.averagingDouble(TimePeriodCalc::getOccupancy));
                                    double efficiency = list.stream().collect(
                                            Collectors.averagingDouble(TimePeriodCalc::getEfficiency));
                                    return new AbstractMap.SimpleEntry<>(occupancy, efficiency);
                                })));


        Map<String, Map<Double, Double>> mapMap = periodCalcs.stream()
                .collect(groupingBy(TimePeriodCalc::getAtDate,
                        collectingAndThen(Collectors.toList(),
                                list -> {
                                    //list 对象的值
                                    System.err.println(new Gson().toJson(list));
                                    double occupancy = list.stream().collect(
                                            averagingDouble(TimePeriodCalc::getOccupancy));
                                    double efficiency = list.stream().collect(
                                            averagingDouble(TimePeriodCalc::getEfficiency));
                                    Map<Double, Double> mapDouble = new HashMap<>();
                                    mapDouble.put(occupancy, efficiency);
                                    return mapDouble;
                                    // return new AbstractMap.SimpleEntry<>(occupancy, efficiency);
                                })
                ));

        // How to count the frequency of words of List in Java 8
        List <String> wordsList = Lists.newArrayList("hello", "bye", "ciao", "bye", "ciao");
        // The result must be:
        // {ciao=2, hello=1, bye=2}
        Map<String, Long> collect6 =
                wordsList.stream().collect(groupingBy(Function.identity(), counting()));
        //Or for Integer values:
        Map<String, Integer> collect7 =
                wordsList.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));
        //I add how to sort the map by value:
        LinkedHashMap<String, Long> countByWordSorted = collect6.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));
    }


    @lombok.Data
    @AllArgsConstructor
    public static class Person{
        private String name;
        private Integer group;
        private Integer age;
    }
    @lombok.Data
    @AllArgsConstructor
    public static class Data {
        private Long average;
        private Long sum;
    }

    @lombok.Data
    @AllArgsConstructor
    public class Data2 {
        private Integer average,
                sum,
                sum2;
    }


    @lombok.Data
    @AllArgsConstructor
    public static class TimePeriodCalc {
        private Double occupancy;
        private Double efficiency;
        private String atDate;
    }
}
