package com.fengwenyi.mp3demo.util;

import cn.hutool.core.util.PageUtil;
import com.google.gson.Gson;

/**
 * @author : Caixin
 * @date 2019/10/15 15:40
 */
public class HutoolUnicodeUtil {

    public static void main(String[] args) {
        String toString = cn.hutool.core.text.UnicodeUtil.toUnicode("因为java注释也能识别unicode");
        String toString2 = cn.hutool.core.text.UnicodeUtil.toUnicode("因为java注释也能识别unicode", Boolean.FALSE);
        System.err.println(toString);
        System.err.println(toString2);
        System.err.println("=============================");
        String string = cn.hutool.core.text.UnicodeUtil.toString(toString);
        String string1 = cn.hutool.core.text.UnicodeUtil.toString(toString2);
        System.err.println(string);
        System.err.println(string1);

        int[] startEnd2 = PageUtil.transToStartEnd(10, 10);//[0, 10]
        int[] startEnd3 = PageUtil.transToStartEnd(2, 10);//[0, 10]
        int[] startEnd4 = PageUtil.transToStartEnd(3, 10);//[0, 10]
        int[] startEnd5 = PageUtil.transToStartEnd(4, 10);//[0, 10]
        System.err.println(new Gson().toJson(startEnd2));
        System.err.println(new Gson().toJson(startEnd3));
        System.err.println(new Gson().toJson(startEnd4));
        System.err.println(new Gson().toJson(startEnd5));
    }

}
