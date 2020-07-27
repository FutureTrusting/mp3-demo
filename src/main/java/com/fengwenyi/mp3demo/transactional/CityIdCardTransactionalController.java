package com.fengwenyi.mp3demo.transactional;

import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Caixin
 * @date 2019/9/18 22:09
 */

@RestController
@RequestMapping("/test")
public class CityIdCardTransactionalController {

    @Autowired
    TransactionalCityService transactionalCityService;

    @Autowired
    TransactionalIdCardService transactionalIdCardService;

    @GetMapping("/transactionalV2")
    public R testCityIdCardTransactional(){
        City city = new City();
        city.setName("宜丰");
        transactionalCityService.addCityPageRows(city);
        return R.success();
    }

}
