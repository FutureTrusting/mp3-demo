package com.fengwenyi.mp3demo.dto;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author : Caixin
 * @date 2019/7/25 15:54
 */

@Builder
@Data
public class UserBuilderVO {
    private String id;
    private String username;
    private String password;
    private String address;



    public static void main(String[] args) {
        UserBuilderVO userVO = UserBuilderVO.builder()
                .address("address1")
                .address("address2")
                .password("password1")
                .username("username1")
                .build();
        System.err.println("new Gson().toJson(userVO)"+new Gson().toJson(userVO));
        System.err.println(JSON.toJSONString(userVO));
    }
}
