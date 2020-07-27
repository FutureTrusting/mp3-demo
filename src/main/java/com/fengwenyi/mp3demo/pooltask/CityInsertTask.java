package com.fengwenyi.mp3demo.pooltask;

import com.fengwenyi.mp3demo.dao.CityDao;
import com.fengwenyi.mp3demo.exception.BusinessException;
import com.fengwenyi.mp3demo.model.City;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author : Caixin
 * @date 2019/5/27 10:28
 */
@Slf4j
public class CityInsertTask implements Runnable {
    private List<City> cities;
    private CityDao cityDao;
    private CountDownLatch latch;

    public CityInsertTask(List<City> cities, CityDao cityDao, CountDownLatch latch) {
        this.cities = cities;
        this.cityDao = cityDao;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            //插入用户表
            cities.forEach(u -> {
                int insertUserRole = cityDao.insert(u);
                log.warn("插入城市表====>>>>>{}", insertUserRole);
            });
        } finally {
            latch.countDown();
        }
    }
}
