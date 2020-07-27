package com.fengwenyi.mp3demo.stackoverflow.java8;

import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.stream.Stream;

public class StreamAllMatchNullTrue {

    public static void main(String[] args) {
        // Why does Stream.allMatch() return true for an empty stream?
        List<String> list = Collections.emptyList();
        List<String> list2 = null;
        //false
        boolean allMatch2 = Optional.of(list)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .anyMatch(Objects::nonNull);
        //false
        boolean allMatch3 = Optional.of(list)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .anyMatch(StrUtil::isBlank);
        //true
        boolean allMatch1 = Optional.of(list)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .allMatch("c"::equals);

        //false
        boolean allMatch4 = Optional.ofNullable(list2)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .anyMatch(StrUtil::isBlank);
        //true
        boolean allMatch5 = Optional.ofNullable(list2)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .allMatch("c"::equals);

        System.err.println(allMatch1); //true
        System.err.println(allMatch2); //false
        System.err.println(allMatch3); //false
        System.err.println(allMatch4); //false
        System.err.println(allMatch5); //true
    }

}
