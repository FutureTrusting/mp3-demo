package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author : Caixin
 * @date 2019/10/22 15:36
 */

@Getter
@AllArgsConstructor
public enum BuyerTypeEnum {
    /**
     * 详情见UserPayServiceTest
     */
    EXCLUSIVE_VIP("1", "专属会员", "particularlyVipPayServiceImpl"),
    SUPER_VIP("2", "超级会员", "superVipPayServiceImpl"),
    ORDINARY_VIP("3", "普通会员", "vipPayServiceImpl");

    private String code;
    private String desc;
    private String codeImpl;

    public static String findByCode(final String code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .map(BuyerTypeEnum::getCodeImpl)
                .orElse(null);
    }

}
