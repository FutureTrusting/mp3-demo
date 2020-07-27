package com.fengwenyi.mp3demo.ifelseinstead;

import org.joda.money.Money;

import java.math.BigDecimal;

/**
 * @author happyGGQ
 */
public interface UserPayService {
    /**
     * 计算应付价格
     * @param orderPrice
     * @return
     */
    Money quote(BigDecimal orderPrice);
}
