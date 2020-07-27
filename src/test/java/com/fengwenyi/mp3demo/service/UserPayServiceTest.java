package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.dto.TestAction;
import com.fengwenyi.mp3demo.enums.BuyerTypeEnum;
import com.fengwenyi.mp3demo.ifelseinstead.UserPayService;
import com.google.gson.Gson;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author : Caixin
 * @date 2019/10/22 16:56
 */
@Component
public class UserPayServiceTest extends Mp3DemoApplicationTests {

    @Autowired
    private Map<String, UserPayService> userPayService;

    @Test
    public void testUserPayService() {
        String buyerTypeCode = "1";
        String price = "91.98";
        UserPayService payService = this.userPayService.get(BuyerTypeEnum.findByCode(buyerTypeCode));
        Money money = payService.quote(new BigDecimal(price));
        System.err.println(new Gson().toJson(money));
    }

}
