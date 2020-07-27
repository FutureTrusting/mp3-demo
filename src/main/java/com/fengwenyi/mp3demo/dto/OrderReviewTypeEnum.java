package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description: 订单审核类型枚举
 * @author: zhengfei
 * @param:
 * @date: 2019-01-10 17:35
 */

@Getter
@AllArgsConstructor
public enum OrderReviewTypeEnum {

    NEED_REVIEW(1, "需要审核"),
    NOT_REVIEW(2, "不审核");

    private Integer code;
    private String desc;

    public static OrderReviewTypeEnum find(Integer code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        OrderReviewTypeEnum orderReviewTypeEnum = OrderReviewTypeEnum.find(null);
        System.out.println(orderReviewTypeEnum);

    }

}
