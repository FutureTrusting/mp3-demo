package com.fengwenyi.mp3demo.streamtest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest5 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5,6,7,8);

        List<Integer> integers = Stream.concat(list.stream(), list2.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.err.println(integers);
    }
}
