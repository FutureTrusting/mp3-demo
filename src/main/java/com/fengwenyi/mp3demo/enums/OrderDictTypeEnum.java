package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-01-11 13:48
 */

@Getter
@AllArgsConstructor
public enum OrderDictTypeEnum {

    PAY_TYPE("payType","付款方式"),
    EXPRESS_TYPE("expressType","产品类型"),
    GOODS("goods","物品"),
    COST_TYPE("costType","成本类型"),
    PICK_TIME("pickTime","取件时间类型"),
    VALUE_ADDED("valueAdded","增值服务"),
    NUM("num","件数"),
    REMARK("remark","备注"),
    ;

    private String code;

    private String desc;



    public static OrderDictTypeEnum find(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(OrderDictTypeEnum.values())
                .filter(v -> v.getCode().toUpperCase().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
