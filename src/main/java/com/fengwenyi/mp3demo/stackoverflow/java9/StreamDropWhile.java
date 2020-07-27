package com.fengwenyi.mp3demo.stackoverflow.java9;

import java.util.stream.Stream;

public class StreamDropWhile {

    public static void main(String[] args) {
        //dropWhile 方法和 takeWhile 作用相反的，使用一个断言作为参数，直到断言语句第一次返回 true 才返回给定 Stream 的子集。
        Stream.of("a","b","c","","e","f")
//                .dropWhile(s-> !s.isEmpty())
                .forEach(System.out::print);
        //以上实例 dropWhile 方法在碰到空字符串时开始循环输出，执行输出结果为：ef
    }
}
