package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/7/15 10:01
 */
public class FindAndMatch {
    /**
     * StreamUtil
     * API通过 allMatch 、 anyMatch 、 noneMatch 、 findFirst 和 findAny 方法提供了这样的工具
     * @param args
     */
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        //anyMatch 方法可以回答“流中是否有一个元素能匹配给定的谓词”  你可以用它来看看菜单里面是否有素食可选择
        //anyMatch 方法返回一个 boolean ，因此是一个终端操作
        boolean b = menu.stream().anyMatch(Dish::isVegetarian);
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        //allMatch 方法的工作原理和 anyMatch 类似，但它会看看流中的元素是否都能匹配给定的谓词。
        // 比如，你可以用它来看看菜品是否有利健康（即所有菜的热量都低于1000卡路里）：
        boolean b1 = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(b1);

        //和 allMatch 相对的是 noneMatch 。它可以确保流中没有任何元素与给定的谓词匹配。
        // 你可以用 noneMatch 重写前面的例子
        boolean b2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(b2);
        //anyMatch 、 allMatch 和 noneMatch 这三个操作都用到了我们所谓的短路，这就是大家熟悉的Java中 && 和 || 运算符短路在流中的版本


        //findAny 方法将返回当前流中的任意元素。它可以与其他流操作结合使用。
        //比如，你可能想找到一道素食菜肴。你可以结合使用 filter 和 findAny 方法来实现这个查询
        //返回一个Optional<Dish>
        Optional<Dish> optionalDish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        //如果包含一个值就打印它，否则什么都不做
        optionalDish.ifPresent(System.out::println);

        //OptionalNullPointException<T> 类（ java.util.OptionalNullPointException ）是一个容器类，代表一个值存在或不存在。在上面的代码中， findAny 可能什么元素都没找到


        //有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由 List 或排序好的数据列生成的流）。
        // 对于这种流，你可能想要找到第一个元素。为此有一个 findFirst方法，它的工作方式类似于 findAny

        //例如，给定一个数字列表，下面的代码能找出第一个平方能被3整除的数
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

         someNumbers.stream()
                .map(s -> s * s)
                .filter(s -> s % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
         //何时使用 findFirst 和 findAny
         //你可能会想，为什么会同时有 findFirst 和 findAny 呢？答案是并行。找到第一个元素在并行上限制更多。
         //如果你不关心返回的元素是哪个，请使用 findAny ，因为它在使用并行流时限制较少

    }














}
