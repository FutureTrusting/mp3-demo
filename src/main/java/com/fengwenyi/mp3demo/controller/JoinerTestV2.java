package com.fengwenyi.mp3demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : Caixin
 * @date 2019/9/23 15:36
 */
public class JoinerTestV2 {

    public static void main(String[] args) {

      PersonDto person = new PersonDto();
      person.setLastName("111");
      person.setFirstName(null);
      String joinProductNameMaterial = Joiner.on("/").skipNulls().join(person.getFirstName(), person.getLastName());
      System.err.println(joinProductNameMaterial);




    }
}
