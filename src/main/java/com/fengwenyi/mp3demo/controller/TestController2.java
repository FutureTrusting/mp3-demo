package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.dto.User;
import com.fengwenyi.mp3demo.dto.UserStudentVO;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/8/15 17:11
 */
public class TestController2 {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);


        User user1 = new User("user1", "user1", "user1", "user1", "user1");
        User user2 = new User("user2", "user2", "user2", "user2", "user2");
        User user3 = new User("user3", "", "user3", "user3", "user3");
        List<User> users = Arrays.asList(user1, user2, user3);

        UserStudentVO studentVO1 = new UserStudentVO("user1","user1");
        UserStudentVO studentVO2 = new UserStudentVO("user2","user2");
        UserStudentVO studentVO3 = new UserStudentVO("user3","user3");
        UserStudentVO studentVO4 = new UserStudentVO("user4","user999");
        UserStudentVO studentVO5 = new UserStudentVO("user5","user998");

        UserStudentVO studentVO6 = new UserStudentVO("user4","user4");
        UserStudentVO studentVO7 = new UserStudentVO("user5","user5");

        List<UserStudentVO> userStudent4 = Arrays.asList(studentVO1, studentVO2, studentVO3,studentVO4,studentVO5);
        List<UserStudentVO> userStudent3 = Arrays.asList(studentVO4, studentVO5);

         userStudent3.stream().map(x -> {
            UserVO reviewConfVO = new UserVO();
             reviewConfVO.setId(x.getId());
             reviewConfVO.setUsername(x.getUsername());
            userStudent4.forEach(y -> {
                if (x.getId().equals(y.getId())) {
                    BeanCopier copier = BeanCopier.create(UserStudentVO.class, UserVO.class, false);
                    copier.copy(y, reviewConfVO, null);
                }
            });
            return reviewConfVO;
        }).collect(Collectors.toList())
                 .forEach(System.err::println);



//        list2.stream()
//                .filter(x -> list1.stream().noneMatch(y -> x.equals(y)))
//                .forEach(System.out::println);
//
//        List<Integer> integerList = list2.stream()
//                .filter(x -> list1.stream()
//                        .noneMatch(x::equals))
//                .collect(Collectors.toList());
//
//        System.err.println(integerList);

//        String toJson = new Gson().toJson(integerList);
//        System.out.println(toJson);

        /**
         * forEach
         * return 跳出本次循环,继续下一次循环
         * 1111
         * 2
         * 3
         * 4
         * 5
         * 6
         */
//        list1.forEach(x -> {
//            if (x == 1) {
//                System.err.println("1111");
//                return;
//            }
//            System.err.println(x);
//        });
//
//        /**
//         * map不适用
//         */
//        list1.stream()
//                .map(x -> {
//                    if (x == 2) {
//                        return null;
//                    }
//                    return x;
//                }).collect(Collectors.toList());

    }
}
