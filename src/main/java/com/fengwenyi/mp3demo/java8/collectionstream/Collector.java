package com.fengwenyi.mp3demo.java8.collectionstream;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author : Caixin
 * @date 2019/7/18 18:37
 */
public interface Collector<T, A, R> {
    //其中 T 、 A 和 R 分别是流中元素的类型、用于累积部分结果的对象类型，以及 collect 操作最
    //终结果的类型。这里应该收集 Integer 流，而累加器和结果类型则都是 Map<Boolean,
    //List<Integer>> （和先前代码清单6-6中分区操作得到的结果 Map 相同），键是 true 和 false ，
    //值则分别是质数和非质数的 List ：

    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    Function<A, R> finisher();

    BinaryOperator<A> combiner();

    Set<java.util.stream.Collector.Characteristics> characteristics();


    public static void main(String[] args) {
//        代码清单6-4  Collector 接口
//        public interface Collector<T, A, R> {
//            Supplier<A> supplier();
//            BiConsumer<A, T> accumulator();
//            Function<A, R> finisher();
//            BinaryOperator<A> combiner();
//            Set<Characteristics> characteristics();
//        }

//        本列表适用以下定义。
//          T 是流中要收集的项目的泛型。
//          A 是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。
//          R 是收集操作得到的对象（通常但并不一定是集合）的类型。

        // 例如，你可以实现一个 ToListCollector<T> 类，将 StreamUtil<T> 中的所有元素收集到一个List<T> 里，它的签名如下：
        // public class ToListCollector<T> implements Collector<T, List<T>, List<T>>

        // 通过分析，你会注意到，前四个方法都会返回一个会被 collect 方法调用的函数，
        // 而第五个方法 characteristics 则提供了一系列特征，也就是一个提示列表，
        // 告诉 collect 方法在执行归约操作的时候可以应用哪些优化（比如并行化）。

        //1. 建立新的结果容器： supplier 方法
        //对于将累加器本身作为结果返回的收集器，
        // 比如我们的 ToListCollector ，在对空流执行操作的时候，这个空的累加器也代表了收集过程的结果。
        // 在我们的 ToListCollector 中， supplier 返回一个空的 List ，如下所示：
        //public Supplier<List<T>> supplier() {
        //            return () -> new ArrayList<T>();
        //        }
        //请注意你也可以只传递一个构造函数引用：
        //public Supplier<List<T>> supplier() {
        //      return ArrayList::new;
        //}
        //2. 将元素添加到结果容器： accumulator 方法
        // accumulator 方法会返回执行归约操作的函数。当遍历到流中第n个元素时，这个函数执行
        // 时会有两个参数：保存归约结果的累加器（已收集了流中的前 n1 个项目），还有第n个元素本身。
        // 该函数将返回 void ，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的
        // 元素的效果。对于 ToListCollector ，这个函数仅仅会把当前项目添加至已经遍历过的项目的
        // 列表：
//        public BiConsumer<List<T>, T> accumulator() {
//            return (list, item) -> list.add(item);
//        }
//        你也可以使用方法引用，这会更为简洁：
//        public BiConsumer<List<T>, T> accumulator() {
//            return List::add;
//        }
        //3. 对结果容器应用最终转换： finisher 方法
        //在遍历完流后， finisher 方法必须返回在累积过程的最后要调用的一个函数，以便将累加
        //器对象转换为整个集合操作的最终结果。通常，就像 ToListCollector 的情况一样，累加器对
        //象恰好符合预期的最终结果，因此无需进行转换。所以 finisher 方法只需返回 identity 函数：
//        public Function<List<T>, List<T>> finisher() {
//            return Function.identity();
//        }

        //4. 合并两个结果容器： combiner 方法
        // 四个方法中的最后一个—— combiner 方法会返回一个供归约操作使用的函数，它定义了对
        // 流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并。对于 toList 而言，
        // 这个方法的实现非常简单，只要把从流的第二个部分收集到的项目列表加到遍历第一部分时得到
        // 的列表后面就行了：
//        public BinaryOperator<List<T>> combiner() {
//            return (list1, list2) -> {
//                list1.addAll(list2);
//                return list1; }
//        }
        // 原始流会以递归方式拆分为子流，直到定义流是否需要进一步拆分的一个条件为非（如
        // 果分布式工作单位太小，并行计算往往比顺序计算要慢，而且要是生成的并行任务比处
        // 理器内核数多很多的话就毫无意义了）。
        //   现在，所有的子流都可以并行处理，即对每个子流应用图6-7所示的顺序归约算法。
        //   最后，使用收集器 combiner 方法返回的函数，将所有的部分结果两两合并。这时会把原
        // 始流每次拆分时得到的子流对应的结果合并起来。

        //5.  characteristics 方法
        //最后一个方法—— characteristics 会返回一个不可变的 Characteristics 集合，它定义了收集器的行为——尤其是关于流是否可以并行归约，
        // 以及可以使用哪些优化的提示。
        //Characteristics 是一个包含三个项目的枚举。
        //    UNORDERED ——归约结果不受流中项目的遍历和累积顺序的影响。
        //    CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归 约流。
        //  如果收集器没有标为 UNORDERED ，那它仅在用于无序数据源时才可以并行归约。
        //    IDENTITY_FINISH ——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
        //  情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检查地转换为结果 R 是安全的。

        //开发你自己的收集器以获得更好的性能
        //代码清单6-6 将前n个自然数按质数和非质数分区
        IntStream.rangeClosed(0, 10).forEach(x -> {
            Map<Boolean, List<Integer>> listMap = partitionPrimes(x);
            System.out.println(listMap);
        });


    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
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
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

//    public static boolean isPrime(List<Integer> primes, int candidate) {
//        return primes.stream().noneMatch(i -> candidate % i == 0);
//    }

    //现在你可以用这个新的自定义收集器来代替6.4节中用 partitioningBy 工厂方法创建的那
    //个，并获得完全相同的结果了：
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(Integer n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    //比较收集器的性能
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}
