package com.fengwenyi.mp3demo.java8.hashmap;

import com.google.common.collect.*;

import java.util.Map;
import java.util.Set;

/**
 * @author : Caixin
 * @date 2019/10/17 13:40
 */
public class GuavaCollections {

    public static void main(String[] args) {
//        Multiset<String> s = HashMultiset.create();
//        s.add("pf");
//        s.add("pf");
//        s.add("pf");
//        s.add("hh");
//        // i =3
//        int i = s.count("pf");
//        System.err.println(i);
//        System.err.println(s);

//        BiMap<String, String> m = HashBiMap.create();
//        m.put("pf", "111");
//        String value = m.get("pf");
//        String key = m.inverse().get("111");
//        System.err.println(value);
//        System.err.println(key);

        Table<Integer, Integer, String> tt = HashBasedTable.create();
        tt.put(1, 2, "huyan");

        String name = tt.get(1, 2);//huyan
        Map<Integer, String> row = tt.row(1);
        System.err.println(row.toString());
        Map<Integer, String> colum = tt.column(1);
        System.err.println(colum.toString());
        Set<Table.Cell<Integer, Integer, String>> ha = tt.cellSet();
        System.err.println(ha.toString());



    }
}
