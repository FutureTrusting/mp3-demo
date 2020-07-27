package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.dto.User;
import com.fengwenyi.mp3demo.dto.UserVO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : Caixin
 * @date 2019/9/9 11:10
 */
public class ParallelStreamTestController {

    public static void main(String[] args) {

        List<User> userList = IntStream.rangeClosed(0, 999999)
                .boxed()
                .map(x -> {
                    return new User("user" + x, "user" + x, "user" + x, "user" + x, "user" + x);
                })
                .collect(Collectors.toList());

        userList.parallelStream()
                .map(x -> {
                    UserVO userVO = new UserVO();
                    BeanCopier copier = BeanCopier.create(User.class, UserVO.class, false);
                    copier.copy(x, userVO, null);
                    return userVO;
                })
                .filter(x -> !x.getId().equalsIgnoreCase(x.getAddress()) || !x.getId().equalsIgnoreCase(x.getPassword()) || !x.getId().equalsIgnoreCase(x.getUsername()))
                .forEach(System.out::println);
    }
}
