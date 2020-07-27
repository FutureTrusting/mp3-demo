package com.fengwenyi.mp3demo.java8.utils;

import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/7/23 14:46
 */
public class OptionalUtility {

    public static Optional<Integer> stringToInt(String s) {
        try {
            //如果 String 能转换为对应的 Integer ，将其封装在 Optioal 对象中返回
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            //否则返回一个空的 Optional 对象
            return Optional.empty();
        }
    }


}
