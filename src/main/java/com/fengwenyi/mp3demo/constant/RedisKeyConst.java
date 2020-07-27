package com.fengwenyi.mp3demo.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/4/25 15:54
 */
public interface RedisKeyConst {
    /**
     * JAVA中break和continue的具体区分是什么呢?
     *
     * break 直接跳出循环 执行循环后面的语句
     * continue 是结束本次循环,但不跳出循环,继续下次循环
     */

    String REDIS_KEY_CONSTANT = "redis_key_value";


    public static void main(String[] args) {
        String code="{'k1':'021WJ','k2':'021HV','k3':'','k4':'T4','k5':'SF1011322930415','k6':'','k7':'99f2675a'}";
        JSONObject object = JSONObject.parseObject(code);
        object.put("k5", "w1");
        String newCode = "MMM=" + JSON.toJSONString(object,SerializerFeature.UseSingleQuotes);
        System.out.println(newCode);
        

        Iterable<String> stringIterable = Splitter.on(",")
                .trimResults() // 将结果中的空格删除
                .omitEmptyStrings() //移去结果中的空字符串
                .split("MEDTRONIC267929CNshh11-1231");
        List<String> stringList = Lists.newArrayList(stringIterable);
        System.out.println(stringList);


    }

}
