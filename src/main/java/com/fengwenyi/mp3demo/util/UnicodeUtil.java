package com.fengwenyi.mp3demo.util;

import com.fengwenyi.mp3demo.model.Person;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * 可使用hutool UnicodeUtil.toUnicode替换toUnicode
 * 可使用hutool UnicodeUtil.toString 替换fromUnicode
 * toUnicode 方法默认跳过ASCII字符
 *
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-03-11 14:32
 */
public class UnicodeUtil {

    public static String toUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }

    public static String fromUnicode(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
    public static void main(String[] args) {
        Person person = new Person();
        person.setFirstName("CAi");
        String join = Joiner.on("").skipNulls().join(person.getLastName(), person.getAddress());
        if(StringUtils.isBlank(join)){
            join= Joiner.on("").skipNulls().join(person.getFirstName(), person.getAddress());
        }
        System.err.println(join);

        String chinese="因为java注释也能识别unicode";

        String toUnicode = UnicodeUtil.toUnicode(chinese);
        System.err.println("toUnicode>>>>>>>>"+toUnicode);
        String fromUnicode = UnicodeUtil.fromUnicode(toUnicode);
        System.err.println("fromUnicode>>>>>>"+fromUnicode);
    }
}
