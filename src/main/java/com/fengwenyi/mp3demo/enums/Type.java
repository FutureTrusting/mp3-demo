package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author : Caixin
 * @date 2019/7/12 16:06
 */

@Getter
@AllArgsConstructor
public enum Type {
    /**
     * FISH
     */
    FISH("1", "FISH"),
    /**
     * OTHER
     */
    OTHER("2", "OTHER"),
    /**
     * MEAT
     */
    MEAT("3", "MEAT");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Type findByCode(final String code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }

    public static Type findByDesc(final String desc) {
        return Arrays.stream(values()).filter(value -> value.getDesc().equals(desc)).findFirst().orElse(null);
    }
}
