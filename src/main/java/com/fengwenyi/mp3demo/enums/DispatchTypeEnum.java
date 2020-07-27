package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Created with IDEA
 *
 * @author: WangXiaoShuai
 * Date:2019-07-23 11:29
 **/
@Getter
@AllArgsConstructor
public enum DispatchTypeEnum {
    /**
     * @since 2.0.0
     */
    DISPATCH_NULL(0, "Null"),
    DISPATCH_NOW(1, "立即派送"),
    DISPATCH_BOOKING(2, "预约派送");

    private Integer code;
    private String desc;

    public static DispatchTypeEnum find(final Integer code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
