package com.fengwenyi.mp3demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Caixin
 * @date 2019/7/1 15:54
 */

@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 3111467505766144185L;

    private String province;
    private String city;
    private String country;

    public Address(String province, String city, String country) {
        this.province = province;
        this.city = city;
        this.country = country;
    }
}
