package com.fengwenyi.mp3demo.java8.parallelstream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author : Caixin
 * @date 2019/7/22 11:02
 */
//代码清单7-6  WordCounterSpliterator
public class WordCounterSpliterator implements Spliterator<Character> {
//  7.4 小结
//      在本章中，你了解了以下内容。
//        内部迭代让你可以并行处理一个流，而无需在代码中显式使用和协调不同的线程。
//        虽然并行处理一个流很容易，却不能保证程序在所有情况下都运行得更快。
//          并行软件的行为和性能有时是违反直觉的，因此一定要测量，确保你并没有把程序拖得更慢。
//        像并行流那样对一个数据集并行执行操作可以提升性能，特别是要处理的元素数量庞大，
//          或处理单个元素特别耗时的时候。
//        从性能角度来看，使用正确的数据结构，如尽可能利用原始流而不是一般化的流，几乎
//          总是比尝试并行化某些操作更为重要。
//        分支/合并框架让你得以用递归方式将可以并行的任务拆分成更小的任务，在不同的线程
//          上执行，然后将各个子任务的结果合并起来生成整体结果。
//        Spliterator 定义了并行流如何拆分它要遍历的数据。

    public static void main(String[] args) {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";

        //现在就可以用这个新的 WordCounterSpliterator 来处理并行流了，如下所示：
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        //传给 StreamSupport.stream 工厂方法的第二个布尔参数意味着你想创建一个并行流。把
        //这个并行流传给 countWords 方法：
        //Found 19 words
        System.out.println("Found " + countWords(stream) + " words");

        // 你已经看到了 Spliterator 如何让你控制拆分数据结构的策略。 Spliterator 还有最后一
        // 个值得注意的功能，就是可以在第一次遍历、第一次拆分或第一次查询估计大小时绑定元素的数
        // 据源，而不是在创建时就绑定。这种情况下，它称为延迟绑定（late-binding）的 Spliterator 。
        // 我们专门用附录C来展示如何开发一个工具类来利用这个功能在同一个流上执行多个操作。
    }

    //现在你已经写好了在 WordCounter 中累计字符，以及在 WordCounter 中把它们结合起来的逻辑，那写一个方法来归约 Character 流就很简单了：
    private static int countWords(Stream<Character> stream) {
        ForkJoinSumCalculator.WordCounter wordCounter = stream.reduce(new ForkJoinSumCalculator.WordCounter(0, true),
                ForkJoinSumCalculator.WordCounter::accumulate,
                ForkJoinSumCalculator.WordCounter::combine);
        return wordCounter.getCounter();
    }

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        //处理当前字符
        action.accept(string.charAt(currentChar++));
        //如果还有字符要处理，则返回 true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            //返回 null 表示要解 析 的 String已经足够小，可以顺序处理
            return null;
        }
        //将试探拆分位置设定为要 解 析 的String 的中间
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            //让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                //创 建 一 个 新WordCounterSpliterator来解析 String从开始到拆分位置的部分
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                //将这个 WordCounterSpliterator 的 起 始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }


    // 这个 Spliterator 由要解析的 String 创建，并遍历了其中的 Character ，同时保存了当前正在遍历的字符位置 。
    // 让我们快速回顾一下实现了Spliterator 接 口 的WordCounterSpliterator 中的各个函数。

//      tryAdvance 方法把 String 中当前位置的 Character 传给了 Consumer ，并让位置加一。
//      作为参数传递的 Consumer 是一个Java内部类，在遍历流时将要处理的 Character 传给了
//    一系列要对其执行的函数。这里只有一个归约函数，即 WordCounter 类的 accumulate
//    方法。如果新的指针位置小于 String 的总长，且还有要遍历的 Character ，则
//    tryAdvance 返回 true 。
//      trySplit 方法是 Spliterator 中最重要的一个方法，因为它定义了拆分要遍历的数据
//    结构的逻辑。就像在代码清单7-1中实现的 RecursiveTask 的 compute 方法一样（分支
//     合并框架的使用方式），首先要设定不再进一步拆分的下限。这里用了一个非常低的下
//    限——10个 Character ，仅仅是为了保证程序会对那个比较短的 String 做几次拆分。
//    在实际应用中，就像分支/合并的例子那样，你肯定要用更高的下限来避免生成太多的
//    任务。如果剩余的 Character 数量低于下限，你就返回 null 表示无需进一步拆分。相
//    反，如果你需要执行拆分，就把试探的拆分位置设在要解析的 String 块的中间。但我
//    们没有直接使用这个拆分位置，因为要避免把词在中间断开，于是就往前找，直到找到
//    一个空格。一旦找到了适当的拆分位置，就可以创建一个新的 Spliterator 来遍历从
//    当前位置到拆分位置的子串；把当前位置 this 设为拆分位置，因为之前的部分将由新
//    Spliterator 来处理，最后返回。
//      还需要遍历的元素的 estimatedSize 就是这个 Spliterator 解析的 String 的总长度和
//    当前遍历的位置的差。
//      最后， characteristic 方法告诉框架这个 Spliterator 是 ORDERED （顺序就是 String
//    中各个 Character 的次序）、 SIZED （ estimatedSize 方法的返回值是精确的）、
//    SUBSIZED （ trySplit 方法创建的其他 Spliterator 也有确切大小）、 NONNULL （ String
//    中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
//    Character ，因为 String 本身是一个不可变类）的。


}