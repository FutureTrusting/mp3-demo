package com.fengwenyi.mp3demo.controller;

import com.baidu.unbiz.easymapper.MapperFactory;
import com.fengwenyi.mp3demo.dto.ReceiveAddressCustomerVO;
import com.fengwenyi.mp3demo.dto.UsReceiveAddressDo;
import com.fengwenyi.mp3demo.response.R;

import java.rmi.ServerError;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/11/4 10:00
 */
public class OptionalMapperFactory {



    public static void main(String[] args) {

        UsReceiveAddressDo addressDo = new UsReceiveAddressDo();
        addressDo.setReceiveAddress("1");
        addressDo.setReceiveCompany("2");
        addressDo.setReceivePhone("3");
        addressDo.setReceiveUserName("4");
        addressDo.setRemark("5");


        UsReceiveAddressDo addressDo2 = new UsReceiveAddressDo();
        addressDo2.setReceiveAddress("11");
        addressDo2.setReceiveCompany("22");
        addressDo2.setReceivePhone("33");
        addressDo2.setReceiveUserName("44");
        addressDo2.setRemark("55");

        UsReceiveAddressDo addressDo3 = new UsReceiveAddressDo();
        addressDo3.setReceiveAddress("111");
        addressDo3.setReceiveCompany("222");
        addressDo3.setReceivePhone("333");
        addressDo3.setReceiveUserName("444");
        addressDo3.setRemark("555");

        List<UsReceiveAddressDo> addressDos = Arrays.asList(addressDo,addressDo2,addressDo3);
        List<ReceiveAddressCustomerVO> voList = Optional.ofNullable(addressDos)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(x ->{
                    MapperFactory.getCopyByRefMapper().mapClass(UsReceiveAddressDo.class, ReceiveAddressCustomerVO.class).register();
                    return MapperFactory.getCopyByRefMapper()
                            .map(x, ReceiveAddressCustomerVO.class);
                })
                .collect(Collectors.toList());

        voList.forEach(System.err::println);
    }
}
