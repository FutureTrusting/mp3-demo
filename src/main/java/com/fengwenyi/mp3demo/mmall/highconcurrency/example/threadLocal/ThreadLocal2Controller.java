package com.fengwenyi.mp3demo.mmall.highconcurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/threadLocal")
public class ThreadLocal2Controller {

    @RequestMapping("/test2")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }
}
