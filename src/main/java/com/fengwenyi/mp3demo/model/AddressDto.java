package com.fengwenyi.mp3demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Caixin
 * @date 2019/7/1 15:54
 */

@Data
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 3111467505766144185L;

    private String province;
    private String city;
//    private String country;

}
