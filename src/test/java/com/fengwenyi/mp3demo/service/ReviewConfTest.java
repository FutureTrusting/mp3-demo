package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.ifelseinstead.UserPayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : Caixin
 * @date 2019/9/26 14:20
 */
@Component
public class ReviewConfTest extends Mp3DemoApplicationTests {

    @Autowired
    private Map<String, UserPayService> userPayService;


    @Test
    public void test02() {
        PersonDto personDto=null;
        if (null == personDto || null == personDto.getAddress()) {
            System.out.println("11111");
        }
    }

    @Test
    public void test03() {
        userPayService.forEach((k,v)->{
            System.err.println(k);
            System.err.println(v);
        });
    }
}
