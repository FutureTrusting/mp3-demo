package com.fengwenyi.mp3demo.stackoverflow.java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamMaximum {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        //You may either convert the stream to IntStream:
        OptionalInt max = list.stream().mapToInt(Integer::intValue).max();
        //Or specify the natural order comparator:
        Optional<Integer> max1 = list.stream().max(Comparator.naturalOrder());
        //Or use reduce operation:
        Optional<Integer> max2 = list.stream().reduce(Integer::max);
        //Or use collector:
        Optional<Integer> max3 = list.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        //Or use IntSummaryStatistics:
        int max4 = list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
    }
}
