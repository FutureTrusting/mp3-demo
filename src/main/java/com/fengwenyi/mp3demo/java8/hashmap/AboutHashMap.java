package com.fengwenyi.mp3demo.java8.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Caixin
 * @date 2019/10/17 10:38
 */
public class AboutHashMap {

    public static void main(String[] args) {
        Map<Integer, Integer> hashMap = new HashMap<>(16);
        hashMap.put(1,11);
        hashMap.put(2,22);
        hashMap.put(3,33);
        hashMap.put(4,44);
        hashMap.put(5,55);
//        hashMap.forEach((k,v)->{
//            System.err.println("k===>>>"+k+"  v===>>>"+v);
//        });

        //这可以说是最常用的方法了吧,获取指定key的value,当key不存在的时候返回一个默认值,也就是第二个参数.
        Integer aDefault = hashMap.getOrDefault(9, 998);
//        System.err.println(aDefault); //998

        //将所有value替换成给定lambda的计算结果,lambda的作用为根据key和value算出新的value.
//        hashMap.replaceAll((k,v)->v*10);
//        hashMap.forEach((k,v)-> System.err.println("k===>>>"+k+"  v===>>>"+v));

        //只能返回replace value值
        hashMap.replaceAll((k,v)->k+10);
        hashMap.forEach((k,v)-> System.err.println("k===>>>"+k+"  v===>>>"+v));

        //当key不存在的时候,写入新值.始终返回执行操作后的新值
        hashMap.putIfAbsent(1,111);
        hashMap.putIfAbsent(6,66);
//        hashMap.forEach((k,v)->{
//            System.err.println("k===>>>"+k+"  v===>>>"+v);
//        });

        //如果给定的key在map中的value与给定值相等,则移除并且返回true,否则返回false.
        boolean remove = hashMap.remove(6, 66);
        //当key在map中的value与给定的oldValue相等,则用newValue替换掉并且返回true,否则返回false.
        boolean replace = hashMap.replace(5, 55, 555);
        //当key存在,就替换掉并且返回新值,否则返回null.
        Integer replace1 = hashMap.replace(5, 5555);
        //如果key不存在,则使用lambda计算并写入新值.永远返回执行操作后的新值.(可以存在,不做任何操作);放回计算的新值.
//        Integer computeIfAbsent = hashMap.computeIfAbsent(1, k -> k * 10);
//        System.err.println(computeIfAbsent);

        Map<Integer, Integer> test = new HashMap<>();
        test.put(1, 1);
        test.put(2, 2);
        System.out.println(test.toString());

        // 1 存在,不做任何操作
        test.computeIfAbsent(1, key -> key + 2);
        // 3 不存在,将3 +2 = 5.
        test.computeIfAbsent(3, key -> key + 2);
        System.out.println(test.toString());
//        {1=1, 2=2}
//        {1=1, 2=2, 3=5}

        Integer computeIfAbsent = hashMap.computeIfAbsent(10, k -> k * 10);
        System.err.println(computeIfAbsent);

        //当key存在时,计算新值,如果新值不为空,则将新值写入,如果新值为空,则移除掉此key.返回新值或者null.

    }















}
