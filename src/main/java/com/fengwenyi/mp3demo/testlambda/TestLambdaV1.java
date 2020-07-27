package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UsOrderCityMailDTO;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author 01376494
 */
public class TestLambdaV1 {
    public static void main(String[] args) {
        UsOrderCityMailDTO mailDTO = new UsOrderCityMailDTO();
        String string = Optional.ofNullable(mailDTO.getSignTime())
                .map(x -> DateFormatUtils.format(x, "yyyy-MM-dd HH:mm:ss"))
                .orElse(null);
        System.err.println(string);
        Long orderNos= 1L;
        Set<Long> hashSet = Sets.newHashSet(orderNos);
        System.err.println(hashSet);
    }
}
