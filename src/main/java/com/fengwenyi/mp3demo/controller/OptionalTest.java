package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.dto.Outer;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.model.AddressDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/10/10 19:31
 */
public class OptionalTest {

    public static void main(String[] args) {

        PersonDto personDto = new PersonDto();
        AddressDto addressDto = Optional.of(personDto).map(PersonDto::getAddress).orElse(null);
        System.err.println(addressDto);

        Map<Long, Long> hashMap = new HashMap<>(10);
        Long aLong = hashMap.get(null);
        System.err.println(aLong);

    }
}
