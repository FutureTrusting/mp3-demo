package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.lang.Validator;
import com.fengwenyi.mp3demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author : Caixin
 * @date 2019/9/26 10:28
 */

@RestController
public class ValidatorController {

    @Autowired
    TestService testService;

    public ValidatorController() {
        System.out.println("此时testService还未被注入: testService = " + testService);
    }

    @PostConstruct
    //@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: testService = " + testService);
    }


    public static void main(String[] args) {
        boolean isEmail = Validator.isEmail("loolly@gmail.com");
        ValidatorController controller = new ValidatorController();
    }
}
