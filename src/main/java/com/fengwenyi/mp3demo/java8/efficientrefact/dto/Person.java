package com.fengwenyi.mp3demo.java8.efficientrefact.dto;


        import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/7/23 11:38
 */
//代码清单10-4 使用 OptionalNullPointException 重新定义 Person / Car / Insurance 的数据模型
public class Person {
    //人可能有车，也可能没有车，因此将这个字段声明为 OptionalNullPointException
    private Optional<Car> car;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}