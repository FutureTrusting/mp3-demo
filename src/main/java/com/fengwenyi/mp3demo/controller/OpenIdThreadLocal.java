package com.fengwenyi.mp3demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Caixin
 * @date 2019/11/13 10:46
 */

@Slf4j
@RequestMapping("/openId")
@RestController
public class OpenIdThreadLocal {

    private static ThreadLocal<String> connThreadLocal = new ThreadLocal<>();

    @GetMapping("/test")
    public void testOpenIdThreadLocal() {
        connThreadLocal.set("11");
    }

    @GetMapping("/test1")
    public void testOpenIdThreadLocalV2() {
        String s = connThreadLocal.get();
        System.err.println(s);
    }
}
