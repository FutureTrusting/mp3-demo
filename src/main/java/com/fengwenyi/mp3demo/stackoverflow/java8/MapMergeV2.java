package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapMergeV2 {

    public static void main(String[] args) {
        // merge 使用旧值和给定的value来计算新值,如果新值为空,则删除key,不为空则写入并且返回.
        // 注意:如果旧值为空,也就是原有的key不存在,新值等于给定值,不会再进行计算.因此下方的测试代码3=10而不是12.
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,12);
        map.put(2,2);
        map.merge(1, 10, (v, oldV) -> v + oldV + 2);
        map.merge(3, 10, (v, oldV) -> v + oldV + 2);
        map.merge(2, 10, (v, oldV) -> null);
        System.out.println(map.toString());
        // {1=1, 2=2}
        // {1=13, 3=10}
        map.merge(1,10,(oldV,newV)->{
            //key命中oldV为原map值
            System.err.println(oldV);
            //key命中newV为新merge的map值
            System.err.println(newV);
            return 10;
        });
        System.err.println(new Gson().toJson(map));
        Map<String, Integer> m1 = ImmutableMap.of("a", 2, "b", 3);
        Map<String, Integer> m2 = ImmutableMap.of("a", 3, "c", 4);
        Map<String, Integer> mx = Stream.of(m1, m2)
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                Integer::max         // such that if a value already exist for
                                // a given key, the max of the old
                                // and new value is taken
                        )
                );
        Map<String, Integer> mx1 = new HashMap<>(m1);
        m2.forEach((k, v) -> mx1.merge(k, v, Integer::max));
        System.err.println(new Gson().toJson(mx1));
        //{"a":3,"b":3,"c":4}
    }
}
