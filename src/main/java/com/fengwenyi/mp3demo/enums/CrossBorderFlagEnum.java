package com.fengwenyi.mp3demo.enums;

import com.fengwenyi.mp3demo.dto.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @author: LiuRenjin
 * @create: 2019-08-21 18:02
 **/

@Getter
@AllArgsConstructor
public enum CrossBorderFlagEnum {
    /**
     * CrossBorderFlagEnum
     */
    N("N", false),
    Y("Y", true);

    private String value;
    private Boolean flag;

    public static CrossBorderFlagEnum findByFlag(final Boolean flag) {
        return Arrays.stream(values()).filter(value -> value.getFlag().equals(flag)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        UserVO userVO1 = new UserVO();
        UserVO userVO2 = new UserVO();
        UserVO userVO3 = new UserVO();
        List<UserVO> userVOS = Arrays.asList(userVO1, userVO2, userVO3);
//        Optional.ofNullable(userVOS)
//                .ifPresent(x->{
//                });
    }
}
