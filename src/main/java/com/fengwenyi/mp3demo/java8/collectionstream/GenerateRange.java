package com.fengwenyi.mp3demo.java8.collectionstream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/7/15 15:25
 */
public class GenerateRange {
    public static void main(String[] args) {
        // 创建流的方法还有许多！本节将介绍如何从值序列、数组、文件来创建流，甚至由生成函数来创建无限流！
        // 你可以使用静态方法 StreamUtil.of ，通过显式值创建一个流。它可以接受任意数量的参数。
        // 例如，以下代码直接使用 StreamUtil.of 创建了一个字符串流。然后，你可以将字符串转换为大写，再一个个打印出来
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);
        // 你可以使用 empty 得到一个空流，如下所示：
        Stream<String> emptyStream = Stream.empty();
        emptyStream.forEach(System.out::println);
        // 你可以使用静态方法 Arrays.stream 从数组创建一个流。它接受一个数组作为参数。
        // 例如，你可以将一个原始类型 int 的数组转换成一个 IntStream ，如下所示：
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream stream1 = Arrays.stream(numbers);
//        StreamUtil<Integer> integerStream = stream1.boxed();
//        Integer reduce = integerStream.reduce(0, (x, y) -> x + y);
//        System.out.println(reduce);
        int sum = stream1.sum();
        System.out.println(sum);
//      由文件生成流
//      Java中用于处理文件等I/O操作的NIO API（非阻塞 I/O）已更新，以便利用Stream API。
//      java.nio.file.Files 中的很多静态方法都会返回一个流。例如，一个很有用的方法是
//      Files.lines ，它会返回一个由指定文件中的各行构成的字符串流
        long uniqueWords = 0;
        //流会自动关闭
        try (Stream<String> lines = Files.lines(Paths.get("Output.json"), Charset.defaultCharset())) {
           uniqueWords =
                    lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    //删除重复项
                    .distinct()
//                    .forEach(System.out::println);
                    //数一数有多少各不相同的单词
                  .count();
        } catch (IOException e) {
            //如果打开文件时出现异常则加以处理
            e.printStackTrace();
        }
        // 你可以使用 Files.lines 得到一个流，其中的每个元素都是给定文件中的一行。然后，你可以对 line 调用 split 方法将行拆分成单词。
        // 应该注意的是，你该如何使用 flatMap 产生一个扁平的单词流，而不是给每一行生成一个单词流。
        // 最后，把 distinct 和 count 方法链接起来，数数流中有多少个不相同的单词
        System.out.println("uniqueWords"+uniqueWords);

//         由函数生成流：创建无限流
//         StreamUtil API提供了两个静态方法来从函数生成流： StreamUtil.iterate 和 StreamUtil.generate 。
//         这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。由 iterate
//         和 generate 产生的流会用给定的函数按需创建值，因此可以无穷无尽地计算下去！一般来说，
//         应该使用 limit(n) 来对这种流加以限制，以避免打印无穷多个值。
        Stream.iterate(0,n->n+2)
                .limit(10)
                .forEach(System.out::println);
//        iterate 方法接受一个初始值（在这里是 0 ），还有一个依次应用在每个产生的新值上的
//        Lambda（ UnaryOperator<t> 类型）。这里，我们使用Lambda  n -> n + 2 ，返回的是前一个元
//        素加上2。因此， iterate 方法生成了一个所有正偶数的流：流的第一个元素是初始值 0 。然后加
//        上 2 来生成新的值 2 ，再加上 2 来得到新的值 4 ，以此类推。这种 iterate 操作基本上是顺序的，
//        因为结果取决于前一次应用。请注意，此操作将生成一个无限流——这个流没有结尾，因为值是
//        按需计算的，可以永远计算下去。我们说这个流是无界的。正如我们前面所讨论的，这是流和集
//        合之间的一个关键区别。我们使用 limit 方法来显式限制流的大小。这里只选择了前10个偶数。
//        然后可以调用 forEach 终端操作来消费流，并分别打印每个元素。


//        测验5.4：斐波纳契元组序列
//        斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分：0, 1, 1,
//                2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。
//        斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1),
//        (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
//        你的任务是用 iterate 方法生成斐波纳契元组序列中的前20个元素。
//        让我们帮你入手吧。第一个问题是， iterate 方法要接受一个 UnaryOperator<t> 作为
//        参数，而你需要一个像(0,1)这样的元组流。你还是可以（这次又是比较草率地）使用一个数组
//        的两个元素来代表元组。例如， new int[]{0,1} 就代表了斐波纳契序列(0, 1)中的第一个元
//        素。这就是 iterate 方法的初始值：
//        StreamUtil.iterate(new int[]{0, 1}, ???)
//              .limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
//        在这个测验中，你需要搞清楚 ??? 代表的代码是什么。请记住， iterate 会按顺序应用给
//        定的Lambda。
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .forEach(t->System.out.println("("+t[0]+","+t[1]+")"));

        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .map(t->t[0])
                .forEach(System.out::println);
//        答案：
//        StreamUtil.iterate(new int[]{0, 1},
//                t -> new int[]{t[1], t[0]+t[1]})
//                .limit(20)
//                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
//        它是如何工作的呢？ iterate 需要一个Lambda来确定后续的元素。对于元组(3, 5)，其后
//        续元素是(5, 3+5) = (5, 8)。下一个是(8, 5+8)。看到这个模式了吗？给定一个元组，其后续的元
//        素是(t[1], t[0] + t[1])。这可以用这个Lambda来计算： t->new int[]{t[1], t[0]+t[1]} 。
//        运行这段代码，你就得到了序列(0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)…请注意，
//        如果你只想打印正常的斐波纳契数列，可以使用 map 提取每个元组中的第一个元素：
//        StreamUtil.iterate(new int[]{0, 1},
//                t -> new int[]{t[1],t[0] + t[1]})
//                .limit(10)
//                .map(t -> t[0])
//                .forEach(System.out::println);
//        这段代码将生成斐波纳契数列：0, 1, 1, 2, 3, 5, 8, 13, 21, 34…

//        2. 生成
//        与 iterate 方法类似， generate 方法也可让你按需生成一个无限流。但 generate 不是依次
//        对每个新生成的值应用函数的。它接受一个 Supplier<T> 类型的Lambda提供新的值。我们先来
//        看一个简单的用法：
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        //我们在这个例子中会使用 IntStream 说明避免装箱操作的代码。
        // IntStream 的 generate 方法会接受一个 IntSupplier ，而不是 Supplier<t> 。例如，可以这样来生成一个全是1的无限流：
//        IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
        //等价于
        IntStream intStream = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5);
