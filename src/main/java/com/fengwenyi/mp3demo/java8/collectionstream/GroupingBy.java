package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;
import com.fengwenyi.mp3demo.enums.CaloricLevel;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author : Caixin
 * @date 2019/7/16 15:26
 */
@Slf4j
public class GroupingBy {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        Map<Dish.Type, List<Dish>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType));
        //{FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza],MEAT=[pork, beef, chicken]}

        //例如，你可能想把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700卡路里的菜划为“普通”（normal），
        // 高于700卡路里的划为“高热量”（fat）。
        // 由于 Dish 类的作者没有把这个操作写成一个方法，你无法使用方法引用，但你可以把这个逻辑写成Lambda表达式：
        Map<CaloricLevel, List<Dish>> levelListMap = menu.stream()
                .collect(groupingBy(d -> {
                    if (d.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (d.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));
//        System.out.println(levelListMap);
//        要实现多级分组，我们可以使用一个由双参数版本的 Collectors.groupingBy 工厂方法创
//        建的收集器，它除了普通的分类函数之外，还可以接受 collector 类型的第二个参数。那么要进
//        行二级分组的话，我们可以把一个内层 groupingBy 传递给外层 groupingBy ，并定义一个为流
//        中项目分类的二级标准，如代码清单6-2所示。

        //代码清单6-2 多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> typeMapMap = menu.stream()
                .collect(groupingBy(Dish::getType, //一级分类函数
                        groupingBy(dish -> { //二级分类函数
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));
        System.out.println(typeMapMap);
        //{
        // MEAT={DIET=[chicken], NORMAL=[beef], FAT=[pork]},
        // FISH={DIET=[prawns],NORMAL=[salmon]},
        // OTHER={DIET=[rice, seasonal fruit], NORMAL=[french fries, pizza]}
        // }

        //这种多级分组操作可以扩展至任意层级，n级分组就会得到一个代表n级树形结构的n级Map

        //我们看到可以把第二个 groupingBy 收集器传递给外层收集器来实现多级分组。
        // 但进一步说，传递给第一个 groupingBy 的第二个收集器可以是任何类型，而不一定是另一个 groupingBy 。
        // 例如，要数一数菜单中每类菜有多少个，可以传递 counting 收集器作为groupingBy 收集器的第二个参数：
        Map<Dish.Type, Long> longMap = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        //{FISH=2, MEAT=3, OTHER=4}
        System.out.println(longMap);

        //还要注意，普通的单参数 groupingBy(f) （其中 f 是分类函数）实际上是 groupingBy(f,toList()) 的简便写法
        Map<String, List<Dish>> listMap = menu.stream()
                .collect(groupingBy(Dish::getName, toList()));
        Map<String, List<Dish>> listMap2 = menu.stream()
                .collect(groupingBy(Dish::getName));
        System.out.println(listMap);
        System.out.println(listMap2);
        //再举一个例子，你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类型分类：
        //这个分组的结果显然是一个 map ，以 Dish 的类型作为键，以包装了该类型中热量最高的 Dish的 OptionalNullPointException<Dish> 作为值：
        Map<Dish.Type, Optional<Dish>> typeOptionalMap = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        Map<Dish.Type, Optional<Dish>> typeOptionalMap1 = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));
        System.out.println(typeOptionalMap);
        // 把收集器的结果转换为另一种类型
        // 因为分组操作的 Map 结果中的每个值上包装的 OptionalNullPointException 没什么用，所以你可能想要把它们去掉。
        // 要做到这一点，或者更一般地来说，把收集器返回的结果转换为另一种类型，
        // 你可以使用Collectors.CollectingAndThen 工厂方法返回的收集器，如下所示。

        //代码清单6-3 查找每个子组中热量最高的 Dish
        Map<Dish.Type, Dish> typeDishMap = menu.stream()
                .collect(groupingBy(Dish::getType, //分类函数
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)), //包装后的收集器
                                Optional::get //转换函数
                        )));
        // 这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。
        // 这个收集器相当于旧收集器的一个包装， collect 操作的最后一步就是将返回值用转换函数做一个映射。
        // 在这里，被包起来的收集器就是用 maxBy 建立的那个，而转换函数 OptionalNullPointException::get 则把返回的 OptionalNullPointException 中的值提取出来。
        // 前面已经说过，这个操作放在这里是安全的，因为 reducing收集器永远都不会返回 OptionalNullPointException.empty() 。
        Map<Dish.Type, Dish> typeDishMap2 = menu.stream()
                .collect(toMap(Dish::getType,
                        Function.identity(),
                        BinaryOperator.maxBy(comparingInt(Dish::getCalories))
                ));
        //{FISH=salmon, OTHER=pizza, MEAT=pork}
        System.out.println(typeDishMap);
        // 收集器用虚线表示，因此 groupingBy 是最外层，根据菜肴的类型把菜单流分组，得到三
        // 个子流。
        //   groupingBy 收集器包裹着 CollectingAndThen 收集器，因此分组操作得到的每个子流
        // 都用这第二个收集器做进一步归约。
        //   CollectingAndThen 收集器又包裹着第三个收集器 maxBy 。
        //   随后由归约收集器进行子流的归约操作，然后包含它的 CollectingAndThen 收集器会对
        // 其结果应用 OptionalNullPointException:get 转换函数。
        //   对三个子流分别执行这一过程并转换而得到的三个值，也就是各个类型中热量最高的
        // Dish ，将成为 groupingBy 收集器返回的 Map 中与各个分类键（ Dish 的类型）相关联的值。

        //例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对每一组 Dish 求和：
        Map<Dish.Type, Integer> typeIntegerMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories))
                );
        System.out.println(typeIntegerMap);
        //比方说你想要知道，对于每种类型的 Dish ，
        //菜单中都有哪些 CaloricLevel 。我们可以把 groupingBy 和 mapping 收集器结合起来，如下所示：
        Map<Dish.Type, Set<CaloricLevel>> typeListMap1 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, toSet())));
        //{OTHER=[DIET, NORMAL], MEAT=[DIET, NORMAL, FAT], FISH=[DIET, NORMAL]}
        System.out.println(typeListMap1);
        //请注意在上
        //一个示例中，对于返回的 Set 是什么类型并没有任何保证。但通过使用 toCollection ，你就可以有更多的控制。
        // 例如，你可以给它传递一个构造函数引用来要求 HashSet ：
        Map<Dish.Type, HashSet<CaloricLevel>> typeHashSetMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, toCollection(HashSet::new))));
        // 分区
        // 分区函数返回一个布尔值，这意味着得到的分组 Map 的键类型是 Boolean ，
        // 于是它最多可以 分为两组—— true 是一组， false 是一组。
        // 例如，如果你是素食者或是请了一位素食的朋友来共进晚餐，可能会想要把菜单按照素食和非素食分开：
        Map<Boolean, List<Dish>> booleanListMap = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));//分区函数
        //{false=[pork, beef, chicken, prawns, salmon],true=[french fries, rice, season fruit, pizza]}
        System.out.println(booleanListMap);
        //那么通过 Map 中键为 true 的值，就可以找出所有的素食菜肴了：
        List<Dish> dishList = booleanListMap.get(Boolean.TRUE);
        System.out.println(dishList);
        //分区的好处在于保留了分区函数返回 true 或 false 的两套流元素列表。
        // 在上一个例子中，要得到非素食 Dish 的 List ，你可以使用两个筛选操作来访问 partitionedMenu 这个 Map 中 false
        //键的值：一个利用谓词，一个利用该谓词的非。而且就像你在分组中看到的， partitioningBy
        //工厂方法有一个重载版本，可以像下面这样传递第二个收集器：
        Map<Boolean, Map<Dish.Type, List<Dish>>> mapMap = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, //分区函数
                        groupingBy(Dish::getType))); //第二个收集器
        //{false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]},
        // true={OTHER=[french fries, rice, season fruit, pizza]}}
        System.out.println(mapMap);
        //再举一个例子，你可以重用前面的代码来找到素食和非素食中热量最高的菜：
        Map<Boolean, Dish> booleanDishMap = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        //{false=pork, true=pizza}
        System.out.println(booleanDishMap);
