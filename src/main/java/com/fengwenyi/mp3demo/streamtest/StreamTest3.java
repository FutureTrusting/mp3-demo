package com.fengwenyi.mp3demo.streamtest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.OrderReviewExtendDo;
import com.google.common.base.Joiner;
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
public class StreamTest3 {

    public static void main(String[] args) {
        OrderReviewExtendDo extendDo1 = new OrderReviewExtendDo();
        OrderReviewExtendDo extendDo2 = new OrderReviewExtendDo();
        OrderReviewExtendDo extendDo3 = new OrderReviewExtendDo();
        extendDo1.setUpdateBy("1");
        extendDo2.setUpdateBy("11");
        extendDo3.setUpdateBy("111");

        extendDo1.setApplyUserName("2");
        extendDo2.setApplyUserName("22");
        extendDo3.setApplyUserName("222");
        List<OrderReviewExtendDo> extendDos = Lists.newArrayList(extendDo1, extendDo2, extendDo3);

        List<Long> longs = new ArrayList<>(extendDos.stream()
                .filter(x -> Objects.nonNull(x)
                        && StrUtil.isNotBlank(x.getUpdateBy())
                        && StrUtil.isNotBlank(x.getApplyUserName()))
                .collect(HashMap::new,
                        (m, c) -> {
                            m.put(Long.valueOf(c.getUpdateBy()), null);
                            m.put(Long.valueOf(c.getApplyUserName()), null);
                        }, HashMap<Long, String>::putAll)
                .keySet());
        System.err.println(longs);

        List<Long> userIds = new ArrayList<>(extendDos.stream()
                .filter(x -> Objects.nonNull(x) && StrUtil.isNotBlank(x.getCreateBy()) && StrUtil.isNotBlank(x.getApplyUserName()))
                .collect(HashMap::new,
                        (m, c) -> {
                            m.put(Long.valueOf(c.getCreateBy()), null);
                            m.put(Long.valueOf(c.getReviewUserId()), null);
                        }, HashMap<Long, String>::putAll)
                .keySet());
//        System.err.println(userIds);


        List<String> asList = Arrays.asList("1", "", "3", null, "4");
        String str=asList.stream()
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.collectingAndThen(Collectors.joining("-"), x -> x + "-"));
        System.err.println(str);

        String join = Joiner.on("").skipNulls().join("0-1297-", "81569", "-");
        System.err.println(join);
    }
}
