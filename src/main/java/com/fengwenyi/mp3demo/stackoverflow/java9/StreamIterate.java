package com.fengwenyi.mp3demo.stackoverflow.java9;

import java.util.stream.IntStream;

public class StreamIterate {

    public static void main(String[] args) {
        //方法允许使用初始种子值创建顺序（可能是无限）流，并迭代应用指定的下一个方法。 当指定的 hasNext 的 predicate 返回 false 时，迭代停止。
//        IntStream.iterate(3, x -> x < 10, x -> x+ 3)
//                .forEach(System.out::println);
        //3
        //6
        //9
    }
}
