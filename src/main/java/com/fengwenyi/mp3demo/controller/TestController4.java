package com.fengwenyi.mp3demo.controller;

import com.baidu.unbiz.easymapper.MapperFactory;
import com.baomidou.mybatisplus.extension.api.R;
import com.fengwenyi.mp3demo.annotation.UnSign;
import com.fengwenyi.mp3demo.dto.*;
import com.fengwenyi.mp3demo.enums.GenderEnum;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.request.CustTypeRequest;
import com.fengwenyi.mp3demo.service.TestService;
import com.fengwenyi.mp3demo.util.Resolver;
import com.fengwenyi.mp3demo.vo.StudentVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/4/26 11:10
 */

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController4 {

    @ApiOperation("获取列表")
    @GetMapping("/enum")
    public void getStudents(@RequestBody @Validated CustTypeRequest custTypeRequest) {
        System.err.println(custTypeRequest.getCustTypeEnum().getCode());
        System.err.println(new Gson().toJson(custTypeRequest));
    }

}