//        测验6.2：使用 partitioningBy
//        我们已经看到，和 groupingBy 收集器类似， partitioningBy 收集器也可以结合其他收
//        集器使用。尤其是它可以与第二个 partitioningBy 收集器一起使用来实现多级分区。以下多
//        级分区的结果会是什么呢？
//        (1)  menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                partitioningBy (d -> d.getCalories() > 500)));
//        (2)  menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                partitioningBy (Dish::getType)));
//        (3)  menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                counting()));
//        答案如下。
//        (1) 这是一个有效的多级分区，产生以下二级 Map ：
//        { false={false=[chicken, prawns, salmon], true=[pork, beef]},
//            true={false=[rice, season fruit], true=[french fries, pizza]}}
//        (2) 这无法编译，因为 partitioningBy 需要一个谓词，也就是返回一个布尔值的函数。
//        方法引用 Dish::getType 不能用作谓词。
//        (3) 它会计算每个分区中项目的数目，得到以下 Map ：
//        {false=5, true=4}
        Map<Boolean, Map<Boolean, List<Dish>>> booleanMapMap = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        partitioningBy(d -> d.getCalories() > 400)));
        //{
        // false={false=[chicken, prawns, salmon],true=[pork, beef]},
        // true={false=[rice, season fruit], true=[french fries, pizza]}
        // }
        System.out.println(booleanMapMap);
        Map<Boolean, Long> booleanLongMap = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, counting()));
        //{false=5, true=4}
        System.out.println(booleanLongMap);
        //将数字按质数和非质数分区
        Map<Boolean, List<Integer>> listMap1 = partitionPrimes(99);
        System.out.println(listMap1);

        //转换函数返回的类型
        Integer collect = menu.stream()
                .collect(collectingAndThen(toList(), List::size));
        System.out.println(collect);

    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime2(candidate)));
    }


    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate) //产生一个自然数范围，从2开始，直至但不包括待测数
                .noneMatch(i -> candidate % i == 0); //如果待测数字不能被流中任何数字整除则返回 true
    }

    //一个简单的优化是仅测试小于等于待测数平方根的因子：
    public static  boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}
