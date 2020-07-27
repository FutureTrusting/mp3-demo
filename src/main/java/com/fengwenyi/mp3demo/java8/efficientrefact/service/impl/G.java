package com.fengwenyi.mp3demo.java8.efficientrefact.service.impl;

import com.fengwenyi.mp3demo.java8.efficientrefact.service.E;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.F;

/**
 * @author : Caixin
 * @date 2019/7/22 18:14
 */
public class G implements E, F {

    //类 G 无法判断 E 或者 F 到底哪一个更加具体。这就是类 G 无法通过编译的原因

    @Override
    public Integer getNumber() {
        return null;
    }
}
