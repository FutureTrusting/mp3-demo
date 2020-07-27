package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.rediskey.biz.RedisKey;
import com.fengwenyi.mp3demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Caixin
 * @date 2019/4/26 11:10
 */

@RestController
@RequestMapping("/test")
public class TestRedisController {

    @Autowired
    private RedisService redisService;
    @Autowired
    HttpServletRequest httpServletRequest;

    public static void main(String[] args) {

        Date newDate = DateUtil.offset(new Date(), DateField.MINUTE, 20);

        System.out.println(newDate);
    }

    @GetMapping("/redis3")
    public void testRedis3(){
        String requestURI = httpServletRequest.getRequestURI();
        System.err.println(requestURI);
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
        Map<String, String> hashMap = new HashMap<>(4);
        hashMap.put(redisKey+"1","echo1");
        hashMap.put(redisKey+"2","echo2");
        hashMap.put(redisKey+"3","echo3");
         redisService.batchSet(hashMap);
        System.out.println(redisService.get(redisKey+"3"));
    }

    @GetMapping("/redis4")
    public  void testRedis4(){
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
        Map<String, String> hashMap = new HashMap<>(3);
        hashMap.put("echo","echo3");
        hashMap.put("ech2o","echo4");
        hashMap.put("ech3o","echo5");
        redisService.putAll(redisKey,hashMap);
    }

    @GetMapping("/redis5")
    public  void testRedis5(){
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
//        Map<String, String> hashMap = new HashMap<>(3);
//        hashMap.put("echo","echo3");
//        hashMap.put("ech2o","echo4");
//        hashMap.put("ech3o","echo5");
        redisService.putIfAbsent(redisKey,"echo","echo6666");
    }

    @GetMapping("/redis2")
    public  void testRedis2(){
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
        String serviceAndSet = redisService.getAndSet(redisKey, "知道呢的");
        System.out.println(serviceAndSet);
    }


    @GetMapping("/redis")
    public  void testRedis(){
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
        if (redisService.hasKey(redisKey)) {
            String json = redisService.get(redisKey);
            System.out.println(json);
        }else{
            redisService.setForTimeMS(redisKey,"知道呢的",30000);
            System.out.println(redisService.get(redisKey));
        }
    }
}
