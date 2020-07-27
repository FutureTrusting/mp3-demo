package com.fengwenyi.mp3demo.streamtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest1 {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 6, 7);
        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 6, 7);
        List<Integer> integerList = Stream.concat(integers.stream(), integers2.stream())
                .collect(Collectors.toList());
        System.err.println(integerList);
    }
}
