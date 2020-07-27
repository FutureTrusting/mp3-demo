package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.util.*;

/**
 * 注意：addAll空指针异常
 *
 * @author : Caixin
 * @date 2019/10/16 16:24
 */
@Data
public class UserOrderVO {

    private Long id;
    private String senderPersonProvince;
    private String senderPersonCounty;
    private String senderPersonCity;
    private String senderPersonAddress;
    private Date createTime;

    private String senderPersonDetailAddress;

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();


        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        List<Integer> integerList2 = null;
        integerList.addAll(integerList2);

        integerList.forEach(x-> Objects.equals(x, 1));

    }
}
