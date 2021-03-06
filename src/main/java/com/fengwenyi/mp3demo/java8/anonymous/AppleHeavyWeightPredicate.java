package com.fengwenyi.mp3demo.java8.anonymous;

import com.fengwenyi.mp3demo.dto.Apple;
import org.springframework.stereotype.Service;

/**
 * @author : Caixin
 * @date 2019/7/10 11:27
 */
@Service
public class AppleHeavyWeightPredicate implements ApplePredicate {

    /**
     * 仅仅选出重的苹果
     *
     * @param apple
     * @return
     */
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }

}
