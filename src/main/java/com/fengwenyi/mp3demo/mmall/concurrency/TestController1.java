package com.fengwenyi.mp3demo.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TestController1 {

    @RequestMapping("/test1")
    @ResponseBody
    public String test() {
        return "test";
    }
}
