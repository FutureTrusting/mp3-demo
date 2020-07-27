package com.fengwenyi.mp3demo.controller;


import com.fengwenyi.mp3demo.annotation.WebLog;
import com.fengwenyi.mp3demo.dto.CityDataTreeResponse;
import com.fengwenyi.mp3demo.model.UsCityData;
import com.fengwenyi.mp3demo.service.MPUsCityDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Wenyi Feng
 * @since 2019-06-27
 */

@Api(tags = "Future测试")
@Slf4j
@RestController
@RequestMapping("/usCityData")
public class UsCityDataController {

    @Autowired
    MPUsCityDataService mpUsCityDataService;

    @ApiOperation("查询所以数据")
    @GetMapping("/all")
    @WebLog
    public List<CityDataTreeResponse> getUsCityDataAll() {
        List<CityDataTreeResponse> cityDataAll = mpUsCityDataService.getUsCityDataAll();
        System.out.println(cityDataAll);
        return cityDataAll;
    }

    public static void main(String[] args) {

        UsCityData cityData = new UsCityData();
        cityData.setCode("1111L");
        //会报空指针
//        String trim = cityData.getName().trim();
//        System.out.println(trim);

        //返回null,强烈使用
        String trim1 = StringUtils.trim(cityData.getName());
        System.out.println(trim1);

    }

}

