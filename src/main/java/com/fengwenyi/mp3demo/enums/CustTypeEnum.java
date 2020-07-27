package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Caixin
 * @date 2019/3/18 19:40
 */

@Getter
@AllArgsConstructor
public enum CustTypeEnum {
    /**
     * 散客
     */
    SINGLE_CUSTOMER("1", "散客"),
    /**
     * 月结客户
     */
    MONTHLY_CUSTOMER("2", "月结客户"),
    /**
     * 供应商
     */
    SUPPLIER_CUSTOMER("4", "供应商"),
    /**
     * 司机
     */
    DRIVER_CUSTOMER("3", "司机");

    private String code;
    private String desc;

    public static CustTypeEnum findByCode(final String code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }

    public static CustTypeEnum findByDesc(final String desc) {
        return Arrays.stream(values()).filter(value -> value.getDesc().equals(desc)).findFirst().orElse(null);
    }

}
