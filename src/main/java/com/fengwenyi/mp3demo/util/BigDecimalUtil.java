package com.fengwenyi.mp3demo.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-04-16 14:16
 */
public class BigDecimalUtil {
    private final static Logger log = LoggerFactory.getLogger(BigDecimalUtil.class);


    public static String suffix2(BigDecimal amount) {
        if(amount == null){
            return "";
        }
        return suffix2(amount.toPlainString());

    }

    /***
     * 保留两位后缀
     * @author zhengfei
     * @date 2019-04-16 14:17
     */
    public static String suffix2(String amount) {
        if (StringUtils.isBlank(amount)) {
            return "";
        }
        try {
            String temp = amount;
            if (temp.indexOf(".") > 0) {
                if (StringUtils.split(temp, ".")[1].length() >= 2) {
                    // 只保留一位小数
                    BigDecimal b = new BigDecimal(temp);
                    double f = b.setScale(1, BigDecimal.ROUND_FLOOR).doubleValue();
                    temp = String.valueOf(f);
                }

                //防止 有0.01的存在导致返回的是0.0
                if (StringUtils.split(temp, ".")[1].length() == 1) {
                    if (Objects.equals(StringUtils.split(temp, ".")[1], "0")) {
                        //小数点后为0的时候，去除最后一个小数点
                        temp = StringUtils.split(temp, ".")[0];
                    }
                }
            }
            return temp;
        } catch (Exception e) {
            log.error("BigDecimalUtil.suffix2 error amount:{}, e:{}", amount, e);
        }
        return amount;
    }

    /***
     * 比较
     * @author zhengfei
     * @date 2019-05-17 14:30
     */
    public static int compareAmount(String v1, String v2){
        if(StringUtils.isBlank(v1)){
            v1 = "0";
        }
        if(StringUtils.isBlank(v2)){
            v2 = "0";
        }

        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);
    }

    /***
     * 比较
     * @author zhengfei
     * @date 2019-05-17 14:32
     */
    public static int compareAmount(BigDecimal v1, String v2){
        if(v1 == null){
            v1 = new BigDecimal("0");
        }
        if(StringUtils.isBlank(v2)){
            v2 = "0";
        }

        BigDecimal b2 = new BigDecimal(v2);
        return v1.compareTo(b2);
    }


    /***
     * 除以100
     * @author zhengfei
     * @date 2019-05-20 16:21
     */
    public static BigDecimal divideOneHundred(BigDecimal v1){
        if(v1 == null){
            v1 = new BigDecimal("0");
        }
        return v1.divide(new BigDecimal("100")).stripTrailingZeros();
    }



    public static void main(String[] args) {
        System.out.println(suffix2("0.1"));
        System.out.println(suffix2("0.16"));
        System.out.println(suffix2("0.111"));
        System.out.println(suffix2("0.1111"));
        System.out.println(suffix2("1.0"));
        System.out.println(suffix2("1.00"));
        System.out.println(suffix2("1.000"));

        System.out.println(suffix2("1.2200"));

        System.out.println(divideOneHundred(new BigDecimal("34589")));

    }
}
