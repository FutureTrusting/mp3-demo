package com.fengwenyi.mp3demo.java8.anonymous;

import com.fengwenyi.mp3demo.dto.Apple;
import org.springframework.stereotype.Service;

/**
 * @author : Caixin
 * @date 2019/7/10 11:33
 */
@Service
public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}
