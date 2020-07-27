package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author : Caixin
 * @date 2019/7/12 16:23
 */
public class FilterMapToObj {

    //    元素序列——就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序
    //    值。因为集合是数据结构，所以它的主要目的是以特定的时间/空间复杂度存储和访问元
    //    素（如 ArrayList 与  LinkedList ）。但流的目的在于表达计算，比如你前面见到的
    //    filter 、 sorted 和 map 。集合讲的是数据，流讲的是计算。我们会在后面几节中详细解释这个思想
    //    源——流会使用一个提供数据的源，如集合、数组或输入/输出资源。 请注意，从有序集
    //    合生成流时会保留原有的顺序。由列表生成的流，其元素顺序与列表一致。
    //    数据处理操作——流的数据处理功能支持类似于数据库的操作，以及函数式编程语言中
    //    的常用操作，如 filter 、 map 、 reduce 、 find 、 match 、 sort 等。流操作可以顺序执
    //    行，也可并行执行。
    //    流水线——很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大
    //    的流水线。这让我们下一章中的一些优化成为可能，如延迟和短路。流水线的操作可以
    //    看作对数据源进行数据库式查询。
    //    内部迭代——与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的。

    public static void main(String[] args) {
        List<Dish> menues = Arrays.asList(
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        List<String> threeHighCaloricDishNames = menues
                //从 menu 获得流（菜肴列表）
                .stream()
                //建立操作流水线：首先选出高热量的菜肴
                .filter(c -> c.getCalories() > 300)
                //获取菜名
                .map(Dish::getName)
                //只选择 头三个
                .limit(3)
                //将结果保存在另一个 List 中
                .collect(toList());
        //结果是 [pork, beef,chicken]
        System.out.println(threeHighCaloricDishNames);

        //和迭代器类似，流只能遍历一次 所以要记得，流只能消费一次！
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        //打印标题中的每个单词
        s.forEach(System.out::println);
        //java.lang.IllegalStateException:流已被操作或关闭
        //s.forEach(System.out::println);


        //外部迭代与内部迭代
        //使用 Collection 接口需要用户去做迭代（比如用 for-each ），这称为外部迭代

        //代码清单4-1 集合：用 for-each 循环外部迭代
        List<String> names = new ArrayList<>();
        for (Dish d : menues) {
            names.add(d.getName());
        }

        //代码清单4-2 集合：用背后的迭代器做外部迭代
        List<String> names2 = new ArrayList<>();
        Iterator<Dish> iterator = menues.iterator();
        //显式迭代
        while (iterator.hasNext()) {
            Dish d = iterator.next();
            names.add(d.getName());
        }

        //代码清单4-3 流：内部迭代
        List<String> names3 = menues.stream()
                //用 getName 方 法参数化 map ，提取菜名
                .map(Dish::getName)
                //开始执行操作流水线；没有迭代！
                .collect(toList());
        System.out.println(names3);
    }
}
