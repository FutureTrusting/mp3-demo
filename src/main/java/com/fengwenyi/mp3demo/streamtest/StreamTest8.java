package com.fengwenyi.mp3demo.streamtest;

import com.fengwenyi.mp3demo.dto.UserDo;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest8 {

    public static void main(String[] args) {
        List<UserDo> listUser = new ArrayList<>();
        listUser.add(new UserDo("李白", 20, true));
        listUser.add(new UserDo("杜甫", 40, true));
        listUser.add(new UserDo("李清照", 18, false));
        listUser.add(new UserDo("李商隐", 23, true));
        listUser.add(new UserDo("杜牧", 39, true));
        listUser.add(new UserDo("苏小妹", 16, false));

        String join1 = listUser.stream().map(UserDo::getUsername).collect(Collectors.joining());
        String join2 = listUser.stream().map(UserDo::getUsername).collect(Collectors.joining(",", "{", "}"));
        System.err.println(join1);
        System.err.println(join2);
        List<String> userNames = listUser.stream().collect(Collectors.mapping(UserDo::getUsername, Collectors.toList()));
        System.err.println(userNames);
    }
}