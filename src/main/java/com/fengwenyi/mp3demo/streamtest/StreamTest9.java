package com.fengwenyi.mp3demo.streamtest;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.*;
import com.fengwenyi.mp3demo.java8.collectionstream.CollectingAndThen;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.annotations.Function;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.maxBy;

/**
 * @author ECHO
 */
public class StreamTest9 {



    public static void main(String[] args) {
        UserVO vo1 = new UserVO();
        UserVO vo2 = new UserVO();
        UserVO vo3 = new UserVO();
        UserVO vo4 = new UserVO();
        UserVO vo5 = new UserVO();
        UserVO vo6 = new UserVO();
        vo1.setId("a");
        vo2.setId("a");
        vo3.setId("b");
        vo4.setId("c");
        vo5.setId("d");
        vo6.setId("d");
        vo1.setUsername("张飞");
        vo2.setUsername("赵云");
        vo3.setUsername("黄忠");
        vo4.setUsername("马超");
        vo5.setUsername("关羽");
        vo6.setUsername("司马懿");
        vo1.setAddress("蜀国");
        vo2.setAddress("蜀国");
        vo3.setAddress("蜀国");
        vo4.setAddress("蜀国");
        vo5.setAddress("蜀国");
        vo6.setAddress("魏国");
        List<UserVO> userVOS = Arrays.asList(vo1, vo2, vo3, vo4, vo5, vo6);
        //java.lang.IllegalStateException: Duplicate key
        Map<String, String> stringMap2 = userVOS.stream()
                .collect(Collectors.toMap(
                        UserVO::getId,
                        UserVO::getUsername,
                        (oldData,newData)->{
                            if(StrUtil.equals(oldData,"张飞")){
                                return oldData;
                            }
                            return newData;
                        })
                );

        Map<String, List<UserVO>> listMap = userVOS.stream()
                .collect(Collectors.groupingBy(UserVO::getId));
        /**
         *      {
         *          "a": [
         *              {
         *                  "id": "a",
         *                  "username": "张飞",
         *                  "address": "蜀国"
         *              },
         *              {
         *                  "id": "a",
         *                  "username": "赵云",
         *                  "address": "蜀国"
         *              }
         *          ],
         *          "b": [
         *              {
         *                  "id": "b",
         *                  "username": "黄忠",
         *                  "address": "蜀国"
         *              }
         *          ],
         *          "c": [
         *              {
         *                  "id": "c",
         *                  "username": "马超",
         *                  "address": "蜀国"
         *              }
         *          ],
         *          "d": [
         *              {
         *                  "id": "d",
         *                  "username": "关羽",
         *                  "address": "蜀国"
         *              },
         *              {
         *                  "id": "d",
         *                  "username": "司马懿",
         *                  "address": "魏国"
         *              }
         *          ]
         *      }
         */
//        System.err.println(new Gson().toJson(stringMap2));

        Map<String, Map<String, List<UserVO>>> mapMap = userVOS.stream()
                .collect(Collectors.groupingBy(UserVO::getId, Collectors.groupingBy(UserVO::getAddress)));
        /**
         *  {
         *      "a": {
         *          "蜀国": [
         *              {
         *                  "id": "a",
         *                  "username": "张飞",
         *                  "address": "蜀国"
         *              },
         *              {
         *                  "id": "a",
         *                  "username": "赵云",
         *                  "address": "蜀国"
         *              }
         *          ]
         *      },
         *      "b": {
         *          "蜀国": [
         *              {
         *                  "id": "b",
         *                  "username": "黄忠",
         *                  "address": "蜀国"
         *              }
         *          ]
         *      },
         *      "c": {
         *          "蜀国": [
         *              {
         *                  "id": "c",
         *                  "username": "马超",
         *                  "address": "蜀国"
         *              }
         *          ]
         *      },
         *      "d": {
         *          "魏国": [
         *              {
         *                  "id": "d",
         *                  "username": "司马懿",
         *                  "address": "魏国"
         *              }
         *          ],
         *          "蜀国": [
         *              {
         *                  "id": "d",
         *                  "username": "关羽",
         *                  "address": "蜀国"
         *              }
         *          ]
         *      }
         *  }
         */
//        Map<String, Integer> integerMap = userVOS.stream().collect(
//                Collectors.groupingBy(
//                        UserVO::getId,
//                        Collectors.collectingAndThen(
//                                UserVO::getAddress,
//                                )
//                ));
        Optional<UserVO> userVO = userVOS.stream()
                .collect(maxBy(comparing(UserVO::getId)));
//        userVOS.stream()
//                .collect(partitioningBy(UserVO::getId,
//                        collectingAndThen(maxBy(comparingInt(UserVO::getId)),
//                                Optional::get)));


//        System.err.println(new Gson().toJson(integerMap));
        System.err.println(new Gson().toJson(mapMap));
        System.err.println(new Gson().toJson(listMap));
    }

}