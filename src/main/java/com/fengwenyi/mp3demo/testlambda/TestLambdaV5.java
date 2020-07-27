package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.Bond;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.F;
import com.google.gson.Gson;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 01376494
 */
public class TestLambdaV5 {
    public static void main(String[] args) {
//        IntStream.rangeClosed(0, 1000)
//                .boxed()
//                .filter(i -> i % 222 == 0 || i % 333 == 0)
//                .forEach(System.out::println);

        UserVO userVO1 = new UserVO("", "b1", "c1", "d1");
        UserVO userVO2 = new UserVO(null, "b1", "c1", "d1");
        UserVO userVO3 = new UserVO("A1", "b1", "c1", "d1");
        UserVO userVO4 = new UserVO("A2", "b1", "c1", "d1");
        UserVO userVO5 = null;
        UserVO userVO6 = new UserVO();
        UserVO userVO7 = new UserVO();
        List<UserVO> voList = Arrays.asList(userVO1, userVO2, userVO3,userVO4,userVO5,userVO6,userVO7);
//        Map<String, String> stringMap = Optional.ofNullable(voList)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//                .filter(Objects::nonNull)
//                .collect(Collectors.toMap(UserVO::getId,
//                        UserVO::getUsername,
//                        (left,right)->right
//                ));
//        System.err.println(new Gson().toJson(stringMap));
//
//        Map<String, String> stringMap1 = Optional.ofNullable(voList)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//                .collect(Collectors.toMap(UserVO::getId, UserVO::getUsername));
//
//        Map<String, UserVO> userVOMap = Optional.ofNullable(voList)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//                .filter(Objects::nonNull)
//                .collect(Collectors.toMap(UserVO::getId,
//                        Function.identity()
////               ,(left, right) -> right));
//                        ));
//        System.err.println(new Gson().toJson(userVOMap));

//        Map<String, String> stringMap2 = Optional.ofNullable(voList)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//                .filter(Objects::nonNull)
//                .filter(x -> StrUtil.isNotBlank(x.getId()))
//                .filter(x -> StrUtil.isNotBlank(x.getUsername()))
//                .collect(Collectors.toMap(UserVO::getId,
//                        UserVO::getUsername,
//                        (left, right) -> right));
//        System.err.println(new Gson().toJson(stringMap2));


//        HashMap<String, String> hashMap = Optional.ofNullable(voList)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//               .filter(Objects::nonNull)
//                .filter(x -> StrUtil.isNotBlank(x.getId()))
//                .filter(x -> StrUtil.isNotBlank(x.getUsername()))
//                .collect(Collectors.toMap(
//                        UserVO::getId,
//                        UserVO::getUsername,
//                        (left, right) -> right,
//                        HashMap::new));
//        System.err.println(new Gson().toJson(hashMap));

        Map<String, String> stringHashMap = Optional.ofNullable(voList)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .filter(Objects::nonNull)
                .collect(HashMap::new, (m, v) ->
                        m.put(v.getId(), v.getUsername()), HashMap::putAll);
        System.err.println(new Gson().toJson(stringHashMap));
    }
}
