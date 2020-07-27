package com.fengwenyi.mp3demo.ifelseinstead;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : Caixin
 * @date 2019/10/22 16:42
 */
//@Service("superVipPayServiceImpl")
@Service
public class SuperVipPayServiceImpl implements UserPayService {

    @Override
    public Money quote(BigDecimal orderPrice) {
        Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
        return cny.multipliedBy(new BigDecimal("0.80"), RoundingMode.HALF_UP);
    }

}
