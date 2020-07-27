package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamDistinct {

    public static void main(String[] args) {
        List<UserVO> userV2 = Arrays.asList(
                new UserVO("1", "2", "1", "1"),
                new UserVO("2", "3", "2", "2"),
                new UserVO("3", "3", "3", "3")
        );

        List<UserVO> collect = userV2.stream()
                .filter(distinctByKey(UserVO::getUsername))
                .collect(Collectors.toList());
        System.err.println(collect);

        List<String> collect2 = userV2.stream()
                .map(UserVO::getUsername)
                .collect(Collectors.toList());
        System.err.println(collect);
        System.err.println(collect2);

    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
