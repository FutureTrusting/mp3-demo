package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;
import com.fengwenyi.mp3demo.dto.Trader;
import com.fengwenyi.mp3demo.dto.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author : Caixin
 * @date 2019/7/15 10:24
 */
public class Reduce {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));


        //在本节中，你将看到如何把一个流中的元素组合起来，使用 reduce 操作来表达更复杂的查询，
        //比如“计算菜单中的总卡路里”或“菜单中卡路里最高的菜是哪一个”。此类查询需要将流中所有元素反复结合起来，得到一个值，
        //比如一个 Integer 。这样的查询可以被归类为归约操作（将流归约成一个值）。
        //用函数式编程语言的术语来说，这称为折叠（fold），因为你可以将这个操作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4);
        //这正是 reduce 操作的用武之地，它对这种重复应用的模式做了抽象。你可以像下面这样对流中所有的元素求和：
        Integer countReduce = someNumbers.stream()
                .reduce(0, (a, b) -> a + b);
        Integer multiplyReduce = someNumbers.stream()
                .reduce(1, (a, b) -> a * b);


        //无初始值
        //reduce 还有一个重载的变体，它不接受初始值，但是会返回一个 OptionalNullPointException 对象
        Optional<Integer> sumReduce = someNumbers.stream()
                .reduce((a, b) -> a + b);
        //为什么它返回一个 OptionalNullPointException<Integer> 呢？
        //考虑流中没有任何元素的情况。 reduce 操作无法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个 OptionalNullPointException 对象里，以表明和可能不存在
        someNumbers.stream()
                .reduce((a, b) -> a + b)
                .ifPresent(System.out::println);

        someNumbers.stream()
                .reduce((a, b) -> a * b)
                .ifPresent(System.out::println);

        System.out.println("countReduce" + countReduce);
        System.out.println("multiplyReduce" + multiplyReduce);

        //在Java 8中， Integer 类现在有了一个静态的 sum方法来对两个数求和，这恰好是我们想要的，用不着反复用Lambda写同一段代码了
        Integer sumInteger = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sumInteger);

        //让我们来看看如何利用刚刚学到的 reduce来计算流中最大或最小的元素。
        // 正如你前面看到的， reduce 接受两个参数

        //你可以像下面这样使用 reduce 来计算流中的最大值
        Optional<Integer> maxInteger = someNumbers.stream().reduce(Integer::max);
        //要计算最小值，你需要把 Integer.min 传给 reduce 来替换 Integer.max
        Optional<Integer> minInteger = someNumbers.stream().reduce(Integer::min);
        //你当然也可以写成Lambda  (x, y) -> x < y ? x : y 而不是 Integer::min ，不过后者比较易读
        Optional<Integer> minReduce = someNumbers.stream().reduce((x, y) -> x < y ? x : y);

//        测验5.3：归约
//        怎样用 map 和 reduce 方法数一数流中有多少个菜呢？
//        答案：要解决这个问题，你可以把流中每个元素都映射成数字 1 ，然后用 reduce 求和。这
//        相当于按顺序数流中的元素个数。
//        int count = menu.stream()
//                .map(d -> 1)
//                .reduce(0, (a, b) -> a + b);
//        map 和 reduce 的连接通常称为 map-reduce 模式，因Google用它来进行网络搜索而出名，
//        因为它很容易并行化。请注意，在第4章中我们也看到了内置 count 方法可用来计算流中元素
//        的个数：
//        long count = menu.stream().count();

        Integer integer = menu.stream()
                .map(x -> 1)
                .reduce(0, (x, y) -> x + y);
        //在第4章中我们也看到了内置 count 方法可用来计算流中元素的个数
        long count = menu.stream()
                .count();

