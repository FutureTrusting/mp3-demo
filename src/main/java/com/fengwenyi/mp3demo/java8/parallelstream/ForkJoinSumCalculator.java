package com.fengwenyi.mp3demo.java8.parallelstream;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.fengwenyi.mp3demo.java8.parallelstream.ParallelStreamRight.measureSumPerf;

/**
 * @author : Caixin
 * @date 2019/7/19 17:16
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> { //继承RecursiveTask来创建可以用于分支/合并框架的任务

    //要求和的数组
    private final long[] numbers;
    //子任务处理的数组的起始和终止位置
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000; //不再将任务分解为子任务的数组大小

    //公共构造函数用于创建主任务
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    //私有构造函数用于以递归方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() { //覆盖 RecursiveTask抽象方法
        //该任务负责求和的部分的大小
        int length = end - start;
        //如果大小小于或等于阈值，顺序计算结果
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        //创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        //利用另 一 个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        //创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        //该任务的结果是两个子任务结果的组合
        return leftResult + rightResult;

        // 这里用了一个 LongStream 来生成包含前n个自然数的数组，然后创建一个 ForkJoinTask
        // （ RecursiveTask 的父类），并把数组传递给代码清单7-2所示 ForkJoinSumCalculator 的公共
        // 构造函数。最后，你创建了一个新的 ForkJoinPool ，并把任务传给它的调用方法 。在
        // ForkJoinPool 中执行时，最后一个方法返回的值就是 ForkJoinSumCalculator 类定义的任务
        // 结果。
        // 请注意在实际应用时，使用多个 ForkJoinPool 是没有什么意义的。正是出于这个原因，一
        // 般来说把它实例化一次，然后把实例保存在静态字段中，使之成为单例，这样就可以在软件中任
        // 何部分方便地重用了。这里创建时用了其默认的无参数构造函数，这意味着想让线程池使用JVM
        // 能够使用的所有处理器。更确切地说，该构造函数将使用 Runtime.availableProcessors 的
        // 返回值来决定线程池使用的线程数。请注意 availableProcessors 方法虽然看起来是处理器，
        // 但它实际上返回的是可用内核的数量，包括超线程生成的虚拟内核。
        // 运行 ForkJoinSumCalculator
        // 当把 ForkJoinSumCalculator 任务传给 ForkJoinPool 时，这个任务就由池中的一个线程
        // 执行，这个线程会调用任务的 compute 方法。该方法会检查任务是否小到足以顺序执行，如果不
        // 够小则会把要求和的数组分成两半，分给两个新的 ForkJoinSumCalculator ，而它们也由
        // ForkJoinPool 安排执行。因此，这一过程可以递归重复，把原任务分为更小的任务，直到满足
        // 不方便或不可能再进一步拆分的条件（本例中是求和的项目数小于等于10 000）。这时会顺序计
        // 算每个任务的结果，然后由分支过程创建的（隐含的）任务二叉树遍历回到它的根。接下来会合
        // 并每个子任务的部分结果，从而得到总任务的结果。这一过程如图7-4所示。
    }

    public static void main(String[] args) {
        //你可以再用一次本章开始时写的测试框架，来看看显式使用分支/合并框架的求和方法的性能：
        //ForkJoin sum done in: 41 msecs
        System.out.println("ForkJoin sum done in: " + measureSumPerf(
                ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");
        //这个性能看起来比用并行流的版本要差，但这只是因为必须先要把整个数字流都放进一个long[] ，
        // 之后才能在 ForkJoinSumCalculator 任务中使用它。

        //7.2.2 使用分支/合并框架的最佳做法
        //虽然分支/合并框架还算简单易用，不幸的是它也很容易被误用。以下是几个有效使用它的最佳做法。
//        对一个任务调用 join 方法会阻塞调用方，直到该任务做出结果。因此，有必要在两个子
//      任务的计算都开始之后再调用它。否则，你得到的版本会比原始的顺序算法更慢更复杂，
//      因为每个子任务都必须等待另一个子任务完成才能启动。
//        不应该在 RecursiveTask 内部使用 ForkJoinPool 的 invoke 方法。相反，你应该始终直
//      接调用 compute 或 fork 方法，只有顺序代码才应该用 invoke 来启动并行计算。
//        对子任务调用 fork 方法可以把它排进 ForkJoinPool 。同时对左边和右边的子任务调用
//      它似乎很自然，但这样做的效率要比直接对其中一个调用 compute 低。这样做你可以为
//      其中一个子任务重用同一线程，从而避免在线程池中多分配一个任务造成的开销。
//        调试使用分支/合并框架的并行计算可能有点棘手。特别是你平常都在你喜欢的IDE里面
//      看栈跟踪（stack trace）来找问题，但放在分支合并计算上就不行了，因为调用 compute
//      的线程并不是概念上的调用方，后者是调用 fork 的那个。
//        和并行流一样，你不应理所当然地认为在多核处理器上使用分支/合并框架就比顺序计
//      算快。我们已经说过，一个任务可以分解成多个独立的子任务，才能让性能在并行化时
//      有所提升。所有这些子任务的运行时间都应该比分出新任务所花的时间长；一个惯用方
//      法是把输入/输出放在一个子任务里，计算放在另一个里，这样计算就可以和输入/输出
//      同时进行。此外，在比较同一算法的顺序和并行版本的性能时还有别的因素要考虑。就
//      像任何其他Java代码一样，分支/合并框架需要“预热”或者说要执行几遍才会被JIT编
//      译器优化。这就是为什么在测量性能之前跑几遍程序很重要，我们的测试框架就是这么
//      做的。同时还要知道，编译器内置的优化可能会为顺序版本带来一些优势（例如执行死
//      码分析——删去从未被使用的计算）。
//      对于分支/合并拆分策略还有最后一点补充：你必须选择一个标准，来决定任务是要进一步
//      拆分还是已小到可以顺序求值。我们会在下一节中就此给出一些提示。

        //7.3  Spliterator
        // Spliterator 是Java 8中加入的另一个新接口；这个名字代表“可分迭代器”（splitable
        // iterator）。和 Iterator 一样， Spliterator 也用于遍历数据源中的元素，但它是为了并行执行
        // 而设计的。虽然在实践中可能用不着自己开发 Spliterator ，但了解一下它的实现方式会让你
        // 对并行流的工作原理有更深入的了解。Java 8已经为集合框架中包含的所有数据结构提供了一个
        // 默认的 Spliterator 实现。集合实现了 Spliterator 接口，接口提供了一个 spliterator 方法。
        // 这个接口定义了若干方法，如下面的代码清单所示。

        //7.3.1 拆分过程
        //7.3.2 实现你自己的 Spliterator
        //让我们来看一个可能需要你自己实现 Spliterator 的实际例子。
        // 我们要开发一个简单的方z法来数数一个 String 中的单词数。这个方法的一个迭代版本可以写成下面的样子。

//        String name="周 杰伦 ml xg jung le";
//        int iteratively = countWordsIteratively(name);
//        System.out.println(iteratively);
        //让我们把这个方法用在但丁的《神曲》的《地狱篇》的第一句话上：①
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        //Found 19 words
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        //1. 以函数式风格重写单词计数器
        //首先你需要把 String 转换成一个流。不幸的是，原始类型的流仅限于 int 、 long 和 double ，所以你只能用 StreamUtil<Character>
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        //现在你就可以试一试这个方法，给它由包含但丁的《神曲》中《地狱篇》第一句的 String创建的流：
        //Found 19 words
        System.out.println("Found " + countWords(stream) + " words");
        //让 WordCounter 并行工作
        //Found 25 words
        System.out.println("Found " + countWords(stream.parallel()) + " words");
        // 因为原始的 String 在任意 位置拆分，所以有时一个词会被分为两个词，然后数了两次。
        // 这就说明，拆分流会影响结果，而把顺序流换成并行流就可能使结果出错。
        // 如何解决这个问题呢？解决方案就是要确保 String 不是在随机位置拆开的，而只能在词尾拆开。
        // 要做到这一点，你必须为 Character 实现一个 Spliterator ，它只能在两个词之间拆开 String （如下所示），然后由此创建并行流。


    }




    //现在你已经写好了在 WordCounter 中累计字符，以及在 WordCounter 中把它们结合起来的逻辑，那写一个方法来归约 Character 流就很简单了：
    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }


    //代码清单7-5 用来在遍历 Character 流时计数的类
    static class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) { //和迭代算 法 一 样 ，accumulate 方 法 一个个遍历 Character
            if (Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            } else {
                //上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
                return lastSpace ? new WordCounter(counter + 1, false) : this;
            }
        }

        //合并两个 Word-Counter ，把其计数器加起来
        public WordCounter combine(WordCounter wordCounter) {
            //仅需要计数器的总和，无需关心 lastSpace
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }


    //代码清单7-4 一个迭代式字数统计方法
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) { //逐个遍历String中的所有字符
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) { //上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }


    //在 子 任务 不 再可 分 时计 算 结果 的 简单算法
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    //现在编写一个方法来并行对前n个自然数求和就很简单了。
    // 你只需把想要的数字数组传给ForkJoinSumCalculator 的构造函数：
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n)
                .toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}