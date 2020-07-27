package com.fengwenyi.mp3demo.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.fengwenyi.mp3demo.model.City;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wenyi Feng
 * @since 2018-08-31
 */
public interface MPCityService extends IService<City> {

    List<City> queryCityAll();

    boolean addCity(City city);

    City queryCityByName(String name);
}
