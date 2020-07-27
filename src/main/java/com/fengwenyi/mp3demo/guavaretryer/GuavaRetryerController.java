package com.fengwenyi.mp3demo.guavaretryer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/7/11 9:13
 */
@Slf4j
@RestController
@RequestMapping("/guava")
public class GuavaRetryerController {

    @Autowired
    GuavaRetryerBuilder guavaRetryerBuilder;

    @GetMapping("/retry")
    public void guavaRetryerTest() {
        List<Integer> arrayList = Lists.newArrayList(1, 2, 3);
        //java 8 并行流是可以重试的
        arrayList.parallelStream().forEach(integer -> {
            guavaRetryerBuilder.uploadOdps(integer);
        });
        //注意子线程是无法重试操作
//        new Thread(()->{
//            arrayList.parallelStream().forEach(integer -> {
//                guavaRetryerBuilder.uploadOdps(integer);
//            });
//        });
    }

}
