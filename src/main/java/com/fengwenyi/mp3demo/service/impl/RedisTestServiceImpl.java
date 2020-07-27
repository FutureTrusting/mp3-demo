package com.fengwenyi.mp3demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fengwenyi.mp3demo.dao.CityDao;
import com.fengwenyi.mp3demo.exception.BusinessException;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.rediskey.biz.RedisKey;
import com.fengwenyi.mp3demo.service.RedisService;
import com.fengwenyi.mp3demo.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/7/4 18:12
 */

@Service
@Slf4j
public class RedisTestServiceImpl implements RedisTestService {

    @Autowired
    CityDao cityDao;

    @Autowired
    RedisService redisService;

    @Override
    public List<City> getCityAll() {
        return cityDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCity() {
        City city = new City();
        city.setId(IdWorker.getId());
        city.setName("caixin");
        String keyPrefix = RedisKey.getRedisConstantKey.getPrefix();
        try {
            cityDao.insert(city);
            if (redisService.hasKey(keyPrefix)) {
                redisService.leftPush(keyPrefix, city);
            }
            int i = 12 / 0;
            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
            if (redisService.hasKey(keyPrefix)) {
                redisService.listRemove(keyPrefix, -1, city);
            }
            throw new BusinessException("111");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCity() {
        City city = new City();
        city.setId(10L);
        city.setName("杭州10");

        City select = cityDao.selectById(10L);
        String keyPrefix = RedisKey.getRedisConstantKey.getPrefix();
        try {
            cityDao.updateById(city);
            if (redisService.hasKey(keyPrefix)) {
                //count=0 删除所有符合条件的value
                redisService.listRemove(keyPrefix, 0, select);
                redisService.leftPush(keyPrefix, city);
            }
        } catch (Exception e) {
            if (redisService.hasKey(keyPrefix)) {
                redisService.leftPush(keyPrefix, select);
                redisService.listRemove(keyPrefix, -1, city);
            }
            e.printStackTrace();
            throw new BusinessException("111");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCity() {
        City select = cityDao.selectById(10L);
        String keyPrefix = RedisKey.getRedisConstantKey.getPrefix();
        try {
            cityDao.deleteById(select);
            if (redisService.hasKey(keyPrefix)) {
                redisService.listRemove(keyPrefix, -1, select);
            }
            int i = 12 / 0;
            System.out.println(i);

        } catch (Exception e) {
            if (redisService.hasKey(keyPrefix)) {
                redisService.leftPush(keyPrefix, select);
            }
            e.printStackTrace();
            throw new BusinessException("111");
        }
        return true;
    }
}
