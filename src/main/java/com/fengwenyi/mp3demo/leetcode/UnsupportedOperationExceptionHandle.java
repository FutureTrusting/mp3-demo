package com.fengwenyi.mp3demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/9/11 11:10
 */
public class UnsupportedOperationExceptionHandle {

    public static void main(String[] args) {
//        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        integers.add(8);
//        integers.add(9);

        final List<Integer> integers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        integers1.add(8);
        integers1.add(9);
        integers1.forEach(System.out::println);
    }
}
