package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class StreamOptional {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("test");

        String firstString = strings.stream()
                .map(Optional::ofNullable)
                .findFirst()
                .flatMap(Function.identity())
                .orElse("Nullable");

        Supplier<String> sqlSegment = () -> "sss";
        String firstString2 = strings.stream()
//                .skip(0)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(sqlSegment);

        String firstString3 = strings.stream()
//                .skip(0)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(() -> "sss");

        System.err.println(firstString);
        System.err.println(firstString2);
        System.err.println(firstString3);

    }
}
