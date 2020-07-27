package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/7/25 15:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class User {

    private String id;
    private String username;
    private String password;
    private String address;
    private String email;

    public static void main(String[] args) {


        List<BspOrderBatchDownVO> batchDownVOS = new ArrayList<>();
        Map<Boolean, List<BspOrderBatchDownVO>> booleanListMap = batchDownVOS.stream()
                .collect(Collectors.partitioningBy(BspOrderBatchDownVO::getReviewValidFlag));
        List<UsOrderDo> usOrderDoList = booleanListMap.get(Boolean.TRUE)
                .stream()
                .map(BspOrderBatchDownVO::getUsOrderDo)
                .collect(Collectors.toList());


    }
}
