package com.fengwenyi.mp3demo.enums;

import com.fengwenyi.mp3demo.java8.expression.StreamUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-07-19 14:17
 */
@Getter
@AllArgsConstructor
public enum ExternalUserSouceTypeEnum {
    /**
     * 美敦力
     */
    MEDTRONIC("MEDTRONIC", "美敦力");

    private String code;
    private String desc;

    public static ExternalUserSouceTypeEnum findByCode(final String code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        ExternalUserSouceTypeEnum[] enums = ExternalUserSouceTypeEnum.values();
        Stream.of(enums).forEach(System.out::println);
    }

}
