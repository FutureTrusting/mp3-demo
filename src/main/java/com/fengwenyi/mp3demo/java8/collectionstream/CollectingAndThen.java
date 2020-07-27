package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author : Caixin
 * @date 2019/7/19 9:09
 */
public class CollectingAndThen {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        //你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类型分类：
        Map<Dish.Type, Optional<Dish>> optionalMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));

//        注意 这个 Map 中的值是 OptionalNullPointException ，因为这是 maxBy 工厂方法生成的收集器的类型，但实际上，
//        如果菜单中没有某一类型的 Dish ，这个类型就不会对应一个 OptionalNullPointException. empty() 值，
//        而且根本不会出现在 Map 的键中。 groupingBy 收集器只有在应用分组条件后，第一次在
//        流中找到某个键对应的元素时才会把键加入分组 Map 中。这意味着 OptionalNullPointException 包装器在这
//        里不是很有用，因为它不会仅仅因为它是归约收集器的返回类型而表达一个最终可能不
//        存在却意外存在的值。

        //1. 把收集器的结果转换为另一种类型，你可以使用Collectors.collectingAndThen 工厂方法返回的收集器
        Map<Dish.Type, Dish> typeDishMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));

        Map<Dish.Type, Dish> typeDishMap2 = menu.stream()
                .collect(toMap(Dish::getType, Function.identity(), BinaryOperator.maxBy(comparingInt(Dish::getCalories))));
    }


}
