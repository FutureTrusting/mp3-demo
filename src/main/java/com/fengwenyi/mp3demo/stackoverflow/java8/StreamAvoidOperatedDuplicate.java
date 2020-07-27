package com.fengwenyi.mp3demo.stackoverflow.java8;


import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAvoidOperatedDuplicate {

    public static void main(String[] args) {
        // 用Supplier 包装流使用多次不会造成流已经关闭的现象
        List<String> integers = Arrays.asList("d2", "ccc", "b1", "b3", "c");
        Supplier<Stream<String>> streamSupplier = () -> integers.stream().filter(s -> s.startsWith("a"));
        Stream<String> stream = streamSupplier.get();

        boolean anyMatch = streamSupplier.get().anyMatch(s -> true);// ok
        boolean noneMatch = streamSupplier.get().noneMatch(s -> true);// false

        System.err.println(anyMatch);
        System.err.println(noneMatch);

        int[] numbers = {1, 2, 3, 4};
        String commaSeparatedNumbers = Arrays.stream(numbers)
                .mapToObj(i -> ((Integer) i).toString()) //i is an int, not an Integer
                .collect(Collectors.joining(", "));
        //which you can also write:
        String commaSeparatedNumbers2 = Arrays.stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
    }
}
