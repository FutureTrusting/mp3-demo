package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class StreamLatsElement {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //Do a reduction that simply returns the current value:
        Integer integer = integers
                .stream()
                .reduce((a, b) -> b)
                .orElse(null);

        //Guava has Streams.findLast:
        Integer orElse = Streams
                .findLast(integers.stream())
                .orElse(null);

        System.err.println(integer);
        System.err.println(orElse);
    }
}
