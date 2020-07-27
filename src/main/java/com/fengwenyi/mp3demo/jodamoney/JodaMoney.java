package com.fengwenyi.mp3demo.jodamoney;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : Caixin
 * @date 2019/10/22 16:35
 */
public class JodaMoney {

    public static void main(String[] args) {
        //人名币币种最大支持的小数为两位
        Money cnyMoney = Money.of(CurrencyUnit.of("CNY"), new BigDecimal("69.65"));
        Money usdMoney = Money.parse("USD 23.87");
        //日元没有小数
        Money jpyMoney = Money.parse("JPY 187");
        BigDecimal amount1 = cnyMoney.getAmount();
        BigDecimal amount2 = usdMoney.getAmount();
        BigDecimal amount3 = jpyMoney.getAmount();
//        System.err.println(amount1);
//        System.err.println(amount2);
//        System.err.println(amount3);

        //通过使用Money类plus和minus实现加减,进行运算的Money对象必须同一币种
        Money cnyMoney2 = Money.of(CurrencyUnit.of("CNY"), new BigDecimal("69.65"));
        Money cnyMoney3 = Money.of(CurrencyUnit.of("CNY"), new BigDecimal("69.65"));
        Money cnyMoney4 = Money.of(CurrencyUnit.of("CNY"), new BigDecimal("69.65"));
        Money plus23 = cnyMoney2.plus(cnyMoney3);
        Money minus43 = cnyMoney4.minus(cnyMoney3);

//        System.err.println(plus23.getAmount());
//        System.err.println(minus43.getAmount());

        //Money对象的乘除方法，以及超出小数部分的处理 RoundingMode.DOWN:即直接删除超出精度的部分
        Money cny1 = Money.of(CurrencyUnit.of("CNY"), new BigDecimal("54.22"));
        BigDecimal decimal = new BigDecimal("323.00");
        Money multipliedBy1 = cny1.multipliedBy(decimal, RoundingMode.DOWN);
        Money dividedBy2 = cny1.dividedBy(decimal, RoundingMode.DOWN);

//        System.err.println(multipliedBy1);
//        System.err.println(dividedBy2);

        //币种通过汇率进行转换，这里小数处理为四舍五入
        Money usdMoney1 = Money.parse("USD 23.87");
        //指定汇率
        BigDecimal decimal1 = new BigDecimal("7.2372");
        CurrencyUnit cnyUnit = CurrencyUnit.of("CNY");
        Money convertedTo = usdMoney1.convertedTo(cnyUnit, decimal1, RoundingMode.HALF_UP);
        BigDecimal convertedToAmount = convertedTo.getAmount();

//       System.err.println(convertedToAmount);

        //通过Money.parse创建一个Money对象
        Money cnyMoney88 = Money.parse("CNY 123.88");
        //第一种存库方式,将Money对应的BigDecimal取出来，存库
//        BigDecimal money88Amount = cnyMoney88.getAmount();
//        System.err.println(money88Amount);
        //第二种存库方式,将Money对象所表示的货币值，转换为该货币最小单位的整数值，存库，不会丢失精度问题
        long minorLong = cnyMoney88.getAmountMinorLong();
        Money ofMinor = Money.ofMinor(CurrencyUnit.of("CNY"), minorLong);
        System.err.println(minorLong);
        System.err.println(ofMinor.getAmount());

    }
}
