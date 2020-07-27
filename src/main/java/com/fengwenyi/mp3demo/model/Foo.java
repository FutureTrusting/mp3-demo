package com.fengwenyi.mp3demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/7/3 15:26
 */

@Getter
@Setter
@ToString
public class Foo {

    String name;

    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

}
