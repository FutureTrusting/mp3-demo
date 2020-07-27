package com.fengwenyi.mp3demo.streamtest;

import com.google.gson.Gson;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest7 {

    public static void main(String[] args) {
        List<String> strings = null;
        final Map<String, String> users = Optional.ofNullable(strings)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(x -> x, Function.identity()));
        System.err.println(users.get("1"));
        System.err.println(new Gson().toJson(users));
    }
}