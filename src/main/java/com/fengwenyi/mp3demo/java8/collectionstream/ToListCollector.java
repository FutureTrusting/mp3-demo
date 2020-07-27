package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.*;

/**
 * @author : Caixin
 * @date 2019/7/19 10:22
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() { //创建集合操作的起始点
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() { //累积遍历过的项目,原位修改累加器
        return List::add;
    }

    @Override
    public Function<List<T>, List<T>> finisher() { //恒等函数
        return Function.identity();
    }

    @Override
    public BinaryOperator<List<T>> combiner() { //
        return (list1, list2) -> {
            list1.addAll(list2); //修改第一个累加器,将其与第二个累加器的内容合并
            return list1;  //返回修改后的第一个累加器
        };
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT));  //为收集器添加 IDENTITY_FINISH 和 CONCURRENT 标志
    }

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        //请注意，这个实现与 Collectors.toList 方法并不完全相同，但区别仅仅是一些小的优化。
        //这些优化的一个主要方面是Java API所提供的收集器在需要返回空列表时使用了 Collections.emptyList() 这个单例（singleton）。
        // 这意味着它可安全地替代原生Java，来收集菜单流中的所有 Dish 的列表：
        List<Dish> dishList = menu.stream().collect(new ToListCollector<Dish>());
        //这个实现和标准的
        List<Dish> dishList1 = menu.stream().collect(Collectors.toList());
        System.out.println(dishList);
        System.out.println(dishList1);
        //构造之间的其他差异在于 toList 是一个工厂，而 ToListCollector 必须用 new 来实例化。

        //进行自定义收集而不去实现 Collector
        //对于 IDENTITY_FINISH 的收集操作，还有一种方法可以得到同样的结果而无需从头实现新
        //的 Collectors 接口。 StreamUtil 有一个重载的 collect 方法可以接受另外三个函数—— supplier 、
        //accumulator 和 combiner ，其语义和 Collector 接口的相应方法返回的函数完全相同。
        // 所以比如说，我们可以像下面这样把菜肴流中的项目收集到一个 List 中：
        List<Dish> dishList2 = menu.stream()
                .collect(ArrayList::new, //供应源
                        List::add, //累加器
                        List::addAll); //组合器
        System.out.println(dishList2);

    }

}
