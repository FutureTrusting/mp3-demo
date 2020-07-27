package com.fengwenyi.mp3demo.java8.efficientrefact.dto;


import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/7/23 11:39
 */
public class Car {
    //车可能进行了保险，也可能没有保险，所以将这个字段声明为 OptionalNullPointException
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() { return insurance; }
}
