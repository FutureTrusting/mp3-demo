package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapList2MapObject {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "bb", "ccc");
        Map<String, Integer> mapCollect = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.err.println(new Gson().toJson(mapCollect));
        // with
        Map<String, Integer> mapCollect2 = strings.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length,
                        (u, v) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                        },
                        LinkedHashMap::new
                ));
        System.err.println(new Gson().toJson(mapCollect2));
        //Or to make it a bit cleaner, write a new toLinkedMap() method and use that:
        Map<String, Integer> mapCollect3 = strings.stream()
                .collect(MoreCollectors.toLinkedMap(
                        Function.identity(),
                        String::length
                ));
        //to tree Map
        Map<String, Integer> mapCollect4 = strings.stream()
                .collect(MoreCollectorsV2.toTreeMap(
                        Function.identity(),
                        String::length,
                        (oldV,newV)->newV
                ));
        Map<String, Integer> mapCollect5 = strings.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length,
                        (oldV,newV)->newV,
                        TreeMap::new
                ));
        System.err.println(new Gson().toJson(mapCollect3));

        List<UserVO> userVOS = Arrays.asList(
                new UserVO("1", "a", "1", "1"),
                new UserVO("1", "b", "2", "2"),
                new UserVO("1", "c", "11", "11"),
                new UserVO("2", "d", "2", "2"),
                new UserVO("2", "e", "2", "2"),
                new UserVO("2", "f", "2", "2")
        );
        // Map like this
        // 1: a,b,c
        // 2: d,e,f
        // etc.
        Map<String, List<String>> mapFrom = userVOS.stream()
                .collect(Collectors.groupingBy(
                        UserVO::getId,
                        Collectors.mapping(UserVO::getUsername, Collectors.toList())
                ));
        // wanna
        //  a: 1
        //  b: 1
        //  c: 1
        //  d: 2
        //  e: 2
        //  f: 2
        //  etc.

        Map<String, String> stringMap = mapFrom.entrySet()
                .stream()
                .flatMap(x -> x.getValue()
                        .stream()
                        .map(v -> {
//                          new AbstractMap.SimpleImmutableEntry<>(x.getKey(), v)
                            Map<String, String> map = new HashMap<>();
                            map.put(x.getKey(), v);
                            return map.entrySet();
                        }))
                .flatMap(Collection::stream)
//                .collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey)
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
                );
//        stringMap.put("a","d");


        Map<String, String> mapTo = mapFrom.entrySet()
                .stream()
                .flatMap(e -> e.getValue()
                        .stream()
                        .map(v -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), v)))
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        HashMap<String, String> map = new HashMap<>();
        map.put("s", "");
        map.put("not empty", "not empty");
        Map<String, String> notEmpty = map.entrySet()
                .stream()
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public static class MoreCollectors {
        public static <T, K, U> Collector<T, ?, Map<K, U>> toLinkedMap(
                Function<? super T, ? extends K> keyMapper,
                Function<? super T, ? extends U> valueMapper) {
            return Collectors.toMap(
                    keyMapper,
                    valueMapper,
                    (u, v) -> {
                        throw new IllegalStateException(String.format("Duplicate key %s", u));
                    },
                    LinkedHashMap::new
            );
        }
    }

    public static class MoreCollectorsV2 {
        public static <T, K, U> Collector<T, ?, Map<K, U>> toTreeMap(
                Function<? super T, ? extends K> keyMapper,
                Function<? super T, ? extends U> valueMapper,
                BinaryOperator<U> mergeFunction) {
            return Collectors.toMap(
                    keyMapper,
                    valueMapper,
                    mergeFunction,
                    TreeMap::new
            );
        }
    }
}
