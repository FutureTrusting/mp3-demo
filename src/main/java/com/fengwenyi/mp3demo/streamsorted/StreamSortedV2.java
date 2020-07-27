package com.fengwenyi.mp3demo.streamsorted;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UserSorted;
import com.fengwenyi.mp3demo.dto.UserSortedV2;
import com.fengwenyi.mp3demo.dto.UserSortedV3;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.StopWatch;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Comparator.*;
import static java.util.Comparator.nullsLast;

/**
 * @author : Caixin
 * @date 2019/10/23 9:39
 */
public class StreamSortedV2 {

    public static void main(String[] args) {
        final Date date = new Date();
        final Long id = 777L;
        List<UserSortedV3> userSortedList = IntStream.rangeClosed(0, 8)
                .boxed()
                .map(x -> {
                    if (0 == x || 1 == x || 2 == x) {
                        return new UserSortedV3(Long.valueOf(x), "UserSorted" + x, null, DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
                    }
                    if (3 == x || 4 == x || 5 == x) {
                        return new UserSortedV3(id, "UserSorted" + x, x, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    }
                    return new UserSortedV3(Long.valueOf(x), "UserSorted" + x, x, DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
                })
//                .peek(System.err::println)
//                .sorted(comparing(UserSorted::getUsername, nullsFirst(String::compareTo)))
//                .sorted(comparing(UserSorted::getCreateTime),nullsFirst(String::compareTo))
//                .sorted(comparing(UserSorted::getId))
//                .sorted(comparing(UserSorted::getRangClosed))
                .collect(Collectors.toList());

        userSortedList.stream()
                .sorted(comparing(UserSortedV3::getCreateTimeFormat, nullsLast(String::compareTo))
                        .thenComparing(UserSortedV3::getId, nullsLast(Long::compareTo))
                        .thenComparing(UserSortedV3::getRangClosed, nullsLast(Integer::compareTo))
                )
                .forEach(System.err::println);


    }
}
