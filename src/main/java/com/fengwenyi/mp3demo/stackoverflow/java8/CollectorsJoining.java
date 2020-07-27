package com.fengwenyi.mp3demo.stackoverflow.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsJoining {
    public static void main(String[] args) {
        List<String> asList = Arrays.asList("a", "b", "c");

        String collect1 = asList.stream()
                .collect(Collectors.joining(""));
        System.err.println(collect1); //abc

        String collect2 = asList.stream()
                .collect(Collectors.joining(","));
        System.err.println(collect2); //a,b,c

        String collect3 = asList.stream()
                .collect(Collectors.joining("-","","-"));
        System.err.println(collect3); //a-b-c-


        Optional<Integer> optional = Stream.of(1, 2, 3, 4, 5, 6)
                .filter(x -> x > 6)
                .findAny();

        String result = "Hello world."
                .codePoints()
                //.parallel()  // uncomment this line for large strings
                .map(c -> c == ' ' ? ' ': '*')
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                )
                .toString();
        System.err.println(result);
    }
}
