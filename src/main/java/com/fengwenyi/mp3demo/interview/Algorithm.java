package com.fengwenyi.mp3demo.interview;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author ECHO
 */

public class Algorithm {
    public static void main(String[] args) {
        //1、替换空格 We Are Happy -> We%20Are%20Happy
        StringBuffer stringBuffer = new StringBuffer().append("We Are Happy");
        String space2 = replaceSpace2(stringBuffer); //We%20Are%20Happy

        //2、查找字符串数组中最常公共前缀，不存在，返回空字符串"" ["flower","flow","flight"] ["dog","racecar","car"]

    }

    public static String replaceSpace2(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }

    public static String replaceSpace(String[] str) {
        if(str!=null && str.length>0 ){
            Arrays.stream(str).sorted().collect(Collectors.toList())
                    .forEach(x->{

                    });
        }
        return "";
    }
}
