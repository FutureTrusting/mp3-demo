package com.fengwenyi.mp3demo.interview;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author ECHO
 */

public class Algorithm {
    public static void main(String[] args) {
        //1���滻�ո� We Are Happy -> We%20Are%20Happy
        StringBuffer stringBuffer = new StringBuffer().append("We Are Happy");
        String space2 = replaceSpace2(stringBuffer); //We%20Are%20Happy

        //2�������ַ��������������ǰ׺�������ڣ����ؿ��ַ���"" ["flower","flow","flight"] ["dog","racecar","car"]

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
