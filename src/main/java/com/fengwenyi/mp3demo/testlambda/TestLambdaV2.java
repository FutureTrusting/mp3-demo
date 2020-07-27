package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UsOrderCityMailDTO;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.enums.DispatchTypeEnum;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author 01376494
 */
public class TestLambdaV2 {
    public static void main(String[] args) {
        Date date = new Date(1583830015000L);
        UsOrderDo usOrderDo1 = new UsOrderDo();
//        usOrderDo1.setTakePartType(1583830015);
        Date expectTime = Optional.of(usOrderDo1)
                .map(UsOrderDo::getTakePartType)
                .map(y -> Long.valueOf(y) * 1000)
                .map(Date::new)
                .orElse(null);
        String s1 = DateUtil.format(expectTime, DatePattern.NORM_DATETIME_PATTERN);
        System.err.println(s1);

        String s = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);
        System.err.println(s);

        String dateStr = "2020-03-01 22:33:23";
        Date dateV2 = DateUtil.parse(dateStr);
        UsOrderDo usOrderDo = new UsOrderDo();
        usOrderDo.setDispatchType(1);
        //  boolean orEquals = DateUtil.offsetMinute(usOrderDo.getDispatchTime(), 30).isBeforeOrEquals(date);
        System.err.println(dateV2);
        //  System.err.println(orEquals);


        if (DispatchTypeEnum.DISPATCH_NOW.getCode().equals(usOrderDo.getDispatchType())
                && Objects.nonNull(usOrderDo.getDispatchTime())
                && DateUtil.offsetMinute(usOrderDo.getDispatchTime(), 30).isBeforeOrEquals(date)) {
            System.err.println(dateV2);
        }
    }
}
