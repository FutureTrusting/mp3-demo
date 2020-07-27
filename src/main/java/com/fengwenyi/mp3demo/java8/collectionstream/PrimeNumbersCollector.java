package com.fengwenyi.mp3demo.java8.collectionstream;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author : Caixin
 * @date 2019/7/19 11:02
 */
public class PrimeNumbersCollector implements Collector2<Integer,//流中元素的类型
        Map<Boolean, List<Integer>>, //累加器类型
        Map<Boolean, List<Integer>> //collect操作的结果类型
        >{

    private <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) { //检查列表中的当前项目是否满足谓词
                return list.subList(0, i); //如果不满足，返回该项目之前的前缀子列表
            }
            i++;
        }
        return list;//列表中的所有项目都满足谓词，因此返回列表本身
    }

    //利用这个方法，你就可以优化 isPrime 方法，只用不大于被测数平方根的质数去测试了：
    private boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    // 2. 第二步：实现归约过程
    // 接下来，你需要实现 Collector 接口中声明的五个方法。
    // supplier 方法会返回一个在调用时创建累加器的函数：
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{ //从一个有两个空List 的 Map 开始收集过程
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    //这里不但创建了用作累加器的 Map ，还为 true 和 false 两个键下面初始化了对应的空列表。
    //在收集过程中会把质数和非质数分别添加到这里。收集器中最重要的方法是 accumulator ，因
    //为它定义了如何收集流中元素的逻辑。这里它也是实现前面所讲的优化的关键。现在在任何一次
    //迭代中，都可以访问收集过程的部分结果，也就是包含迄今找到的质数的累加器：
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get( isPrime( acc.get(true),
                    candidate) )//根据 isPrime 的结果，获取质数或非质数列表 将已经找到的质数列表传递给 isPrime 方法
                    .add(candidate);// 将被测数添加到相应的列表中  根据 isPrime 方法的返回值，从 Map 中取质数或非质数列表，把当前的被测数加进去
        };
    }
    //在这个方法中，你调用了 isPrime 方法，将待测试是否为质数的数以及迄今找到的质数列表
    //（也就是累积 Map 中 true 键对应的值）传递给它。这次调用的结果随后被用作获取质数或非质数
    //列表的键，这样就可以把新的被测数添加到恰当的列表中。


    //3. 第三步：让收集器并行工作（如果可能）
    //下一个方法要在并行收集时把两个部分累加器合并起来，这里，它只需要合并两个 Map ，即
    //将第二个 Map 中质数和非质数列表中的所有数字合并到第一个 Map 的对应列表中就行了：
    //请注意，实际上这个收集器是不能并行使用的，因为该算法本身是顺序的。这意味着永远都
    //不会调用 combiner 方法，你可以把它的实现留空（更好的做法是抛出一个 Unsupported-
    //OperationException 异常）。为了让这个例子完整，我们还是决定实现它。
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,
                Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true)); //将第二个 Map 合并到第一个
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    //4. 第四步： finisher 方法和收集器的 characteristics 方法
    //最后两个方法的实现都很简单。前面说过， accumulator 正好就是收集器的结果，用不着
    //进一步转换，那么 finisher 方法就返回 identity 函数：
    @Override
    public Function<Map<Boolean, List<Integer>>,
            Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();//收集过程最后无需转换，因此用 identity 函数收尾
    }

    //就 characteristics 方法而言，我们已经说过，它既不是 CONCURRENT 也不是 UNORDERED ，但却是 IDENTITY_FINISH 的：
    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));//这个收集器是 IDENTITY_FINISH ，但既不是 UNORDERED也不是 CONCURRENT ，因为质数是按顺序发现的
    }


}
