package com.fengwenyi.mp3demo.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : Caixin
 * @date 2019/8/12 14:02
 */

@Controller
@Slf4j
@RequestMapping("/appBusiness")
public class AppBusinessController {

//    @Autowired
//    @Qualifier("appBusinessImpl")
//    AppBusiness appBusiness;

    @Resource(name = "appBusinessImpl")
    AppBusiness appBusinessImpl;

    @GetMapping("/test")
    @ResponseBody
    public String testAppBusiness() {
        String appService = appBusinessImpl.testAppService();
        return appService;
    }

    public static void main(String[] args) throws InterruptedException {
        // 定义一个计数器
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        stopWatch.start("任务一");
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();

        String result = stopWatch.prettyPrint();
        System.err.println(result);

        // 统计任务二耗时
        stopWatch.start("任务二");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        // 打印出耗时
        String result2 = stopWatch.prettyPrint();
        System.err.println(result2);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        integers.stream().filter(x->x==1).forEach(System.out::println);
    }

}