//        归约方法的优势与并行化
//        相比于前面写的逐步迭代求和，使用 reduce 的好处在于，这里的迭代被内部迭代抽象掉
//        了，这让内部实现得以选择并行执行 reduce 操作。而迭代式求和例子要更新共享变量 sum ，
//        这不是那么容易并行化的。如果你加入了同步，很可能会发现线程竞争抵消了并行本应带来的
//        性能提升！这种计算的并行化需要另一种办法：将输入分块，分块求和，最后再合并起来。但
//        这样的话代码看起来就完全不一样了。你在第7章会看到使用分支/合并框架来做是什么样子。
//        但现在重要的是要认识到，可变的累加器模式对于并行化来说是死路一条。你需要一种新的模
//        式，这正是 reduce 所提供的。你还将在第7章看到，使用流来对所有的元素并行求和时，你的
//        代码几乎不用修改： stream() 换成了 parallelStream() 。
//        int sum = numbers.parallelStream().reduce(0, Integer::sum);
//        但要并行执行这段代码也要付一定代价，我们稍后会向你解释：传递给 reduce 的Lambda
//        不能更改状态（如实例变量），而且操作必须满足结合律才可以按任意顺序执行。

        Integer parallelReduce = someNumbers.parallelStream()
                .reduce(0, Integer::sum);

//        流操作：无状态和有状态
//        你已经看到了很多的流操作。乍一看流操作简直是灵丹妙药，而且只要在从集合生成流的
//        时候把 StreamUtil 换成 parallelStream 就可以实现并行。
//        当然，对于许多应用来说确实是这样，就像前面的那些例子。你可以把一张菜单变成流，
//        用 filter 选出某一类的菜肴，然后对得到的流做 map 来对卡路里求和，最后 reduce 得到菜单
//        的总热量。这个流计算甚至可以并行进行。但这些操作的特性并不相同。它们需要操作的内部
//        状态还是有些问题的。
//        诸如 map 或 filter 等操作会从输入流中获取每一个元素，并在输出流中得到0或1个结果。
//        这些操作一般都是 无状态的：它们没有内部状态（假设用户提供的Lambda或方法引用没有内
//        部可变状态）。
//        但诸如 reduce 、 sum 、 max 等操作需要内部状态来累积结果。在上面的情况下，内部状态
//        很小。在我们的例子里就是一个 int 或 double 。不管流中有多少元素要处理，内部状态都是
//        有界的。
//        相反，诸如 sort 或 distinct 等操作一开始都和 filter 和 map 差不多——都是接受一个
//        流，再生成一个流（中间操作），但有一个关键的区别。从流中排序和删除重复项时都需要知
//        道先前的历史。例如，排序要求所有元素都放入缓冲区后才能给输出流加入一个项目，这一操
//        作的存储要求是无界的。要是流比较大或是无限的，就可能会有问题（把质数流倒序会做什么
//        呢？它应当返回最大的质数，但数学告诉我们它不存在）。我们把这些操作叫作 有状态操作。


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        Currency instance1 = Currency.getInstance(Locale.FRANCE);
        Currency instance2 = Currency.getInstance(Locale.CHINA);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(raoul, 2012, 1000, instance1),
                new Transaction(brian, 2011, 300, instance2),
                new Transaction(raoul, 2011, 400, instance2),
                new Transaction(mario, 2012, 710, instance1),
                new Transaction(mario, 2012, 700, instance2),
                new Transaction(alan, 2012, 950, instance1)
        );

//        (1) 代码清单5-1 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transactionList = transactions.stream()
                .filter(f -> f.getYear() == 2011)//给 filter 传递一个谓词来选择2011年的交易
                .sorted(Comparator.comparing(Transaction::getValue)) //按照交易额进行排序
                .collect(toList()); //将生成的 StreamUtil 中的所有元素收集到一个 List 中
        System.out.println("交易额排序" + transactionList);
