package com.fengwenyi.mp3demo.java8.collectionstream;

import cn.hutool.core.util.ObjectUtil;
import com.fengwenyi.mp3demo.dto.Dish;
import com.fengwenyi.mp3demo.enums.Type;
import com.google.common.base.Predicate;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author : Caixin
 * @date 2019/7/12 15:12
 */
public class CollectionSorted {



    public static void main(String[] args) {

        //用累加器筛选元素
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        List<Dish> lowCaloricDishes = new ArrayList<>();

        Predicate<Dish> predicate = dish -> dish != null && ObjectUtil.isNotNull(dish.getCalories());

        for (Dish d : menu) {
            if (predicate.test(d) && d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        System.out.println(lowCaloricDishes);

        //用匿名类对菜肴排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        //处理排序后的菜名列表
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        System.out.println("处理排序后的菜名列表"+lowCaloricDishesName);


//        List<String> lowCaloricDishesName2 =
//                menu.stream()
//                        //选出400卡路里以下的菜肴
//                        .filter(d -> d.getCalories() < 400)
//                        //按照卡路里排序 倒叙排序
//                        .sorted(comparing(Dish::getCalories))
//                        .sorted(comparing(Dish::getName).reversed())
//                        //提取菜肴的名称
//                        .map(Dish::getName)
//                        //将所有名称保存在 List 中
//                        .collect(toList());
//        System.out.println(lowCaloricDishesName2);

        //为了利用多核架构并行执行这段代码，你只需要把 stream() 换成 parallelStream()
        List<String> lowCaloricDishesName3 =
                menu.parallelStream()
                        //选出400卡路里以下的菜肴
                        .filter(d -> d.getCalories() < 400)
                        //按照卡路里排序 倒叙排序
                        .sorted(comparing(Dish::getCalories))
                        .sorted(comparing(Dish::getName).reversed())
                        //提取菜肴的名称
                        .map(Dish::getName)
                        //将所有名称保存在 List 中
                        .collect(toList());
        System.out.println(lowCaloricDishesName3);

        /**
         * 代码是以声明性方式写的
         * 你可以把几个基础操作链接起来，来表达复杂的数据处理流水线（在 filter 后面接上
         * sorted 、 map 和 collect 操作，如图4-1所示），同时保持代码清晰可读。 filter 的结果
         * 被传给了 sorted 方法，再传给 map 方法，最后传给 collect 方法
         *
         *  声明性——更简洁，更易读
         *  可复合——更灵活
         *  可并行——性能更好
         */

        Map<Dish.Type, List<Dish>> typeListMap = menu.stream().collect(groupingBy(Dish::getType));

//        {
//          FISH=[prawns, salmon],
//          OTHER=[french fries, rice, season fruit, pizza],
//          MEAT=[pork, beef, chicken]
//        }

    }
}
