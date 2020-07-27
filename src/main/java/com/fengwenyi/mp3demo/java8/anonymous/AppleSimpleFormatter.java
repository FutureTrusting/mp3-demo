package com.fengwenyi.mp3demo.java8.anonymous;

import com.fengwenyi.mp3demo.dto.Apple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : Caixin
 * @date 2019/7/11 10:27
 */
@Slf4j
@Service
public class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }

}
