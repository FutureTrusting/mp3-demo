package com.fengwenyi.mp3demo.effective;

import com.fengwenyi.mp3demo.enums.CustTypeEnum;

import java.time.Instant;
import java.util.Date;
import java.util.EnumSet;

/**
 * @author : Caixin
 * @date 2019/10/14 13:59
 */
public class EffectiveJava {

    public static void main(String[] args) {

        Date date = Date.from(Instant.now());
        System.err.println(date);

//        EnumSet.of(CustTypeEnum.values());


    }
}
