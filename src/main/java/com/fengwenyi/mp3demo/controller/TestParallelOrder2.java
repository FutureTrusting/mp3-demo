package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.date.DateUtil;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.dto.UserOrder;
import com.fengwenyi.mp3demo.dto.UserOrderDTO;
import com.fengwenyi.mp3demo.dto.UserOrderVO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

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
public class TestParallelOrder2 {



    public static void main(String[] args) {
        List<UserOrderVO> userOrders = LongStream.rangeClosed(0, 999998)
                .boxed()
                .map(x -> {
                    UserOrderVO userOrder = new UserOrderVO();
                    userOrder.setId(x);
                    userOrder.setSenderPersonProvince("上海"+x);
                    userOrder.setSenderPersonCounty("上海"+x);
                    userOrder.setSenderPersonCity("上海"+x);
                    userOrder.setSenderPersonAddress("上海"+x);
                    userOrder.setCreateTime(DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x)));
                    return userOrder;
                })
                .collect(Collectors.toList());
//        userOrders.forEach(System.err::println);

        List<List<String>> listArrayList = userOrders.parallelStream()
                .map(x -> {
                    UserOrderVO userOrder = new UserOrderVO();
                    String addressFromOrder = getSenderPersonAddressFromOrder(x);
                    userOrder.setSenderPersonAddress(addressFromOrder);
                    userOrder.setCreateTime(x.getCreateTime());
                    userOrder.setId(x.getId());
                    return userOrder;
                })
                .sequential()
                .map(y -> {
                    Iterable<String> stringIterable = Splitter.on("上海")
                            .trimResults() // 将结果中的空格删除
                            .omitEmptyStrings() //移去结果中的空字符串
                            .split(y.getSenderPersonAddress());
                    List<String> arrayList = Lists.newArrayList(stringIterable);
                    if(arrayList.get(0).equalsIgnoreCase(arrayList.get(1))
                            && arrayList.get(0).equalsIgnoreCase(arrayList.get(2))
                            && arrayList.get(0).equalsIgnoreCase(arrayList.get(3))
                    ){
                        System.err.println("===========>>>>>");
                    }else{
                        throw new RuntimeException("当前数据不同===========>>>>>"+new Gson().toJson(arrayList));
                    }
                    return arrayList;
                }).collect(Collectors.toList());

    }


    public static String getSenderPersonAddressFromOrder(UserOrderVO order) {
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
