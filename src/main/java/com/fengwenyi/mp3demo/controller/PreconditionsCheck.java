package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.dto.Apple;
import com.google.common.base.Preconditions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/8/1 11:04
 */
public class PreconditionsCheck {

    public static void main(String[] args) {
        Apple apple = new Apple();
        Preconditions.checkNotNull(apple.getCountry(), "name为null");
        Preconditions.checkArgument(apple.getColor().length()>0, "name为\'\'");
        Preconditions.checkArgument(apple.getWeight()>0, "age 必须大于0");
    }

    @Test
    public void preconditions_2() throws Exception {
        getPersonByPrecondition(8, "peida");
        try {
            getPersonByPrecondition(-9, "peida");
        } catch (Exception e) {
            System.out.println("e.getStackTrace():" + e.getStackTrace());
            System.out.println("e.getMessage():" + e.getMessage());
            System.out.println("e.getCause():" + e.getCause());
            System.out.println("e.getSuppressed():" + e.getSuppressed());
        }
        try {
            getPersonByPrecondition(8, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("getPersonByPrecondition(8, \"\")==============");
        try {
            getPersonByPrecondition(8, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("getPersonByPrecondition(8, null)==============");
        List<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("i:" + i);
                checkState(intList, 9);
                intList.add(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("checkState(intList, 9)===========");

        try {
            checkPositionIndex(intList, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkPositionIndex(intList, 3)================");

        try {
            checkPositionIndex(intList, 13);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkPositionIndex(intList, 13)==============");

        try {
            checkPositionIndexes(intList, 3, 7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkPositionIndexes(intList, 3, 7)============");

        try {
            checkPositionIndexes(intList, 3, 17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkPositionIndexes(intList, 3, 17)===============");

        try {
            checkPositionIndexes(intList, 13, 17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkPositionIndexes(intList, 13, 17)==============");

        try {
            checkElementIndex(intList, 6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkElementIndex(intList, 6)===================");

        try {
            checkElementIndex(intList, 16);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("checkElementIndex(intList, 16)=================");
    }

    public static void getPersonByPrecondition(int age, String name) throws Exception {
        Preconditions.checkNotNull(name, "name为null");
        Preconditions.checkArgument(name.length() > 0, "name为\'\'");
        Preconditions.checkArgument(age > 0, "age 必须大于0");
        System.out.println("a person age:" + age + ",name:" + name);
    }

    public static void checkState(List<Integer> intList, int index) throws Exception {
        //表达式为true不抛异常
        Preconditions.checkState(intList.size() < index, " intList size 不能大于" + index);
    }

    public static void checkPositionIndex(List<Integer> intList, int index) throws Exception {
        Preconditions.checkPositionIndex(index, intList.size(), "index " + index + " 不在 list中， List size为：" + intList.size());
    }

    public static void checkPositionIndexes(List<Integer> intList, int start, int end) throws Exception {
        Preconditions.checkPositionIndexes(start, end, intList.size());
    }

    public static void checkElementIndex(List<Integer> intList, int index) throws Exception {
        Preconditions.checkElementIndex(index, intList.size(), "index 为 " + index + " 不在 list中， List size为： " + intList.size());
    }
}
