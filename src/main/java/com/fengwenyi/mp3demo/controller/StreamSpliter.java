package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.dto.BspOrderBatchDownVO;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/10/11 16:11
 */
public class StreamSpliter {

    public static void main(String[] args) {
        UserVO orderDo1 = new UserVO("1","Cai1","pwd1","address1");
        UserVO orderDo2 = new UserVO("2","Cai2","pwd2","address2");
        UserVO orderDo3 = new UserVO("3","Cai3","pwd3","address3");

//        List<BspOrderBatchDownVO> batchDownVOS = Arrays.asList(new BspOrderBatchDownVO(orderDo1, Boolean.TRUE),
//                new BspOrderBatchDownVO(orderDo2, Boolean.TRUE),
//                new BspOrderBatchDownVO(orderDo3, Boolean.FALSE));
//
//        Stream<BspOrderBatchDownVO> batchDownVOStream = batchDownVOS.stream()
//                .filter(x -> x.getReviewValidFlag().equals(Boolean.FALSE));
//
//        List<UserVO> userVoList = batchDownVOStream
//                .map(BspOrderBatchDownVO::getUserVO)
//                .collect(Collectors.toList());
//        System.out.println(new Gson().toJson(userVoList));


//        String format = DateFormatUtils.format(new Date(), "yyyy.MM.dd HH:mm");
//        System.err.println(format);
    }
}
