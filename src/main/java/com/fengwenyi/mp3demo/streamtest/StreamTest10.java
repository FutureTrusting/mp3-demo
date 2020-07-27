package com.fengwenyi.mp3demo.streamtest;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

/**
 * @author ECHO
 */
public class StreamTest10 {



    public static void main(String[] args) {
        String REJECT_RESP_TEMPLATE = "审批驳回成功%s%s%s条";
        String format = String.format(REJECT_RESP_TEMPLATE, 1,1,1);
        System.err.println(format);
        String mailNo = "SF2000148509656";
        String[] nos = mailNo.split(",");
        if (nos.length > 1) {//有子单号
            System.err.println("有子单号");
        }
        System.err.println("无子单号");
    }

}