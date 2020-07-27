package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.UsReceiveAddressDo;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/11/1 16:23
 */
public class IfExistReceiveAddressUpdateOrInsert {

    public static void main(String[] args) {

        UsReceiveAddressDo receiveAddressDo1 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo2 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo3 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo4 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo5 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo6 = new UsReceiveAddressDo();
        UsReceiveAddressDo receiveAddressDo7 = new UsReceiveAddressDo();

        receiveAddressDo1.setReceiveCompany("1");
        receiveAddressDo2.setReceiveCompany("2");
        receiveAddressDo3.setReceiveCompany("3");
        receiveAddressDo4.setReceiveCompany("4");
        receiveAddressDo5.setReceiveCompany("5");
        receiveAddressDo6.setReceiveCompany("6");
        receiveAddressDo7.setReceiveCompany("5");




        List<UsReceiveAddressDo> list = Arrays.asList(receiveAddressDo1, receiveAddressDo2, receiveAddressDo3, receiveAddressDo4, receiveAddressDo5);

        List<UsReceiveAddressDo> list2 = Arrays.asList(receiveAddressDo6, receiveAddressDo7);

        List<UsReceiveAddressDo> list3 = Arrays.asList(receiveAddressDo1);

        List<Optional<UsReceiveAddressDo>> optionals = Arrays.asList(Optional.ofNullable(receiveAddressDo1),
                Optional.ofNullable(receiveAddressDo2),
                Optional.ofNullable(receiveAddressDo3));


//        list2.forEach(addressDo->{
//            boolean company = list.stream().noneMatch(x -> StrUtil.equalsIgnoreCase(x.getReceiveCompany(),addressDo.getReceiveCompany()));
//            System.err.println(company);
//        });

        List<String> usReceiveAddressDoList = Optional.of(list3)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(Optional::ofNullable)
                .map(x -> x.map(UsReceiveAddressDo::getOpenId)
                        .orElse("1")
                ).collect(Collectors.toList());

        usReceiveAddressDoList.forEach(System.err::println);


        List<String> collect = Optional.ofNullable(optionals)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(x -> x.map(UsReceiveAddressDo::getOpenId).orElse("2"))
                .collect(Collectors.toList());

        collect.forEach(System.err::println);

    }
}
