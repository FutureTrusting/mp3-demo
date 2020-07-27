package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamCollect {
    public static void main(String[] args) {
        Map<Integer, Integer> hashMap = IntStream.rangeClosed(1, 18)
                .collect(HashMap::new,
                        (left, right) -> left.put(right, right),
                         Map::putAll
//                      (left1, tight1) -> left1.putAll(tight1)
                );

        List<String> strings = Arrays.asList("a", "a1", "a2", "a3", "a4")
                .stream()
                .sorted(Collections.reverseOrder()) // Method on Stream<Integer>
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(strings));

        List<Integer> integers =
                IntStream.range(1, 10)                      // <-- creates a stream of ints
                        .boxed()                                // <-- converts them to Integers
                        .collect(Collectors.toList());          // <-- collects the values to a list
        //洗牌，打乱顺序
        Collections.shuffle(integers);
        System.out.println(integers);
    }
}
