package com.fengwenyi.mp3demo.dto;

import cn.hutool.core.util.PageUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
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
public class UserSorted {

    private Long id;
    private String username;
    private Date createTime;

    public static void main(String[] args) {
        int[] startEnd1 = PageUtil.transToStartEnd(1, 20);
        int[] startEnd2 = PageUtil.transToStartEnd(2, 20);
        int[] startEnd3 = PageUtil.transToStartEnd(3, 20);
        System.err.println(new Gson().toJson(startEnd1));
        System.err.println(new Gson().toJson(startEnd2));
        System.err.println(new Gson().toJson(startEnd3));
    }

}