//        intStream.forEach(System.out::println);

//        generate 方法将使用给定的供应源，并反复调用 getAsInt 方法，而这个方法总是返回 2 。
//        但这里使用的匿名类和Lambda的区别在于，匿名类可以通过字段定义状态，而状态又可以用
//        getAsInt 方法来修改。这是一个副作用的例子。你迄今见过的所有Lambda都是没有副作用的；
//        它们没有改变任何状态。

        //下面的代码就是如何创建一个在调用时返回下一个斐波纳契项的
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);

//          Streams API可以表达复杂的数据处理查询。常用的流操作总结在表5-1中。
//          你可以使用 filter 、 distinct 、 skip 和 limit 对流做筛选和切片。
//          你可以使用 map 和 flatMap 提取或转换流中的元素。
//          你可以使用 findFirst 和 findAny 方法查找流中的元素。你可以用 allMatch 、
//                noneMatch 和 anyMatch 方法让流匹配给定的谓词。
//          这些方法都利用了短路：找到结果就立即停止计算；没有必要处理整个流。
//          你可以利用 reduce 方法将流中所有的元素迭代合并成一个结果，例如求和或查找最大
//                元素。
//          filter 和 map 等操作是无状态的，它们并不存储任何状态。 reduce 等操作要存储状态才
//                能计算出一个值。 sorted 和 distinct 等操作也要存储状态，因为它们需要把流中的所
//                有元素缓存起来才能返回一个新的流。这种操作称为有状态操作。
//          流有三种基本的原始类型特化： IntStream 、 DoubleStream 和 LongStream 。它们的操
//                作也有相应的特化。
//          流不仅可以从集合创建，也可从值、数组、文件以及 iterate 与 generate 等特定方法
//                创建。
//          无限流是没有固定大小的流。















    }
}
