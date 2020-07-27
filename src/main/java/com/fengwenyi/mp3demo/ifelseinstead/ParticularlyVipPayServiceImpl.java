package com.fengwenyi.mp3demo.ifelseinstead;

import com.fengwenyi.mp3demo.enums.BuyerTypeEnum;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : Caixin
 * @date 2019/10/22 16:40
 */
@Service
//@Service
public class ParticularlyVipPayServiceImpl implements UserPayService {
    private static final String DISCOUNT = "30";

    @Override
    public Money quote(BigDecimal orderPrice) {
        Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
        if (new BigDecimal(DISCOUNT).compareTo(orderPrice) < 0) {
            return cny.multipliedBy(new BigDecimal("0.70"), RoundingMode.HALF_UP);
        }
        return cny;
    }

}
