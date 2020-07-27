package com.fengwenyi.mp3demo.testlambda;

import com.fengwenyi.mp3demo.dto.ChargePriceList;
import com.fengwenyi.mp3demo.dto.PrecreateOrderRes;
import com.fengwenyi.mp3demo.util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author 01376494
 */
public class TestLambdaV3 {
    public static void main(String[] args) {
        PrecreateOrderRes orderRes = new PrecreateOrderRes();
        BigDecimal decimal = Optional.ofNullable(orderRes.getChargePriceList())
                .map(ChargePriceList::getShopPayPrice)
                .map(String::valueOf)
                .map(y -> BigDecimalUtil.divideOneHundred(new BigDecimal(y)))
                .orElse(null);
//        System.err.println(decimal);

        BigDecimal bd = new BigDecimal("0");
        BigDecimal decimal1 = multiplyOneThousand(bd);
        Integer intValue = decimal1.setScale(0, BigDecimal.ROUND_DOWN).intValue();
        System.err.println(intValue);
    }

    public static BigDecimal multiplyOneThousand(BigDecimal v1){
        if(v1 == null){
            v1 = new BigDecimal("0");
        }
        return v1.multiply(new BigDecimal("1000")).stripTrailingZeros();
    }
}
