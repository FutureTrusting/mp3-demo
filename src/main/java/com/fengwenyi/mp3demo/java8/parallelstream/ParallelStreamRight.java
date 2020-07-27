package com.fengwenyi.mp3demo.java8.parallelstream;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * @author : Caixin
 * @date 2019/7/19 15:35
 */
public class ParallelStreamRight {
    //7.1.3 正确使用并行流
    public static void main(String[] args) {
        //这种代码非常普遍，特别是对那些熟悉指令式编程范式的程序员来说。这段代码和你习惯的
        //那种指令式迭代数字列表的方式很像：初始化一个累加器，一个个遍历列表中的元素，把它们和
        //累加器相加。
        long sideEffectSum = sideEffectSum(10);
        System.out.println(sideEffectSum);
        //那这种代码又有什么问题呢？不幸的是，它真的无可救药，因为它在本质上就是顺序的。每
        //次访问 total 都会出现数据竞争。如果你尝试用同步来修复，那就完全失去并行的意义了。为了
        //说明这一点，让我们试着把 StreamUtil 变成并行的：
        long sideEffectSum2 = sideEffectParallelSum(10);
        System.out.println(sideEffectSum2);
        //用代码清单7-1中的测试框架来执行这个方法，并打印每次执行的结果：
        //你可能会得到类似于下面这种输出：
        //Result: 5959989000692
        //Result: 7425264100768
        //Result: 6827235020033
        //Result: 7192970417739
        //Result: 6714157975331
        //Result: 7497810541907
        //Result: 6435348440385
        //Result: 6999349840672
        //Result: 7435914379978
        //Result: 7715125932481
        //SideEffect parallel sum done in: 49 msecs
        System.out.println("SideEffect parallel sum done in: " +measureSumPerf(ParallelStreamRight::sideEffectParallelSum, 10_000_000L) +"msecs" );
        // 这回方法的性能无关紧要了，唯一要紧的是每次执行都会返回不同的结果，都离正确值
        // 50000005000000 差很远。这是由于多个线程在同时访问累加器，执行 total += value ，而这
        // 一句虽然看似简单，却不是一个原子操作。问题的根源在于， forEach 中调用的方法有副作用，
        // 它会改变多个线程共享的对象的可变状态。要是你想用并行 StreamUtil 又不想引发类似的意外，就
        // 必须避免这种情况。
        // 现在你知道了，共享可变状态会影响并行流以及并行计算。第13章和第14章详细讨论函数式
        // 编程的时候，我们还会谈到这一点。现在，记住要避免共享可变状态，确保并行 StreamUtil 得到正
        // 确的结果。接下来，我们会看到一些实用建议，你可以由此判断什么时候可以利用并行流来提升
        // 性能。

        //7.1.4 高效使用并行流
        // 尽管如此，我们至少可以提出一些定性意见，帮你决定某个特定情况下是否有必要使用并行流。
//          如果有疑问，测量。把顺序流转成并行流轻而易举，但却不一定是好事。我们在本节中
//        已经指出，并行流并不总是比顺序流快。此外，并行流有时候会和你的直觉不一致，所
//        以在考虑选择顺序流还是并行流时，第一个也是最重要的建议就是用适当的基准来检查
//        其性能。
//          留意装箱。自动装箱和拆箱操作会大大降低性能。Java 8中有原始类型流（ IntStream 、
//                LongStream 、 DoubleStream ）来避免这种操作，但凡有可能都应该用这些流。
//          有些操作本身在并行流上的性能就比顺序流差。特别是 limit 和 findFirst 等依赖于元
//                素顺序的操作，它们在并行流上执行的代价非常大。例如， findAny 会比 findFirst 性
//                能好，因为它不一定要按顺序来执行。你总是可以调用 unordered 方法来把有序流变成
//                无序流。那么，如果你需要流中的n个元素而不是专门要前n个的话，对无序并行流调用
//                limit 可能会比单个有序流（比如数据源是一个 List ）更高效。
//          还要考虑流的操作流水线的总计算成本。设N是要处理的元素的总数，Q是一个元素通过
//                流水线的大致处理成本，则N*Q就是这个对成本的一个粗略的定性估计。Q值较高就意味
//                着使用并行流时性能好的可能性比较大。
//          对于较小的数据量，选择并行流几乎从来都不是一个好的决定。并行处理少数几个元素
//                的好处还抵不上并行化造成的额外开销。
//          要考虑流背后的数据结构是否易于分解。例如， ArrayList 的拆分效率比 LinkedList
//                高得多，因为前者用不着遍历就可以平均拆分，而后者则必须遍历。另外，用 range 工厂
//                方法创建的原始类型流也可以快速分解。最后，你将在7.3节中学到，你可以自己实现
//                Spliterator 来完全掌控分解过程。
//          流自身的特点，以及流水线中的中间操作修改流的方式，都可能会改变分解过程的性能。
//                例如，一个 SIZED 流可以分成大小相等的两部分，这样每个部分都可以比较高效地并行处
//                理，但筛选操作可能丢弃的元素个数却无法预测，导致流本身的大小未知。
//          还要考虑终端操作中合并步骤的代价是大是小（例如 Collector 中的 combiner 方法）。
//                如果这一步代价很大，那么组合每个子流产生的部分结果所付出的代价就可能会超出通
//                过并行流得到的性能提升。
//        表7-1按照可分解性总结了一些流数据源适不适于并行。
//        表7-1 流的数据源和可分解性
//          源                     可分解性
//        ArrayList                 极佳
//        LinkedList                 差
//        IntStream.range           极佳
//        StreamUtil.iterate             差
//        HashSet                    好
//        TreeSet                    好
          //7.2 分支/合并框架
          //分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，然后将每个子任
          //务的结果合并起来生成整体结果。它是 ExecutorService 接口的一个实现，它把子任务分配给
          //线程池（称为 ForkJoinPool ）中的工作线程。首先来看看如何定义任务和子任务。

        //7.2.1 使用 RecursiveTask
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }



    public static class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
}
