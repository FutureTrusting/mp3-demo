package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.enums.CustTypeEnum;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/10/10 16:35
 */
public class EnumsTest {

    public static void main(String[] args) {

        CustTypeEnum[] typeEnums = CustTypeEnum.values();
        List<String> collect = Arrays.stream(CustTypeEnum.values())
                .filter(x -> Objects.nonNull(x) && Objects.nonNull(x.getCode()))
                .map(CustTypeEnum::getCode)
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(collect));
    }
}
