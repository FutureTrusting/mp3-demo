package com.fengwenyi.mp3demo.controller;

/**
 * @author : Caixin
 * @date 2019/7/3 10:38
 */
public interface Converter {

//    T convert(F from);

    public static void main(String[] args) {

        String deliverTm = "2013-12-27 12:00:00";

        String time = deliverTm.split(",")[0];

        System.out.println(time);
    }
}
