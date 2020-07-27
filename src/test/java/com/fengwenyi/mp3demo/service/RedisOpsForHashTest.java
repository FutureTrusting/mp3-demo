package com.fengwenyi.mp3demo.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.config.BspDeliverTmResponse;
import com.fengwenyi.mp3demo.constant.RedisKey;
import com.fengwenyi.mp3demo.dto.*;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/10/15 16:33
 */
@Component
@Slf4j
public class RedisOpsForHashTest extends Mp3DemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedisOpsForHash() {
        Long orgId = 1111L;
        CalcTimePromiseFreightRequest request = new CalcTimePromiseFreightRequest();
        request.setSrcAddress("上海市青浦区沪青平公路2888号");
        request.setDestAddress("上海市浦东新区申迪北路753号");
//        request.setExpressType("3");
       request.setExpressType("15");
        String unicodeAddress = Joiner.on(":").skipNulls().join(RedisKey.GIS_ADDR_DELIVER_TM_CACHE_KEY, orgId, UnicodeUtil.toUnicode(request.getSrcAddress()), UnicodeUtil.toUnicode(request.getDestAddress()));
        System.err.println(unicodeAddress);
        if (redisTemplate.hasKey(unicodeAddress)) {
            if (StrUtil.isNotBlank(request.getExpressType()) && redisTemplate.opsForHash().hasKey(unicodeAddress, request.getExpressType())) {
                String promiseFreightVO = (String) redisTemplate.opsForHash().get(unicodeAddress, request.getExpressType());
                CalcTimePromiseFreightVO orderReviewDTO = JSON.parseObject(promiseFreightVO, CalcTimePromiseFreightVO.class);
                log.warn("from redis map no key orderReviewDTO:{}", new Gson().toJson(orderReviewDTO));
            }
        }
        BspDeliverTmResponse response = new BspDeliverTmResponse();
        BspDeliverTmResponse.DeliverTm deliverTm1 = new BspDeliverTmResponse.DeliverTm();
        BspDeliverTmResponse.DeliverTm deliverTm2 = new BspDeliverTmResponse.DeliverTm();
        BspDeliverTmResponse.DeliverTm deliverTm3 = new BspDeliverTmResponse.DeliverTm();
        BspDeliverTmResponse.DeliverTm deliverTm4 = new BspDeliverTmResponse.DeliverTm();
        BspDeliverTmResponse.DeliverTm deliverTm5 = new BspDeliverTmResponse.DeliverTm();
        deliverTm1.setBusinessType("3");
        deliverTm2.setBusinessType("1");
        deliverTm3.setBusinessType("7");
        deliverTm4.setBusinessType("15");
        deliverTm5.setBusinessType("16");
        deliverTm1.setBusinessTypeDesc("电商特惠");
        deliverTm2.setBusinessTypeDesc("标准快递");
        deliverTm3.setBusinessTypeDesc("电商速配");
        deliverTm4.setBusinessTypeDesc("生鲜速配");
        deliverTm5.setBusinessTypeDesc("生鲜速配22");

        deliverTm1.setDeliverTime("2019-10-16 12:00:00,2019-10-16 12:00:00");
        deliverTm2.setDeliverTime("2019-10-16 13:00:00,2019-10-16 12:00:00");
        deliverTm3.setDeliverTime("2019-10-16 14:00:00,2019-10-16 12:00:00");
        deliverTm4.setDeliverTime("2019-10-16 15:00:00,2019-10-16 12:00:00");
        deliverTm5.setDeliverTime("2019-10-16 16:00:00,2019-10-16 12:00:00");

        deliverTm1.setPrice(13.0D);
        deliverTm2.setPrice(14.0D);
        deliverTm3.setPrice(15.0D);
        deliverTm4.setPrice(16.0D);
        deliverTm5.setPrice(17.0D);

//      response.setDeliverTms(Arrays.asList(deliverTm1, deliverTm2, deliverTm3, deliverTm4,deliverTm5));
     response.setDeliverTms(Arrays.asList(deliverTm1, deliverTm2));
//       response.setDeliverTms(Arrays.asList(deliverTm4,deliverTm5));

        List<BspDeliverTmResponse.DeliverTm> deliverTms = response.getDeliverTms();
        Map<String, String> deliverTmsMap = deliverTms.stream()
                .collect(Collectors.toMap(BspDeliverTmResponse.DeliverTm::getBusinessType, x -> {
                    CalcTimePromiseFreightVO build = CalcTimePromiseFreightVO.builder()
                            .expressType(StrUtil.isBlank(x.getBusinessType()) ? "" : x.getBusinessType())
                            .expressTypeDesc(StrUtil.isBlank(x.getBusinessTypeDesc()) ? "" : x.getBusinessTypeDesc())
                            .timeStandard(StrUtil.isBlank(x.getDeliverTime()) ? "" : x.getDeliverTime().split(",")[0])
                            .freight(String.valueOf(x.getPrice()))
                            .build();
                    return JSON.toJSONString(build);
                }, (oldData, newData) -> newData));

        if (CollectionUtil.isNotEmpty(deliverTmsMap)) {
            redisTemplate.opsForHash().putAll(unicodeAddress, deliverTmsMap);
            redisTemplate.expire(unicodeAddress, 3, TimeUnit.MINUTES);
        }

        String resultString = deliverTmsMap.get(request.getExpressType());
        BspDeliverTmResponse.DeliverTm resultVO = JSON.parseObject(resultString, BspDeliverTmResponse.DeliverTm.class);
        System.err.println(new Gson().toJson(resultVO));

    }
}
