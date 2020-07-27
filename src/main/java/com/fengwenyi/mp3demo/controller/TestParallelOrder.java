package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.Dish;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.dto.UserOrder;
import com.fengwenyi.mp3demo.dto.UserOrderDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * @author : Caixin
 * @date 2019/10/16 10:19
 */
public class TestParallelOrder {



    public static void main(String[] args) {
        List<UserOrder> userOrders = LongStream.rangeClosed(0, 999998)
                .boxed()
                .map(x -> {
                    UserOrder userOrder = new UserOrder();
                    userOrder.setId(x);
                    userOrder.setCreateTime(DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x)));
                    return userOrder;
                })
                .collect(Collectors.toList());
//        userOrders.forEach(System.err::println);

        userOrders.parallelStream()
                .map(x -> {
                    UserOrderDTO orderDTO = new UserOrderDTO();
                    orderDTO.setCreateBy(x.getId().toString());
                    orderDTO.setId(x.getId());
                    orderDTO.setCreateTime(x.getCreateTime());
                    return orderDTO;
                })
                .sequential()
                .sorted(comparing(UserOrderDTO::getCreateTime).reversed())
                .forEach(System.err::println);
    }


    public static String getSenderPersonAddressFromOrder(UsOrderDo order) {
        String senderPersonAddress = order.getSenderPersonAddress();
        //兼容老数据  senderPersonDetailAddress为新增的字段 可能为空
        if (StringUtils.isBlank(order.getSenderPersonDetailAddress())) {
            senderPersonAddress = Stream.of(order.getSenderPersonProvince(), order.getSenderPersonCounty(), order.getSenderPersonCity(), order.getSenderPersonAddress())
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining());
        }

        return senderPersonAddress;
    }
}
