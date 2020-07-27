package com.fengwenyi.mp3demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/5/23 18:45
 */

@Getter
@Setter
public class UserDto {

    /** 用户编号 */
    private long id;

    private String name;

    private Integer age;

    private Optional<Long> phone;

    private Optional<String> email;

    public UserDto() {

    }

    public UserDto(String name, Integer age,Optional<Long> phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

}
