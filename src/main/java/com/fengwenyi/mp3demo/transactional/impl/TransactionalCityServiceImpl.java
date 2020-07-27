package com.fengwenyi.mp3demo.transactional.impl;

import com.fengwenyi.mp3demo.dao.CityDao;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.transactional.TransactionalCityService;
import com.fengwenyi.mp3demo.transactional.TransactionalIdCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Caixin
 * @date 2019/9/18 22:11
 */
@Slf4j
@Service
public class TransactionalCityServiceImpl implements TransactionalCityService {

    @Autowired
    CityDao cityDao;

    @Autowired
    TransactionalIdCardService transactionalIdCardService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCityPageRows(City city) {
        int insert = cityDao.insert(city);
        Idcard idcard = new Idcard();
        transactionalIdCardService.addIdCardPageRows(idcard);
        if(insert>0){

        }
        return insert>0;
    }
}
