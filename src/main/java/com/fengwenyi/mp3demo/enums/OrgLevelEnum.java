package com.fengwenyi.mp3demo.enums;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;


/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-01-23 20:25
 */
@Getter
@AllArgsConstructor
public enum OrgLevelEnum {

    /**
     * 111
     */
    HEADQUARTER(1,"总部"),
    ONE(2,"一级组织"),
    TWO(3,"二级组织"),
    THREE(4,"三级组织"),
    FOUR(5,"四级组织"),
    FIVE(6,"五级组织"),
    SIX(7,"六级组织"),
    SEVEN(8,"七级组织"),
    EIGHT(9,"八级组织"),
    NINE(10,"九级组织"),
    ;

    private int code;
    private String desc;

    public static OrgLevelEnum find(Integer value) {
        if(value == null){
            return null;
        }
        return Arrays.stream(OrgLevelEnum.values())
                .filter(v -> (v.getCode() == value))
                .findFirst()
                .orElse(null);
    }



    public static void main(String[] args) {
        String organizationNumber="0-314-12";
        Iterable<String> split = Splitter.on("-")
                .trimResults() // 将结果中的空格删除
                .omitEmptyStrings() //移去结果中的空字符串
                .split(organizationNumber);
        List<String> organizationNumberList = Lists.newArrayList(split);
        String anElse = Optional.of(organizationNumberList)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .findFirst()
                .orElse(null);
        System.err.println(anElse);
        Collections.reverse(organizationNumberList);


//        List<String> list = null;
//        String s = Optional.ofNullable(list)
//                .map(Collection::stream)
//                .orElse(Stream.empty())
//                .findFirst()
//                .orElse("12223");
//        System.err.println(s);

    }
}
