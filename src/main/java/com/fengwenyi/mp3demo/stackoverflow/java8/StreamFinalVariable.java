package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StreamFinalVariable {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> list1 = Lists.newArrayList(11, 22, 33);
        AtomicReference<List<Integer>> strings = new AtomicReference<>(list1);
        List<UserVO> voList = integers.stream()
                .map(x -> {
                    String s = x.toString();
                    List<Integer> list2 = strings.get();
                    list2.add(x);
                    strings.set(list2);
                    UserVO userVO = new UserVO(s, s, s, s);
                    return userVO;
                })
                .collect(Collectors.toList());
        System.err.println("voList" + new Gson().toJson(voList));
        System.err.println(new Gson().toJson(strings.get()));
    }
}
