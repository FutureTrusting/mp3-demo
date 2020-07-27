package com.fengwenyi.mp3demo.java8.expression;

import com.fengwenyi.mp3demo.dto.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * @author : Caixin
 * @date 2019/7/12 9:35
 */
public class ComplexExpression {

    public static void main(String[] args) {

        List<Apple> inventory2 = Arrays.asList(new Apple("PURPLE", 118), new Apple("blue", 119), new Apple("pink", 998));
        inventory2.sort((Apple a9, Apple a10) -> a9.getWeight().compareTo(a10.getWeight()));
        //简化1,Java编译器可以根据Lambda出现的上下文来推断Lambda表达式参数的类型
        inventory2.sort((a3, a4) -> a3.getWeight().compareTo(a4.getWeight()));
        //简化2
        Comparator<Apple> c10 = comparing((Apple a) -> a.getWeight());
        //现在你可以把代码再改得紧凑一点了
        inventory2.sort(comparing((a11) -> a11.getWeight()));
        inventory2.sort(comparing(a12 -> a12.getWeight()));
        //第 4 步：使用方法引用 对库存进行排序，比较苹果的重量
        inventory2.sort(comparing(Apple::getWeight));
        //1. 逆序 按重量递减排序
        inventory2.sort(comparing(Apple::getWeight).reversed());
        //比较器链 上面说得都很好，但如果发现有两个苹果一样重怎么办？哪个苹果应该排在前面呢？你可能需要再提供一个 Comparator 来进一步定义这个比较
        //按重量递减排序 两个苹果一样重时，进一步按国家排序
        inventory2.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));

        Apple apple4 = new Apple("RED", 140);
        apple4.setCountry("China");

        //谓词复合
        //Predicate 其实表示是条件，可以复用的条件，看起来更优雅的条件
        Predicate<Apple> redApple = apple -> apple != null;
        //表示不是redApple  表示否定 negate 源码表示否定的test
        Predicate<Apple> notRedApple = redApple.negate();

        boolean test1 = redApple.test(apple4);
        System.out.println("表示是红苹果" + test1);
        boolean test = notRedApple.test(apple4);
        System.out.println("表示不是红苹果" + test);

        //表示redApple并且重量大于150
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        boolean test2 = redAndHeavyApple.test(apple4);
        System.out.println("判断apple4是redApple并且重量大于150吗" + test2);

        //a.getWeight() > 150 || "red".equalsIgnoreCase(a.getColor()) 判断重量大于150或者color为red吗
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150).or(a -> "red".equalsIgnoreCase(a.getColor()));
        boolean test4 = redAndHeavyAppleOrGreen.test(apple4);
        System.out.println("判断重量大于150或者color为red吗" + test4);

        Apple appleX = new Apple("RED", 140);
        Predicate<Apple> redApple2 = apple -> apple != null && "China".equalsIgnoreCase(apple.getCountry());
        Predicate<Apple> appleWeight = redApple2.or(a -> a.getWeight() > 150);
        boolean test3 = appleWeight.test(appleX);
        System.out.println("(appleX!=null && China) || Weight() > 10" + test3);

        //  a.or(b).and(c) 可以看作 (a || b) && c
        Predicate<Apple> applePredicate = redApple.and(a -> a.getWeight() > 150).or(a -> "red".equalsIgnoreCase(a.getColor())).and(a -> "china".equalsIgnoreCase(a.getCountry()));

        /**
         * 从简单Lambda表达式出发，你可以构建更复杂的表达式，但读起来
         * 仍然和问题的陈述差不多！请注意， and 和 or 方法是按照在表达式链中的位置，从左向右确定优
         * 先级的。因此， a.or(b).and(c) 可以看作 (a || b) && c
         */

    }

}
