package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;
import com.fengwenyi.mp3demo.dto.Trader;
import com.fengwenyi.mp3demo.dto.Transaction;
import io.swagger.models.auth.In;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * @author : Caixin
 * @date 2019/7/15 16:46
 */
public class CollectorJoin {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader brian = new Trader("Brian", "Cambridge");
        Trader alan = new Trader("Alan", "Cambridge");
        Currency instance1 = Currency.getInstance(Locale.FRANCE);
        Currency instance2 = Currency.getInstance(Locale.CHINA);
        List<Transaction> transactions = Arrays.asList(
                new Transaction(raoul, 2012, 1000, instance1),
                new Transaction(brian, 2011, 300, instance2),
                new Transaction(raoul, 2011, 400, instance2),
                new Transaction(mario, 2012, 700, instance2),
                new Transaction(mario, 2012, 710, instance1),
                new Transaction(alan, 2012, 950, instance1)
        );
        //对一个交易列表按货币分组，获得该货币的所有交易额总和（返回一个 Map<Currency,Integer> ）。
        //代码清单6-1 用指令式风格对交易按照货币分组
        //建立累积交易分组的Map
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>(100);
        //迭代 Transaction 的 List
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            //如果分组 Map 中没有这种货币的条目，就创建一个
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        System.out.println(transactionsByCurrencies);

        //用Stream 中 collect 方法的一个更通用的 Collector 参数，你就可以用一句话实现完全相同的结果，而用不着使用上一章中那个 toList 的特殊情况了：
        Map<Currency, List<Transaction>> currencyListMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(currencyListMap);

        //最直接和最常用的收集器是 toList静态方法，它会把流中所有的元素收集到一个 List 中
        List<Transaction> transactionList = transactions.stream()
                .collect(Collectors.toList());
//        预定义收集器
//        也就是那些可以从 Collectors类提供的工厂方法（例如 groupingBy ）创建的收集器。它们主要提供了三大功能：
//          将流元素归约和汇总为一个值
//          元素分组
//          元素分区

        //我们先来举一个简单的例子，利用 counting 工厂方法返回的收集器，数一数菜单里有多少种菜
        Long count = menu.stream()
                .collect(counting());
        //这还可以写得更为直接：
        long howManyDishes = menu.stream().count();
        System.out.println(howManyDishes);

        //你可以创建一个 Comparator 来根据所含热量对菜肴进行比较，并把它传递给Collectors.maxBy
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> optionalDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));

        //另一个常见的返回单个值的归约操作是对流中对象的一个数值字段求和。或者你可能想要求平均数。
        // 这种操作被称为汇总操作。让我们来看看如何使用收集器来表达汇总操作
        Integer integer = menu.stream().collect(summingInt(Dish::getCalories));
        Integer integer2 = menu.stream().mapToInt(Dish::getCalories).sum();
        //在遍历流时，会把每一道菜都映射为其热量，然后把这个数字累加到一个累加器（这里的初始值 0 ）。
        //但汇总不仅仅是求和；还有 Collectors.averagingInt ，连同对应的 averagingLong 和averagingDouble 可以计算数值的平均数
        Double aDouble = menu.stream()
                .collect(averagingInt(Dish::getCalories));
        double anElse = menu.stream().mapToDouble(Dish::getCalories).average().orElse(0);

        //到目前为止，你已经看到了如何使用收集器来给流中的元素计数，找到这些元素数值属性的
        //最大值和最小值，以及计算其总和和平均值。不过很多时候，你可能想要得到两个或更多这样的
        //结果，而且你希望只需一次操作就可以完成。在这种情况下，你可以使用 summarizingInt 工厂
        //方法返回的收集器。例如，通过一次 summarizing 操作你可以就数出菜单中元素的个数，并得
        //到菜肴热量总和、平均值、最大值和最小值
        IntSummaryStatistics statistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(statistics);

        //joining 工厂方法返回的收集器会把对流中每一个对象应用 toString 方法得到的所有字符串连接成一个字符串。
        // 这意味着你把菜单中所有菜肴的名称连接起来，如下所示：
        String joining = menu.stream()
                .map(Dish::getName)
                .collect(joining());

        //请注意， joining 在内部使用了 StringBuilder 来把生成的字符串逐个追加起来。此外还
        //要注意，如果 Dish 类有一个 toString 方法来返回菜肴的名称，那你无需用提取每一道菜名称的
        //函数来对原流做映射就能够得到相同的结果
//        String shortMenu = menu.stream().collect(joining());

        //但该字符串的可读性并不好。幸好， joining 工厂方法有一个重载版本可以接受元素之间的
        //分界符，这样你就可以得到一个逗号分隔的菜肴名称列表
        String joiner = menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));
        System.out.println(joiner);

        //例如，可以用 reducing 方法创建的收集器来计算你菜单的总热量，如下所示：
        // 它需要三个参数。
        //   第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言 0 是一个合适的值。
        //   第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的 int 。
        //   第三个参数是一个 BinaryOperator ，将两个项目累积成一个同类型的值。这里它就是
        // 对两个 int 求和。
        Integer integer1 = menu.stream().collect(reducing(0, Dish::getCalories, (x, y) -> x + y));
        Integer integer11 = menu.stream().map(Dish::getCalories).reduce(0, (x, y) -> x + y);
        //同样，你可以使用下面这样单参数形式的 reducing 来找到热量最高的菜，如下所示：
        Optional<Dish> optionalDish1 = menu.stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
