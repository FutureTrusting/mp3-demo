package com.fengwenyi.mp3demo.streamsorted;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UserSorted;
import com.fengwenyi.mp3demo.dto.UserSortedV2;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.Comparator.comparing;

/**
 * @author : Caixin
 * @date 2019/10/23 9:39
 */
public class StreamSorted {

    public static void main(String[] args) {
        List<UserSorted> userSortedList = LongStream.rangeClosed(0, 999999)
                .boxed()
                .map(x -> new UserSorted(x, "UserSorted" + x, DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x))))
                .sorted(comparing(UserSorted::getCreateTime))
                .sorted(comparing(UserSorted::getId))
                .collect(Collectors.toList());

        // 定义一个计数器
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        stopWatch.start("任务一 parallelStream");
        List<UserSortedV2> collect1 = userSortedList.parallelStream()
                .map(y -> new UserSortedV2(y.getId(), y.getUsername(), y.getCreateTime()))
                .sequential()
                .sorted(comparing(UserSortedV2::getCreateTime))
                .sorted(comparing(UserSortedV2::getId))
                .collect(Collectors.toList());
        stopWatch.stop();

        stopWatch.start("任务二 stream");
        List<UserSortedV2> collect = userSortedList.stream()
                .map(y -> new UserSortedV2(y.getId(), y.getUsername(), y.getCreateTime()))
                .collect(Collectors.toList());
        stopWatch.stop();

        String result = stopWatch.prettyPrint();
        System.err.println(result);
    }
}
