package com.fengwenyi.mp3demo.util;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.enums.TakePartTimeEnum;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/5/30 11:37
 */
public class Resolver {

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            // 可能会抛出空指针异常，直接返回一个空的 OptionalNullPointException 对象
            return Optional.empty();
        }
    }

    public static void main(String[] args) throws ParseException {
        UsOrderDo orderDo = new UsOrderDo();
        UsOrderDo orderDo1 = new UsOrderDo();
        UsOrderDo orderDo2 = new UsOrderDo();
        UsOrderDo orderDo3 = new UsOrderDo();
        orderDo3.setTakePartType(0);

        orderDo2.setBanchNo(12234L);
        orderDo.setTakePartTime(DateUtil.parse("2019-02-01 22:33:23"));
        orderDo1.setTakePartTime(DateUtil.parse("2019-12-06 16:22:23"));
        orderDo3.setTakePartTime(DateUtil.parse("2019-01-06 16:22:23"));

        List<UsOrderDo> source = Lists.newArrayList(orderDo, orderDo1, orderDo2,orderDo3);
        Map<Boolean, List<UsOrderDo>> reviewTakePartTime = source.stream()
                .collect(Collectors.partitioningBy(x ->(Objects.isNull(x.getTakePartTime()))
                        || (TakePartTimeEnum.AN_HOUR.getCode().equals(String.valueOf(x.getTakePartType())))
                        || (LocalDateTime.now().isBefore(LocalDateTime.ofInstant(cn.hutool.core.date.DateUtil.offsetHour(x.getTakePartTime(), 1).toInstant(), ZoneId.of("Asia/Shanghai"))))));

        System.err.println(new Gson().toJson(reviewTakePartTime));
//        String dateStr = "2017-03-01 22:33:23";
//        Date date = DateUtil.parse(dateStr);
//            //结果：2017-03-03 22:33:23
//        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
//        //常用偏移，结果：2017-03-04 22:33:23
//        DateTime newDate2 = DateUtil.offsetDay(date, 3);
//
//        //常用偏移，结果：2017-03-01 19:33:23
//        DateTime newDate3 = DateUtil.offsetHour(date, 1);
//
//        System.err.println(DateUtil.formatDateTime(newDate3));




    }

}
