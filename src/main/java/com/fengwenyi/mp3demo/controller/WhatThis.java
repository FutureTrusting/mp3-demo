package com.fengwenyi.mp3demo.controller;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caixin
 * @date 2018/11/7 16:48
 */
public class WhatThis {

    public void whatThis(){
        //转全小写
        List<String> stringList = Lists.newArrayList("Ni","Hao","Lambda");

        stringList.stream().map(r->r.charAt(0)).forEach(System.out::println);

        List<String> execStrs = stringList.stream().map(String::toLowerCase).collect(Collectors.toList());

        execStrs.forEach(System.out::println);
    }

    public static void main(String[] args) {
        WhatThis wt = new WhatThis();
        wt.whatThis();
        long l = System.currentTimeMillis();
        System.out.println(l);
    }
}
