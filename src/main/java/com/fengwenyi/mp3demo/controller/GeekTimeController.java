package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.model.Person;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : Caixin
 * @date 2019/7/8 14:06
 */
public class GeekTimeController {

    public static void main(String[] args) {

        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
        List<String> arrayList = Lists.newArrayList(split);
        System.out.println(arrayList);
        //[foo, bar, qux]

//        Iterable<String> split1 = Splitter.on(',').trimResults(CharMatcher.is('_')).split("_a,_b_ ,c__");
//        System.out.println(split1);

        Iterable<String> strings = Splitter.on(',').limit(3).split("a,b,c,d");
        System.out.println(strings);
        // 返回 "a", "b", "c,d"


//        Iterable<String> strings = Splitter.on('!').omitEmptyStrings().split("a,,c,d");
//        List<String> arrayList1 = Lists.newArrayList(strings);
//        System.out.println(arrayList1);

    }

//    public static void main(String[] args) {
//
//        Person person = new Person();
//        person.setLastName("111");
//        person.setSalary(2222222L);
//
//        String join = Joiner.on("").skipNulls().join(person.getFirstName(), person.getLastName(),person.getSalary());
//        System.out.println(join);
//
//        String[] y= new String[]{"1","2","3",null};
//        String join1 = Joiner.on(":").skipNulls().join(y);
//        System.out.println(join1);
//
//        String join2 = Joiner.on("; ").useForNull("NULL").join(y);
//        System.out.println(join2);
//
//        String join3 = Joiner.on("@").join(Arrays.asList(1, 5, 7));
//        System.out.println(join3);
//        //1@5@7
//
//        Map map = ImmutableMap.of("k1", "v1", "k2", "v2");
//        String join4 = Joiner.on("; ").withKeyValueSeparator("=").join(map);
//        System.out.println(join4);
//        //"k1=v1; k2=v2"
//
//        StringBuilder sb = new StringBuilder("result:");
//        Joiner.on(" ").appendTo(sb, 1, 2, 3);
//        System.out.println(sb);//result:1 2 3
//
//    }

}
