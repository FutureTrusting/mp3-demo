package com.fengwenyi.mp3demo.mmall.highconcurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class Test22Controller {

    @RequestMapping("/test22")
    @ResponseBody
    public String test() {
        return "test";
    }
}
