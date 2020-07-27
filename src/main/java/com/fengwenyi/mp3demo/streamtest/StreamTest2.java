package com.fengwenyi.mp3demo.streamtest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.enums.TakePartTimeEnum;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest2 {

    public static void main(String[] args) {
        String dateStr = "2019-12-09 11:48:23";
        Date date = DateUtil.parse(dateStr);
        DateTime dateTime = DateUtil.offsetHour(date, -1);
        System.err.println("取件时间"+DateUtil.format(dateTime,"yyyy-MM-dd HH:mm:ss"));

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(localDateTime);
        System.err.println("当前时间"+format);

        boolean before = LocalDateTime.now().isBefore(LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.of("Asia/Shanghai")));
        System.err.println(before);
    }
}
