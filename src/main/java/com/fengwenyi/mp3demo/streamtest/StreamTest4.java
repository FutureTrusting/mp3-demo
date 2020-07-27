package com.fengwenyi.mp3demo.streamtest;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.OrderReviewExtendDo;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ECHO
 */
public class StreamTest4 {

    public static void main(String[] args) {
        BigDecimal cashCard = new BigDecimal("2000.000");
        BigDecimal cashCard2 = new BigDecimal("2000000000000000000000000000000000000000.000");
        BigDecimal cashCard3 = new BigDecimal("2000000000000000000000000000000000000000.000");
        String cardValue = cashCard.stripTrailingZeros().toPlainString();
        String cardValue2 = cashCard2.stripTrailingZeros().toString();
        String cardValue3 = cashCard2.stripTrailingZeros().toPlainString();
        System.err.println(cardValue);
        System.err.println(cardValue2);
        System.err.println(cardValue3);
    }
}
