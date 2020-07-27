package com.fengwenyi.mp3demo.java8.parallelstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/7/19 15:35
 */
public class ParallelStream {
//   第 7 章     并行数据处理与性能
//              本章内容
//                用并行流并行处理数据
//                并行流的性能分析
//                分支/合并框架
//                使用 Spliterator 分割流

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        //7.1 并行流
        // 在第4章中，我们简要地提到了 StreamUtil 接口可以让你非常方便地处理它的元素：
        // 可以通过对收集源调用 parallelStream 方法来把集合转换为并行流。并行流就是一个把内容分成多个数据块，
        // 并用不同的线程分别处理每个数据块的流。

        //假设你需要写一个方法，接受数字n作为参数，并返回从1到给定参数的所有数字的和。
        // 一个直接（也许有点土）的方法是生成一个无穷大的数字流，把它限制到给定的数目，然后用对两个
        //数字求和的 BinaryOperator 来归约这个流，如下所示：
        Long sequentialSum = sequentialSum(98L);
        Long sequentialSum1 = sequentialSumRangeClosed(98L);
        System.out.println(sequentialSum);
        System.out.println(sequentialSum1);
        //7.1.1 将顺序流转换为并行流
        Long sequentialSumParallel = sequentialSumParallel(98L);
        Long sequentialSumParallel2 = sequentialSumRangeClosedParallel(98L);
        System.out.println(sequentialSumParallel);
        System.out.println(sequentialSumParallel2);
        // 在上面的代码中，对流中所有数字求和的归纳过程的执行方式和5.4.1节中说的差不多。
        // 不同之处在于 StreamUtil 在内部分成了几块。因此可以对不同的块独立并行进行归纳操作，如图7-1所示。
        // 最后，同一个归纳操作会将各个子流的部分归纳结果合并起来，得到整个原始流的归纳结果。

        //请注意，在现实中，对顺序流调用 parallel 方法并不意味着流本身有任何实际的变化。它
        //在内部实际上就是设了一个 boolean 标志，表示你想让调用 parallel 之后进行的所有操作都并
        //行执行。类似地，你只需要对并行流调用 sequential 方法就可以把它变成顺序流。请注意，你
        //可能以为把这两个方法结合起来，就可以更细化地控制在遍历流时哪些操作要并行执行，哪些要
        //顺序执行。例如，你可以这样做：

        Integer reduce1 = menu.stream()
                .parallel()
                .filter(Dish::isVegetarian)
                .sequential()
                .map(x -> x.getName().length())
                .parallel()
                .reduce(0, Integer::sum);

        Integer reduce = menu.stream()
                .parallel()
                .filter(Dish::isVegetarian)
                .sequential()
                .map(Dish::getCalories)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println(reduce1);
        // 配置并行流使用的线程池
        // 看看流的 parallel 方法，你可能会想，并行流用的线程是从哪儿来的？有多少个？怎么自定义这个过程呢？
        // 并行流内部使用了默认的 ForkJoinPool （7.2节会进一步讲到分支/合并框架），它默认的
        // 线 程 数 量 就是 你 的 处 理器 数 量 ， 这个 值 是 由 Runtime.getRuntime().availableProcessors() 得到的。
        // 但 是 你 可 以 通 过 系 统 属 性 java.util.concurrent.ForkJoinPool.common.parallelism 来改变线程池大小，如下所示：
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
        // 这是一个全局设置，因此它将影响代码中所有的并行流。反过来说，目前还无法专为某个
        // 并行流指定这个值。一般而言，让 ForkJoinPool 的大小等于处理器数量是个不错的默认值，
        // 除非你有很好的理由，否则我们强烈建议你不要修改它。
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");

        //7.1.2 测量流性能
        //代码清单7-1 测量对前n个自然数求和的函数的性能

        System.out.println("sequentialSumRangeClosed sum done in:" + measureSumPerf(ParallelStream::sequentialSumRangeClosed, 10_000_000) + " msecs");
        System.out.println("sequentialSumRangeClosedParallel sum done in:" + measureSumPerf(ParallelStream::sequentialSumRangeClosedParallel, 10_000_000) + " msecs");


        //Sequential sum done in: 97 msecs
        System.out.println("Sequential sum done in:" + measureSumPerf(ParallelStream::sequentialSum, 10_000_000) + " msecs");
        //Parallel sum done in: 164 msecs
        System.out.println("Parallel sum done in:" + measureSumPerf(ParallelStream::sequentialSumParallel, 10_000_000) + " msecs");
        //Iterative sum done in: 2 msecs
        System.out.println("Iterative sum done in:" + measureSumPerf(ParallelStream::iterativeSum, 10_000_000) + " msecs");

        // 这相当令人失望，求和方法的并行版本比顺序版本要慢很多。你如何解释这个意外的结果呢？这里实际上有两个问题：
        //   iterate 生成的是装箱的对象，必须拆箱成数字才能求和；
        //   我们很难把 iterate 分成多个独立块来并行执行。
        // 第二个问题更有意思一点，因为你必须意识到某些流操作比其他操作更容易并行化。具体来
        // 说， iterate 很难分割成能够独立执行的小块，因为每次应用这个函数都要依赖前一次应用的结
        // 果，如图7-2所示。

        //Ranged sum done in: 17 msecs
        System.out.println("rangedSum sum done in:" + measureSumPerf(ParallelStream::rangedSum, 10_000_000) + " msecs");
        //这个数值流比前面那个用 iterate 工厂方法生成数字的顺序执行版本要快得多，因为数值流
        //避免了非针对性流那些没必要的自动装箱和拆箱操作。由此可见，选择适当的数据结构往往比并
        //行化算法更重要。但要是对这个新版本应用并行流呢？

        //Parallel range sum done in: 1 msecs
        System.out.println("parallelRangedSum sum done in:" + measureSumPerf(ParallelStream::parallelRangedSum, 10_000_000) + " msecs");

    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
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


    public static Long sequentialSum(Long n) {
        Long reduce = Stream.iterate(1L, i -> i + 1) //生成自然数无限流
                .limit(n) //限制到前n个数
                .reduce(0L, Long::sum); //对所有数字求和来归纳流
        return reduce;
    }


    public static Long sequentialSumRangeClosed(Long n) {
        long reduce1 = LongStream.rangeClosed(1, n)//生成自然数无限流
                .reduce(0L, Long::sum);//对所有数字求和来归纳流
        return reduce1;
    }


    public static Long sequentialSumParallel(Long n) {
        Long reduce = Stream.iterate(1L, i -> i + 1) //生成自然数无限流
                .limit(n) //限制到前n个数
                .parallel() //将流转换为并行流
                .reduce(0L, Long::sum); //对所有数字求和来归纳流
        return reduce;
    }


    public static Long sequentialSumRangeClosedParallel(Long n) {
        long reduce1 = LongStream.rangeClosed(1, n)//生成自然数无限流
                .parallel()  //将流转换为并行流
                .reduce(0L, Long::sum);//对所有数字求和来归纳流
        return reduce1;
    }


    //用更为传统的Java术语来说，这段代码与下面的迭代等价：
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }
}
