package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UserSorted;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/10/30 17:53
 */
public class CollectJoining {

    public static void main(String[] args) {
        List<UserSorted> userSortedList = LongStream.rangeClosed(0, 5)
                .boxed()
                .map(x -> {
                    if (x < 3) {
                        return new UserSorted(x, "userName" + x, DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x)));
                    }
                    return new UserSorted(x, "", DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x)));
                })
                .collect(Collectors.toList());

        System.err.println(new Gson().toJson(userSortedList));

        Optional<List<UserSorted>> sortedList = Optional.of(userSortedList);
        String collect = sortedList.map(Collection::stream)
                .orElse(Stream.empty())
                .map(UserSorted::getUsername)
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
        System.err.println(collect);

    }
}
