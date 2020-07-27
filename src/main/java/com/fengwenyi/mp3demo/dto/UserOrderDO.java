package com.fengwenyi.mp3demo.dto;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 注意：addAll空指针异常
 *
 * @author : Caixin
 * @date 2019/10/16 16:24
 */
@Data
@AllArgsConstructor
public class UserOrderDO {

    private String id;

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        stopWatch.start("任务一parallel reduce");
        List<UserOrderDO> reduce = IntStream.rangeClosed(1, 100)
                .boxed()
                .parallel()
                .map(x -> {
                    String toString = x.toString();
                    UserOrderDO userVO1 = new UserOrderDO(toString);
                    List<UserOrderDO> orderDOS = new ArrayList<>();
                    orderDOS.add(userVO1);
                    return orderDOS;
                })
//              .sequential()
                .reduce(new ArrayList<>(), (all, item) -> {
                    all.addAll(item);
                    return all;
                });
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());

    }

}