//        收集与归约
//        在上一章和本章中讨论了很多有关归约的内容。你可能想知道， StreamUtil 接口的 collect
//        和 reduce 方法有何不同，因为两种方法通常会获得相同的结果。例如，你可以像下面这样使
//        用 reduce 方法来实现 toList Collector 所做的工作：
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> reduceList = stream.reduce(new ArrayList<>(), (List<Integer> list, Integer e) -> {
            list.add(e);
            return list;
        }, (List<Integer> list1, List<Integer> list12) -> {
            list1.addAll(list12);
            return list1;
        });
        System.out.println(reduceList);
        //  这个解决方案有两个问题：一个语义问题和一个实际问题。语义问题在于， reduce 方法
        //  旨在把两个值结合起来生成一个新值，它是一个不可变的归约。与此相反， collect 方法的设
        //  计就是要改变容器，从而累积要输出的结果。这意味着，上面的代码片段是在滥用 reduce 方
        //  法，因为它在原地改变了作为累加器的 List 。你在下一章中会更详细地看到，以错误的语义
        //  使用 reduce 方法还会造成一个实际问题：这个归约过程不能并行工作，因为由多个线程并发
        //  修改同一个数据结构可能会破坏 List 本身。在这种情况下，如果你想要线程安全，就需要每
        //  次分配一个新的 List ，而对象分配又会影响性能。这就是 collect 方法特别适合表达可变容
        //  器上的归约的原因，更关键的是它适合并行操作，本章后面会谈到这一点。

        //收集框架的灵活性：以不同的方法执行同样的操作
        Integer totalCalories = menu.stream()
                .collect(reducing(0, //初始值
                        Dish::getCalories, //转换函数
                        Integer::sum)); //累积函数
        Integer integer4 = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        Integer integer5 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);

        //我们在6.2节开始时提到的 counting 收集器也是类似地利用三参数 reducing 工厂方法实现的。
        // 它把流中的每个元素都转换成一个值为 1 的 Long 型对象，然后再把它们相加：
        Long aLong = menu.stream().collect(counting());
        //我们在第5章已经注意到，还有另一种方法不使用收集器也能执行相同操作——
        // 将菜肴流映射为每一道菜的热量，然后用前一个版本中使用的方法引用来归约得到的流：
        int totalCalories2 =
                menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        Integer integer3 = menu.stream()
                .map(Dish::getCalories).reduce(Integer::sum).orElse(null);
        //请注意，就像流的任何单参数 reduce 操作一样， reduce(Integer::sum) 返回的不是 int而是 OptionalNullPointException<Integer> ，
        // 以便在空流的情况下安全地执行归约操作

        //最后，更简洁的方法是把流映射到一个 IntStream ，然后调用 sum 方法，你也可以得到相同的结果：
        int sum = menu.stream()
                .mapToInt(Dish::getCalories).sum();

//        测验6.1：用 reducing 连接字符串
//        以下哪一种 reducing 收集器的用法能够合法地替代 joining 收集器（如6.2.3节用法）？
//        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
//        (1)  String shortMenu = menu.stream().map(Dish::getName)
//                .collect( reducing ( (s1, s2) -> s1 + s2 ) ).get();
//        (2)  String shortMenu = menu.stream()
//                .collect( reducing( (d1, d2) -> d1.getName() + d2.getName() ) ).get();
//        (3)  String shortMenu = menu.stream()
//                .collect( reducing( "",Dish::getName, (s1, s2) -> s1 + s2 ) );
//        答案：语句1和语句3是有效的，语句2无法编译。
//        (1) 这会将每道菜转换为菜名，就像原先使用 joining 收集器的语句一样。然后用一个
//        String 作为累加器归约得到的字符串流，并将菜名逐个连接在它后面。
//        (2) 这无法编译，因为 reducing 接受的参数是一个 BinaryOperator<t> ，也就是一个
//        BiFunction<T,T,T> 。这就意味着它需要的函数必须能接受两个参数，然后返回一个相同类
//        型的值，但这里用的Lambda表达式接受的参数是两个菜，返回的却是一个字符串。
//        (3) 这会把一个空字符串作为累加器来进行归约，在遍历菜肴流时，它会把每道菜转换成
//        菜名，并追加到累加器上。请注意，我们前面讲过， reducing 要返回一个 OptionalNullPointException 并不需
//        要三个参数，因为如果是空流的话，它的返回值更有意义——也就是作为累加器初始值的空字
//        符串。
//        请注意，虽然语句1和语句3都能够合法地替代 joining 收集器，它们在这里是用来展示我
//        们为何可以（至少在概念上）把 reducing 看作本章中讨论的所有其他收集器的概括。然而就
//        实际应用而言，不管是从可读性还是性能方面考虑，我们始终建议使用 joining 收集器。
        String collectJoining = menu.stream()
                .map(Dish::getName)
                .collect(joining());
        String collectReducing = menu.stream()
                .map(Dish::getName)
                .collect(reducing((s1, s2) -> s1 + s2)).get();
        String collectReducing2 = menu.stream()
                .collect(reducing("", Dish::getName, (d1, d2) -> d1 + d2));

    }
        //在现实中，我们在6.2节开始时提到的 counting 收集器也是类似地利用三参数 reducing 工厂方法实现的。
        // 它把流中的每个元素都转换成一个值为 1 的 Long 型对象，然后再把它们相加：
        public static <T> Collector<T, ?, Long> counting() {
            return reducing(0L, e -> 1L, Long::sum);
        }

}
