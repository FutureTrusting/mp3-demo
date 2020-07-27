package com.fengwenyi.mp3demo.ifelseinstead;

import com.fengwenyi.mp3demo.enums.BuyerTypeEnum;
import com.google.gson.Gson;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Caixin
 * @date 2019/10/22 15:34
 */
public class IfElseInstead {

    private static ThreadLocal<AtomicInteger> threadLocalHolder = new ThreadLocal<>();

    private static final String DISCOUNT = "30";

    public static void main(String[] args) {
        Money money = calPrice(new BigDecimal("56.89"), "3");
        System.err.println(new Gson().toJson(money));
        System.err.println(threadLocalHolder.get());
    }

    private static Money calPrice(BigDecimal orderPrice, String buyerType) {
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger counter2 = new AtomicInteger(0);
        //用户是专属会员 消费大于30 70%优惠
        if (BuyerTypeEnum.EXCLUSIVE_VIP.getCode().equalsIgnoreCase(buyerType)) {
            if (new BigDecimal(DISCOUNT).compareTo(orderPrice) < 0) {
                Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
                return cny.multipliedBy(new BigDecimal("0.70"), RoundingMode.HALF_UP);
            }
        }
        //用户是超级会员 8 折价格
        if (BuyerTypeEnum.SUPER_VIP.getCode().equalsIgnoreCase(buyerType)) {
            Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
            counter.getAndIncrement();
            return cny.multipliedBy(new BigDecimal("0.80"), RoundingMode.HALF_UP);
        }
        threadLocalHolder.set(counter);
        //用户是普通会员
        Money cny = Money.of(CurrencyUnit.of("CNY"), orderPrice);
        if (BuyerTypeEnum.ORDINARY_VIP.getCode().equalsIgnoreCase(buyerType)) {
            //该用户超级会员刚过期并且尚未使用过临时折扣
            if (counter.getAndIncrement() > 0) {
                //临时折扣使用次数更新
                counter2.getAndIncrement();
                return cny.multipliedBy(new BigDecimal("0.80"), RoundingMode.HALF_UP);
            }
            //8折
            return cny.multipliedBy(new BigDecimal("0.90"), RoundingMode.HALF_UP);
        }
        //原价
        return cny;
    }

}
