package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import one.util.streamex.MoreCollectors;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapMerge {


    public static void main(String[] args) {
        Map<String, List<String>> mapGlobal = new HashMap<>();
        Map<String, List<String>> mapAdded = new HashMap<>();

        mapGlobal.put("A", Lists.newArrayList("1", "2", "3"));
        mapGlobal.put("B", Lists.newArrayList("11", "22", "33"));
        mapAdded.put("A", Lists.newArrayList("4", "5", "6"));
        mapAdded.put("B", Lists.newArrayList("44", "55", "66"));

        Map<String, List<List<String>>> listMap = Stream.of(mapGlobal, mapAdded)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue,
                                Collectors.toList())
                ));

        Map<String, List<String>> listMap1 = Stream.of(mapGlobal, mapAdded)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collector.of(
                                ArrayList::new,
                                (left1, right1) -> {
                                    // left1 指 TreeSet::new
                                    // right1 指迭代的Map
                                    left1.addAll(right1.getValue());
                                    System.err.println("left1>>>>>>>" + left1);
                                    System.err.println("right1>>>>>>>" + right1);
                                },
                                (left, right) -> {
                                    // left 指 上游的Set
                                    // right 指 迭代的Map
                                    left.addAll(right);
                                    System.err.println("left>>>>>>>" + left);
                                    System.err.println("right>>>>>>>" + right);
                                    return left;
                                }
                        )
                ));
        System.err.println("listMap map merge" + new Gson().toJson(listMap1));


        mapAdded.forEach((k, v) ->
                mapGlobal.merge(k, v, (v1, v2) -> {
                    Set<String> set = new TreeSet<>(v1);
                    set.addAll(v2);
                    return new ArrayList<>(set);
                }));

        ConcurrentMap<String, List<String>> mapGlobal2 = new ConcurrentHashMap<>();
        mapAdded.entrySet()
                .parallelStream()
                .forEach(e -> mapGlobal2.merge(e.getKey(), e.getValue(), (v1, v2) -> {
                    Set<String> set = new TreeSet<>(v1);
                    set.addAll(v2);
                    return new ArrayList<>(set);
                }));

        /**
         *  input example:
         *     [
         *       {
         *           "k1": { "kk1": 1, "kk2": 2},
         *           "k2": {"kk1": 3, "kk2": 4}
         *       }
         *       {
         *           "k1": { "kk1": 10, "kk2": 20},
         *           "k2": {"kk1": 30, "kk2": 40}
         *       }
         *     ]
         *  output:
         *     {
         *          "k1": { "kk1": 11, "kk2": 22},
         *          "k2": {"kk1": 33, "kk2": 44}
         *     }
         */
        List<Map<String, Map<String, Long>>> valueList = new ArrayList<>();
        Map<String, Map<String, Long>> map1 = ImmutableMap.of("a",ImmutableMap.of("1",1L));
        Map<String, Map<String, Long>> map2 = ImmutableMap.of("b",ImmutableMap.of("2",2L));
        valueList.add(map1);
        valueList.add(map2);
//        Map<String, Map<String, Long>> mapMap = mergeMapsValue3(valueList);
        Map<String, List<Integer>> map3 = ImmutableMap.of(
                "A", Lists.newArrayList(1, 2, 3),
                "B", Lists.newArrayList(4, 5, 6),
                "C", Lists.newArrayList(7, 8, 9)
        );
        Map<String, List<Integer>> map4 = ImmutableMap.of(
                "A", Lists.newArrayList(11, 22, 33),
                "B", Lists.newArrayList(44, 55, 66),
                "C", Lists.newArrayList(77, 88, 99)
        );
        Map<String, List<Integer>> map5 = ImmutableMap.of(
                "AA", Lists.newArrayList(11, 22, 33),
                "BB", Lists.newArrayList(44, 55, 66),
                "CC", Lists.newArrayList(77, 88, 99)
        );
        Map<String, List<Integer>> map6 = ImmutableMap.of(
                "AA", Lists.newArrayList(11, 22, 33),
                "BB", Lists.newArrayList(44, 55, 66),
                "CC", Lists.newArrayList(77, 88, 99)
        );
        List<Map<String, List<Integer>>> map34 = Lists.newArrayList(map3, map4);

        /**
         *
         *   Collector<Widget, ?, TreeSet<Widget>> intoSet =
         *   Collector.of(TreeSet::new,
         *                TreeSet::add,
         *                (left, right) -> {
         *                      left.addAll(right);
         *                       return left;
         *                }
         *      );
         *
         */
        Map<String, List<Integer>> setMap = map34.parallelStream()
                .flatMap(e -> e.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collector.of(
                                //The supplier function for the new collector 新的收集器
//                                ()->new ArrayList<>(),
                                ArrayList::new,
                                (left1, right1) ->{
                                    // left1 指 TreeSet::new
                                    // right1 指迭代的Map
                                    left1.addAll(right1.getValue());
                                    System.err.println("left1>>>>>>>"+left1);
                                    System.err.println("right1>>>>>>>"+right1);
                                },
                                (left, right) -> {
                                    // left 指 上游的Set
                                    // right 指 迭代的Map
                                    left.addAll(right);
                                    System.err.println("left>>>>>>>" + left);
                                    System.err.println("right>>>>>>>" + right);
                                    return left;
                                }
                        )
                ));
        System.err.println(new Gson().toJson(setMap));


    }

    private static Map<String, Map<String, Long>> mergeMapsValue(List<Map<String, Map<String, Long>>> valueList) {
        return valueList.stream()
                .flatMap(e -> e.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        MoreCollectors.flatMapping(
                                e -> e.getValue().entrySet().stream(),
                                Collectors.<Map.Entry<String,Long>,String,Long>toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum)
                        )
                ));
    }

    private static Map<String, Map<String, Long>> mergeMapsValue2(List<Map<String, Map<String, Long>>> valueList) {
        return valueList.stream()
                .flatMap(e -> e.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collector.of(
                                HashMap::new,
                                (r, t) -> t.getValue().forEach((k, v) -> r.merge(k, v, Long::sum)),
                                (r1, r2) -> { r2.forEach((k, v) -> r1.merge(k, v, Long::sum)); return r1; }
                        )
                ));
    }

    private static Map<String, Map<String, Long>> mergeMapsValue3(List<Map<String, Map<String, Long>>> valueList) {
        return valueList.stream()
                .flatMap(e -> e.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collector.of(
                                HashMap::new,
                                (r, t) ->{
                                    System.err.println(r);
                                    System.err.println(t);
                                    t.getValue().forEach((k, v) -> r.merge(k, v, Long::sum));
                                },
                                (r1, r2) -> {
                                    System.err.println(r1);
                                    System.err.println(r2);
                                    r2.forEach((k, v) -> r1.merge(k, v, Long::sum));
                                    return r1;
                                }
                        )
                ));
    }
}
