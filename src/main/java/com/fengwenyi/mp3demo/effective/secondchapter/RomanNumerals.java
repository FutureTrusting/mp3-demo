package com.fengwenyi.mp3demo.effective.secondchapter;

import java.util.regex.Pattern;

/**
 * @author : Caixin
 * @date 2019/10/14 17:42
 */
public class RomanNumerals {

    private static final Pattern ROMAN = Pattern.compile(
            "^1[3|4|5|6|7|8|9][0-9]\\d{8}$");

    static boolean isRomanNumerals(String phone) {
        return ROMAN.matcher(phone).matches();
    }

}
