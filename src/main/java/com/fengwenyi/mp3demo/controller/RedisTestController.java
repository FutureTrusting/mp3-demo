package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.rediskey.biz.RedisKey;
import com.fengwenyi.mp3demo.response.R;
import com.fengwenyi.mp3demo.service.RedisService;
import com.fengwenyi.mp3demo.service.RedisTestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/7/4 18:09
 */
@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisTestController {


    @Autowired
    RedisTestService redisTestService;

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "测试查询List")
    @GetMapping("/cityAll")
    public List<City> getCityAll() {
        String keyPrefix = RedisKey.getRedisConstantKey.getPrefix();
        if (redisService.hasKey(keyPrefix)) {
            List<City> objects = (List<City>) (Object) redisService.listRange(keyPrefix, 0, -1);
            System.out.println(objects);
            return objects;
        }
        List<Object> cityAll = (List<Object>) (Object) redisTestService.getCityAll();
        redisService.leftPushAll(keyPrefix, cityAll);
        List<City> objects = (List<City>) (Object) redisService.listRange(keyPrefix, 0, -1);
        System.out.println(objects);
        return objects;
    }


    @ApiOperation(value = "新增List")
    @GetMapping("/cityAdd")
    public R addCity() {
        boolean cityAll = redisTestService.addCity();
        System.out.println(cityAll);
        return R.success(cityAll);
    }

    @ApiOperation(value = "更新List")
    @GetMapping("/cityUpdate")
    public R updateCity() {
        boolean cityAll = redisTestService.updateCity();
        System.out.println(cityAll);
        return R.success(cityAll);
    }

    @ApiOperation(value = "删除List")
    @GetMapping("/cityDel")
    public R deleteCity() {
        boolean cityAll = redisTestService.deleteCity();
        System.out.println(cityAll);
        return R.success(cityAll);
    }


    @ApiOperation(value = "删除List")
    @GetMapping("/cityDel2")
    public R deleteCity2() {
        String keyPrefix = RedisKey.getRedisConstantKey.getPrefix();
        City city = new City();
        city.setId(1111L);
        city.setName("上海6");
        if (redisService.hasKey(keyPrefix)) {
            //count=0 删除所有符合条件的value
            redisService.listRemove(keyPrefix, 0, city);
        }
        return R.success("111");
    }


}
