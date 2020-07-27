package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorsMapping {


    public static void main(String[] args) {
        List<UserVO> vos = Lists.newArrayList(
                new UserVO("a", "a", "a", "a"),
                new UserVO("a", "b", "b", "b"),
                new UserVO("a", "c", "c", "c"),
                new UserVO("a", "c", "c", "c"),
                new UserVO("b", "c", "c", "c"),
                new UserVO("b", "c", "c", "c"),
                new UserVO("b", "d", "d", "d")
        );
        Map<String, Set<String>> setMap = vos.stream()
                .collect(groupingBy(UserVO::getId,
                        //封装  Collectors.mapping List
                        mapping(UserVO::getUsername, Collectors.toSet())
                ));
        Map<String, Integer> collect = vos.stream()
                .collect(groupingBy(UserVO::getId,
                        mapping(UserVO::getUsername, Collectors.toSet())
                )).entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().size()
                ));

        Map<String, Long> longMap = vos.stream()
                .collect(groupingBy(UserVO::getId,
                        Collectors.counting()));


        Map<String, Integer> integerMap = vos.stream().collect(
                groupingBy(
                        UserVO::getId,
                        collectingAndThen(groupingBy(UserVO::getUsername, Collectors.counting()),
                                Map::size)
                ));

        Map<String, Integer> integerMap1 = vos.stream().collect(
                groupingBy(
                        UserVO::getId,
                        collectingAndThen(
                                mapping(UserVO::getUsername, toSet()),
                                Set::size)
                ));

        System.err.println(new Gson().toJson(setMap));
        System.err.println(new Gson().toJson(collect));
        System.err.println(new Gson().toJson("longMap>>>>>" + new Gson().toJson(longMap)));
        System.err.println(new Gson().toJson("integerMap>>>>>" + new Gson().toJson(integerMap)));

    }
}
