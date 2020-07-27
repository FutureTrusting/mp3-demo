package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author : Caixin
 * @date 2019/10/16 10:20
 */

@Data
public class UserOrderDTO implements Serializable {
    private static final long serialVersionUID = -8199925234080538662L;

    private Long id;
    private Date createTime;
    private String createBy;
    private String createTimeDesc;


    private static void removeTempFiles(String... fileNames) {
        Arrays.stream(fileNames)
                .forEach(System.err::println);
    }

    public static void main(String[] args) {
        removeTempFiles("1", "2", "3", "4");
    }
}
