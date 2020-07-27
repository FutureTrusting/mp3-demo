package com.fengwenyi.mp3demo.dto;

import lombok.Data;

/**
 * @author : Caixin
 * @date 2019/7/15 13:41
 */

@Data
public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city){
        this.name = name;
        this.city = city;
    }

}
