package com.fengwenyi.mp3demo.transactional;

import com.fengwenyi.mp3demo.model.City;

/**
 * @author : Caixin
 * @date 2019/9/18 22:10
 */
public interface TransactionalCityService {

    boolean addCityPageRows(City city);

}
