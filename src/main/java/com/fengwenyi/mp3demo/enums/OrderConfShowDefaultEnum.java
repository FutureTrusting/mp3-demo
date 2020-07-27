package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/11/5 11:35
 */

@Getter
@AllArgsConstructor
public enum OrderConfShowDefaultEnum {
    /**
     * 查询POS/订单号、运单号、  默认空值不显示
     * 报价、签回单、代收贷款、  默认空值不显示
     */
    custOrderNo,
    waybillNo,
    valueAdded,
    needReturnTrackingNo,
    collectionFee,
    passwordSign;

    public static void main(String[] args) {
        OrderConfShowDefaultEnum[] values = OrderConfShowDefaultEnum.values();



        List<OrderConfShowDefaultEnum> orderConfShowDefaultEnums = Arrays.asList(values);

        Arrays.asList(values).forEach(System.out::println);
    }
}
