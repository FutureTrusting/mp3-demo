package com.fengwenyi.mp3demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/6/28 17:43
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -7830678287171037075L;
    private String firstName;
    private String lastName;
    private List<String> jobTitles;
    private long salary;

    private Address address;

}


