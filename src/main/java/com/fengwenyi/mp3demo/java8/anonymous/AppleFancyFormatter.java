package com.fengwenyi.mp3demo.java8.anonymous;

import com.fengwenyi.mp3demo.dto.Apple;
import org.springframework.stereotype.Service;

/**
 * @author : Caixin
 * @date 2019/7/10 13:40
 */

@Service
public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" :
                "light";
        return "A " + characteristic +" " + apple.getColor() + " apple";
    }
}
