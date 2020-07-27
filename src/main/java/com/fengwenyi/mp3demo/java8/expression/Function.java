package com.fengwenyi.mp3demo.java8.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Function<T, R>{
    R apply(T t);

     static void main(String[] args) {
        List<Integer> l = map(
                Arrays.asList("lambdas","in","action"),
                (String s) -> s.length()
        );
         System.err.println(l);// [7, 2, 6]
    }

     static <T, R> List<R> map(List<T> list,Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }
}


