package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TakePartTimeEnum {
    AN_HOUR("0", "一个小时内"),
        ;
    private String code;
    private String desc;
}
