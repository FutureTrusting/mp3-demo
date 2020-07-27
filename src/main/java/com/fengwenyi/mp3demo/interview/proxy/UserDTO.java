package com.fengwenyi.mp3demo.interview.proxy;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @author ECHO
 */
@Data
public class UserDTO {
    private String address;
    private Integer age;
    private String name;

    public static void main(String[] args) {
        IntStream.rangeClosed(0, 9900008)
                .boxed()
                .parallel()
                .forEach(x -> {
                    try {
//                     System.err.println(Thread.currentThread().getName() + ":" + DateUtils.parse("2013-05-24 06:02:20"));
                     System.err.println(Thread.currentThread().getName() + ":" + DateUtil.parse("2013-05-24 06:02:20"));
//                        System.err.println(Thread.currentThread().getName() + ":" + DateUtil.formatDate(new Date()));
                       System.err.println(Thread.currentThread().getName() + ":" + DateUtil.formatDate(new Date()));
//                    } catch (ParseException e) {
//                        System.err.println("有错误>>>>>>>>>>>>>>>>>>>>>>>>"+e.getMessage()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    } catch (Exception e) {
                        System.err.println("有错误>>>>>>>>>>>>>>>>>>>>>>>>"+e.getMessage()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    }
                });
    }
}