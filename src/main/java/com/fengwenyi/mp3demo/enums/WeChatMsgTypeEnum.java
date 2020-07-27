package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2020-01-01 10:50
 */

@Getter
@AllArgsConstructor
public enum WeChatMsgTypeEnum {
    /**
     * 审批微信通知
     */
    REVIEW_PASS("REVIEW_PASS","审批通过"),
    REVIEW_REJECT("REVIEW_REJECT","已驳回"),
    REVIEW_EXCEPTIONAL("REVIEW_EXCEPTIONAL","审批异常");

    private String code;
    private String desc;

    public static WeChatMsgTypeEnum find(final String code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        String code = WeChatMsgTypeEnum.REVIEW_PASS.getCode();
    }

}
