package com.fengwenyi.mp3demo.stackoverflow.java9;

import java.util.stream.Stream;

public class StreamTakeWhile {

    public static void main(String[] args) {
        //takeWhile() 方法使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false。如果第一个值不满足断言条件，将返回一个空的 Stream。
        //takeWhile() 方法在有序的 Stream 中，takeWhile 返回从开头开始的尽量多的元素；在无序的 Stream 中，takeWhile 返回从开头开始的符合 Predicate 要求的元素的子集。
        Stream.of("a","b","c","","e","f")
//                .takeWhile(s->!s.isEmpty())
                .forEach(System.out::print);
        //以上实例 takeWhile 方法在碰到空字符串时停止循环输出，执行输出结果为：abc

    }
}
