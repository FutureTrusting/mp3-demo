package com.fengwenyi.mp3demo.ifelseinstead;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : Caixin
 * @date 2019/10/22 16:43
 */

//@Service("vipPayServiceImpl")
@Service
public class VipPayServiceImpl implements UserPayService {



    @Override
    public Money quote(BigDecimal orderPrice) {
        Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
        //该用户超级会员刚过期并且尚未使用过临时折扣
//        if (counter.getAndIncrement() > 0) {
//            //临时折扣使用次数更新
//            return cny.multipliedBy(new BigDecimal("0.80"), RoundingMode.HALF_UP);
//        }
        //8折
        return cny.multipliedBy(new BigDecimal("0.90"), RoundingMode.HALF_UP);
    }
}