//        (2) 代码清单5-2 交易员都在哪些不同的城市工作过？
        List<String> cityList = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity()) //提取与交易相关的每位交易员的所在城市
                .distinct() //只选择互不相同的城市
                .collect(toList());
        //这里还有一个新招：你可以去掉 distinct() ，改用 toSet() ，这样就会把流转换为集合
        Set<String> stringSet = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());
        System.out.println("不同的城市工作" + cityList);
//        (3)代码清单5-3 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader) //从交易中提取所有交易员
                .filter(f -> "Cambridge".equalsIgnoreCase(f.getCity())) //仅选择位于剑桥的交易员
                .distinct() //确保没有任何重复
                .sorted(Comparator.comparing(Trader::getName)) //对生成的交易员流按照姓名进行排序
                .collect(toList());
        List<Transaction> transactionsName = transactions.stream()
                .filter(transaction -> "Cambridge".equalsIgnoreCase(transaction.getTrader().getCity()))
                .sorted(Comparator.comparing(x -> x.getTrader().getName()))
                .collect(toList());
        System.out.println("来自于剑桥的交易员，并按姓名排序" + traders);
//        (4)代码清单5-4 返回所有交易员的姓名字符串，按字母顺序排序。
        String reduce = transactions.stream()
                .map(trader -> trader.getTrader().getName()) //提取所有交易员姓名，生成一个 Strings 构成的 StreamUtil
                .distinct() //只选择不相同的姓名
                .sorted()   //对姓名按字母顺序排序
                .reduce("", (x, y) -> x + y); //逐个拼接每个名字，得到一个将所有名字连接起来的 String
        //请注意，此解决方案效率不高（所有字符串都被反复连接，每次迭代的时候都要建立一个新的 String 对象）。
        // 下一章中，你将看到一个更为高效的解决方案，它像下面这样使用 joining （其内部会用到 StringBuilder ）：
        System.out.println(reduce);
        String joinCollect = transactions.stream()
                .map(trader -> trader.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println("Collectors.joining()" + joinCollect);
//        (5)代码清单5-5 有没有交易员是在米兰工作的？
        boolean anyMatch = transactions.stream()
                //把一个谓词传递给 anyMatch ，检查是否有交易员在米兰工作
                .anyMatch(x -> "Milan".equalsIgnoreCase(x.getTrader().getCity()));
        System.out.println("有没有交易员是在米兰工作的" + anyMatch);
//        (6)代码清单5-6 打印生活在剑桥的交易员的所有交易额。
//        List<Integer> integerTrade =
        transactions.stream()
                //选择住在剑桥的交易员所进行的交易
                .filter(f -> "Cambridge".equalsIgnoreCase(f.getTrader().getCity()))
                //提取这些交易的交易额
                .map(Transaction::getValue)
                //打印每个值
                .forEach(System.out::println);
//              .collect(toList());
//        (7)代码清单5-7 所有交易中，最高的交易额是多少？
        transactions.stream()
                //提取每项交易的交易额
                .map(Transaction::getValue)
                //计算生成的流中的最大值
                .reduce(Integer::max)
                .ifPresent(System.out::println);
//        (8)代码清单5-8 找到交易额最小的交易。
        Optional<Integer> optionalInteger = transactions.stream()
                .map(Transaction::getValue)
                .reduce((x, y) -> x < y ? x : y);
        //流支持 min 和 max 方法，它们可以接受一个 Comparator 作为参数，
        // 指定计算最小或最大值时要比较哪个键值
        Optional<Transaction> transaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min).ifPresent(System.out::println);

        //我们在前面看到了可以使用 reduce 方法计算流中元素的总和。例如，你可以像下面这样计算菜单的热量
        //这段代码的问题是，它有一个暗含的装箱成本。每个 Integer 都必须拆箱成一个原始类型，再进行求和
        Integer reduceSum = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("reduceSum" + reduceSum);

        //原始类型流特化
        //Java 8引入了三个原始类型特化流接口来解决这个问题： IntStream 、 DoubleStream 和
        //LongStream ，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本。每
        //个接口都带来了进行常用数值归约的新方法，比如对数值流求和的 sum ，找到最大元素的 max 。
        //此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复
        //杂性，而是装箱造成的复杂性——即类似 int 和 Integer 之间的效率差异

        //将流转换为特化版本的常用方法是 mapToInt 、 mapToDouble 和 mapToLong 。
        // 这些方法和前面说的 map 方法的工作方式一样，只是它们返回的是一个特化流，而不是 StreamUtil<T> 。
        // 例如，你可以像下面这样用 mapToInt 对 menu 中的卡路里求和：
        int sum = menu.stream()//返回一个Stream<Dish>
                .mapToInt(Dish::getCalories) //返回一个IntStream
                .sum();
        //请注意，如果流是空的， sum 默认返回 0 。 IntStream 还支持其他的方便方法，如max 、 min 、 average 等
        OptionalInt optionalInt = menu.stream()
                .mapToInt(Dish::getCalories)
                .min();
        System.out.println("mapToInt转sum" + sum);
        //转换回对象流

        //将 StreamUtil 转换为数值流
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        //将数值流转换为 StreamUtil
        //你在下一节中会看到，在需要将数值范围装箱成为一个一般流时， boxed 尤其有用
        Stream<Integer> stream = intStream.boxed();

        //默认值 OptionalInt
        //对于三种原始流特化，也分别有一个 OptionalNullPointException 原始类型特化版本：
        // OptionalInt 、 OptionalDouble 和 OptionalLong 。
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        //如果没有最大值的话，显式提供一个默认最大值
        int max = maxCalories.orElse(1);
        System.out.println(max);

