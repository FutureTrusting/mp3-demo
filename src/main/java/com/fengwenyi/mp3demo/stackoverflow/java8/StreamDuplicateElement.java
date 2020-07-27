package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDuplicateElement {


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 2, 2, 3, 3, 4);
        // left1 指 TreeSet::new
        // right1 指迭代的Map
        List<Integer> listMap = numbers.stream()
                .collect(Collectors.groupingBy(
                        x -> x,
                        Collector.of(
                                (Supplier<ArrayList<Integer>>) ArrayList::new,
                                List::add,
                                (left, right) -> {
                                    left.addAll(right);
                                    return left;
                                }
                        )
                ))
                .entrySet()
                .stream()
                .filter(y -> y.getValue().size() > 1)
                .flatMap(z -> z.getValue().stream())
                .collect(Collectors.toList());
        //1, 1, 2, 2, 2, 3, 3
        System.err.println(new Gson().toJson(listMap));

        //You can use Collections.frequency:
        numbers.stream()
                .filter(i -> Collections.frequency(numbers, i) > 1)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        // You need a set (allItems below) to hold the entire array contents, but this is O(n):
        Integer[] numbers2 = new Integer[]{1, 2, 1, 3, 4, 4};
        Set<Integer> allItems = new HashSet<>();
        Set<Integer> duplicates2 = Arrays.stream(numbers2)
                .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was already in the set.
                .collect(Collectors.toSet());
        System.out.println(duplicates2); // [1, 4]
        //Basic example. First half builds frequency-map, second half reduces it to a filtered list.
        List<Integer> duplicates = IntStream.of(1, 2, 3, 2, 1, 2, 3, 4, 2, 2, 2)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(p -> p.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(duplicates));
    }
}
