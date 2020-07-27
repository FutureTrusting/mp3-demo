package com.fengwenyi.mp3demo.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/9/29 14:15
 */

@Getter
@AllArgsConstructor
public enum BspOpenServerUriEnum {

    GENERAL_ORDER("/api/open/createOrder", "general_order", "通用下单"),
    TRANSFER_ORDER("/api/transfer/createOrder", "transfer_order", "调拨下单"),
    AL_ORDER("/api/al/createOrder", "al_order", "阿里下单"),
    CANCEL_ORDER("/api/cancel/order ", "cancel_order", "取消订单"),
    SUB_ORDER("/api/open/subWaybillNumber/apply", "sub_order", "子单号申请");

    private String uri;
    private String type;
    private String desc;

    public static BspOpenServerUriEnum find(final String uri) {
        return Arrays.stream(values())
                .filter(value -> value.getUri().equalsIgnoreCase(uri))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        Map<String, String> stringMap = Arrays.stream(BspOpenServerUriEnum.values())
                .collect(Collectors.toMap(BspOpenServerUriEnum::getUri,
                        BspOpenServerUriEnum::getType,
                        (left, right) -> right
                ));
    }
}
