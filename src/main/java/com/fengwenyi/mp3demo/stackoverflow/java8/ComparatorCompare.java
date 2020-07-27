package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorCompare {

    public static void main(String[] args) {
        List<UserVO> userVOS = Lists.newArrayList(
                new UserVO("a","a","a","a"),
                new UserVO("b","b","b","b"),
                new UserVO("c","c","c","c"),
                new UserVO("d","d","d","d")
        );
        List<String> vos = userVOS.stream()
                .filter(x -> x.getId().equals("a"))
                .map(UserVO::getId)
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(vos));
    }
}
