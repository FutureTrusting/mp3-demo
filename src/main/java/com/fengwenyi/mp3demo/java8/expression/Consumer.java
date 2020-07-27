package com.fengwenyi.mp3demo.java8.expression;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    static void main(String[] args) {
        forEach(
                Arrays.asList(1, 2, 3, 4, 5),
                (Integer i) -> System.out.println(i)
        );
    }

    static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }
}
