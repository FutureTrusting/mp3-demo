package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.model.City;

import java.util.List;

public interface RedisTestService {

    List<City> getCityAll();

    boolean addCity();

    boolean updateCity();

    boolean deleteCity();
}
