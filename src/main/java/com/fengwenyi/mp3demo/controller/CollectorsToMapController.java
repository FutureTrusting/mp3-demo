package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.model.Address;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/7/2 14:52
 */
@Slf4j
@Api(tags = "java 8 list.stream().collect Collectors.toMap 重复key 值处理")
public class CollectorsToMapController {

    public static void main(String[] args) {
        Address address = new Address("1", "2", "3");
        Address address2 = new Address("1", "3", "4");
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        addressList.add(address2);
        Map<String, Address> addressMap = addressList
                .stream()
                .collect(Collectors.toMap(Address::getProvince, Function.identity(),(oldData,newData)->newData));
        System.out.println(addressMap);
    }

}
