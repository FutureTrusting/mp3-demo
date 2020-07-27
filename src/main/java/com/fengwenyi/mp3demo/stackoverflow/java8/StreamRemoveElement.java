package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;

import java.util.*;

public class StreamRemoveElement {

    public static void main(String[] args) {
        List<String> str1 = new ArrayList<>();
            str1.add("A");
            str1.add("B");
            str1.add("C");
            str1.add("D");
        List<String> str2 = new ArrayList<>();
            str2.add("D");
            str2.add("E");
        str1.removeIf(x -> str2.contains(x));
        str1.forEach(System.out::println); // A B C

        List<UserVO> userVOS = Arrays.asList(
                new UserVO("1", "a", "1", "1"),
                new UserVO("1", "b", "2", "2"),
                new UserVO("1", "c", "11", "11"),
                new UserVO("2", "d", "2", "2"),
                new UserVO("2", "e", "2", "2"),
                new UserVO("2", "f", "2", "2")
        );
        Set<UserVO> userVOSet = new HashSet<>(userVOS);
        userVOSet.removeIf(item -> {
            if (item.getId().equalsIgnoreCase("1")){
                return false;
            }
            return true;
        });
        System.err.println(new Gson().toJson(userVOSet));


    }
}
