package com.fengwenyi.mp3demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Person;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/9/23 15:36
 */
public class JoinerTest {

    public static void main(String[] args) {


        String destRouteLabel="021WJ-021LD";
        JSONObject object3 = new JSONObject();
        if (StringUtils.isNotBlank(destRouteLabel)) {
            String[] splits = destRouteLabel.split("-");
            if (splits.length > 1) {
                object3.put("k1", splits[1]);
                object3.put("k2", splits[0]);
            } else {
                String[] splits2 = destRouteLabel.split("\\+");
                object3.put("k2", StringUtils.trim(destRouteLabel));
                if (splits2.length > 1) {
                    object3.put("k1", StringUtils.trim("+k1"));
                    object3.put("k2", StringUtils.trim("+k2"));
                }
            }
        }
        System.err.println(object3);


      PersonDto person = new PersonDto();
      person.setLastName("111");
      System.err.println(StringUtils.trim(person.getFirstName()));
//        Iterable<String> split = Splitter.on(",")
//                .trimResults() // 将结果中的空格删除
//                .omitEmptyStrings() //移去结果中的空字符串
//                .split(person.getLastName());
//        List<String> arrayList = Lists.newArrayList(split);
//        arrayList.forEach(System.err::println);
    }
}
