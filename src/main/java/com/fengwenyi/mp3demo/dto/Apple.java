package com.fengwenyi.mp3demo.dto;

import lombok.Data;

/**
 * @author : Caixin
 * @date 2019/7/10 11:26
 */

@Data
public class Apple {

    private String color;

    private Integer weight;

    private String country;

    public Apple() {

    }

    public Apple(String color) {
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }


    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(String color, Integer weight, String country) {
        this.color = color;
        this.weight = weight;
        this.country = country;
    }
}
