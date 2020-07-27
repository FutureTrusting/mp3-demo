package com.fengwenyi.mp3demo.java8.hashmap;

import com.fengwenyi.mp3demo.dto.User;
import com.fengwenyi.mp3demo.dto.UserOrderVO;
import com.fengwenyi.mp3demo.enums.OrderDictTypeEnum;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/10/17 19:13
 */
public class EmptyStream {

    public static void main(String[] args) {

        User user1 = new User("user1", "user1", "user1", "user1", "user1");
        User user2 = new User("user2", "user2", "user2", "user2", "user2");
        User user3 = new User("user3", "", "user3", "user3", "user3");
        List<User> users = Arrays.asList(user1, user2, user3);
        List<User> users2 = null;

        List<User> userList = Optional.ofNullable(users2)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .collect(Collectors.toList());

        List<UserOrderVO> collect = userList.stream()
                .map(x -> {
                    UserOrderVO orderVO = new UserOrderVO();
                    orderVO.setId(Long.valueOf(x.getId()));
                    return orderVO;
                })
                .collect(Collectors.toList());

        System.err.println(new Gson().toJson(collect));

    }
}
