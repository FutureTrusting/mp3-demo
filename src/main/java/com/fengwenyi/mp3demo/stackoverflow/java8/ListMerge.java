package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.fengwenyi.mp3demo.dto.UserVO2;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ListMerge {
    public static void main(String[] args) {
        List<UserVO> userVo1 = Lists.newArrayList(
                new UserVO("a","a","a","a"),
                new UserVO("b","b","b","b"),
                new UserVO("c","c","c","c"),
                new UserVO("d","d","d","d")
        );
        List<UserVO2> userVo2 = Lists.newArrayList(
                new UserVO2("a","a","a","a"),
                new UserVO2("b","b","b","b"),
                new UserVO2("c2","c","c","c"),
                new UserVO2("d2","d","d","d")
        );
        Set<UserVO> vos = userVo2.stream()
                .filter(c -> Objects.nonNull(c)
                        && Objects.nonNull(c.getId()))
                .map(UserVO2::getId)
                .flatMap(id -> userVo1.stream().filter(track ->track.getId().equals(id)))
                .collect(Collectors.toSet());
        Set<UserVO> vos2 = userVo2.stream()
                .filter(c -> Objects.nonNull(c)
                        && Objects.nonNull(c.getId()))
                .flatMap(id -> userVo1.stream().filter(track ->track.getId().equals(id.getId())))
                .collect(Collectors.toSet());
        System.err.println(new Gson().toJson(vos));
        System.err.println(new Gson().toJson(vos2));
    }

    public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new Iterator<T>() {
                            public T next() {
                                return e.nextElement();
                            }
                            public boolean hasNext() {
                                return e.hasMoreElements();
                            }
                        },
                        Spliterator.ORDERED), false);
    }
}
