package com.fengwenyi.mp3demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Caixin
 * @date 2019/7/3 15:26
 */

@Getter
@Setter
@ToString
public class Bar {

    String name;

    public Bar(String name) {
        this.name = name;
    }

}
