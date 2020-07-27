package com.fengwenyi.mp3demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Caixin
 * @date 2019/7/3 10:32
 */

@ToString
@Getter
@Setter
public class PersonVO {

    String firstName;
    String lastName;


    public PersonVO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonVO() {

    }
}