//        数值范围
//        和数字打交道时，有一个常用的东西就是数值范围。比如，假设你想要生成1和100之间的所有数字。
//        Java 8引入了两个可以用于 IntStream 和 LongStream 的静态方法，帮助生成这种范围：
//        range 和 rangeClosed 。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。
//        但range 是不包含结束值的，而 rangeClosed 则包含结束值。

        //一个从1到100的偶数流
        //表 示 范 围[1, 100]
        IntStream range100 = IntStream.rangeClosed(0, 100)
                .filter(x -> x % 2 == 0);
        //从1到100有50个偶数
        System.out.println(range100.count());

        //勾股数
//        StreamUtil<int[]> stream1 = IntStream.rangeClosed(1, 100)
//                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
//                .boxed()
//                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

//        StreamUtil<int[]> mapToObj = IntStream.rangeClosed(1, 100)
//                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
//                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        //这里有一个关键的假设：给出了 a 的值。 现在，只要已知 a 的值，你就有了一个可以生成勾股数的流。
        // 如何解决这个问题呢？就像 b 一样，你需要为 a 生成数值！最终的解决方案如下所示：

        IntStream intStream1 = IntStream.rangeClosed(0, 100);
        Stream<Integer> boxed = IntStream.rangeClosed(0, 100).boxed();

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(x, 100)
                        .filter(y -> Math.sqrt(x * x + y * y) % 1 == 0)
                        .mapToObj(y -> new int[]{x, y, (int) Math.sqrt(x * x + y * y)}));
        pythagoreanTriples.limit(5)
                .forEach(z -> System.out.println(z[0] + "," + z[1] + "," + z[2]));

        // 8. 你还能做得更好吗？
        // 目前的解决办法并不是最优的，因为你要求两次平方根。
        // 让代码更为紧凑的一种可能的方法是，先生成所有的三元数 (a*a, b*b, a*a+b*b) ，然后再筛选符合条件的：
        IntStream.rangeClosed(1, 100)
                .boxed()    //IntStream转换成Stream<Integer>流
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        //产生三元数
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        //元组中的第三个元素必须是整数
                        .filter(c -> c[2] % 1 == 0)
                ).forEach(d -> System.out.println(d[0] + "," + d[1] + "," + d[2]));
    }
}
