package com.fengwenyi.mp3demo.java8.collectionstream;

import com.fengwenyi.mp3demo.dto.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author : Caixin
 * @date 2019/7/12 16:51
 */
public class StreamOperate {

    /**
     * stream 流操作
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Dish> menues = Arrays.asList(
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        /**
         *  filter 、 map 和 limit 可以连成一条流水线；
         *  collect 触发流水线执行并关闭它
         *  可以连接起来的流操作称为中间操作，关闭流的操作称为终端操作
         */
        List<String> names = menues
                //从菜单获得流
                .stream()
                //中间操作
                .filter(d -> d.getCalories() > 300)
                //中间操作
                .map(Dish::getName)
                //中间操作
                .limit(3)
                //将 StreamUtil 转换为 List
                .collect(toList());

        List<String> names2 =
                menues.stream()
                        .filter(d -> {
                            System.out.println("filtering" + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping" + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(toList());
//      System.out.println(names2);
        menues.stream().forEach(System.out::println);

//        测验4.1：中间操作与终端操作
//        在下列流水线中，你能找出中间操作和终端操作吗？
//        long count = menu.stream()
//                .filter(d -> d.getCalories() > 300)
//                .distinct()
//                .limit(3)
//                .count();
//        答案：流水线中最后一个操作 count 返回一个 long ，这是一个非 StreamUtil 的值。因此它是
//                打印当
//        前筛选
//                的菜肴
//        提取菜名时
//                打印出来
//        80 第 4章 引入流
//        一个终端操作。所有前面的操作， filter 、 distinct 、 limit ，都是连接起来的，并返回一
//        个 StreamUtil ，因此它们是中间操作。


        //用谓词筛选

        //方法引用检查菜肴是否适合素食者
        List<Dish> vegetarianMenu = menues
                .stream()
                .filter(Dish::isVegetarian)
                .collect(toList());


        //筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        //截短流
        List<Dish> dishes = menues.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        //流还支持 skip(n) 方法，返回一个扔掉了前 n 个元素的流。如果流中元素不足 n 个，则返回一个空流

        //下面的代码将跳过超过300卡路里的头两道菜，并返回剩下的
        List<Dish> dishes2 = menues.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        //对流中每一个元素应用函数
        List<String> dishNames = menues.stream().map(Dish::getName).collect(toList());

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);


        List<Integer> integers = menues.stream().map(Dish::getName).map(String::length).collect(toList());
//        List<Integer> integers = menues.stream().map(d -> d.getName().length()).collect(toList());
        System.out.println(integers);


        List<String> flapMapStrings = Arrays.asList("Hello", "World");

        List<String[]> strings = flapMapStrings.stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(toList());
        //传递给 map 方法的Lambda为每个单词返回了一个 String[] (String列表)

        //尝试使用 map 和 Arrays.stream()

        //你需要一个字符流，而不是数组流。有一个叫作 Arrays.stream() 的方法可以接受
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        //把它用在前面的那个流水线里，看看会发生什么
        List<Stream<String>> streamList = words.stream()
                //将每个单词转换为由其字母构成的数组
                .map(word -> word.split(""))
                //让每个数组变成一个单独的流
                .map(Arrays::stream)
                .distinct()
//                .flatMap(StreamUtil::distinct)
                .collect(toList());

        //使用 flatMap
        List<String> stringList = flapMapStrings.stream()
                //将每个单词转换为由 其字母构成的数组
                .map(s -> s.split(""))
                //将各个生成流扁平化为单个流
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(stringList);


//        测验5.2：映射
//                (1) 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
//                5]，应该返回[1, 4, 9, 16, 25]。
//        答案：你可以利用 map 方法的Lambda，接受一个数字，并返回该数字平方的Lambda来解
//        决这个问题。
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        List<Integer> squares =
//                numbers.stream()
//                        .map(n -> n * n)
//                        .collect(toList());
//        (2) 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
//        该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
//        表数对。
//        答案：你可以使用两个 map 来迭代这两个列表，并生成数对。但这样会返回一个 StreamUtil-
//                <StreamUtil<Integer[]>> 。你需要让生成的流扁平化，以得到一个 StreamUtil<Integer[]> 。这
//        正是 flatMap 所做的：
//        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
//        List<Integer> numbers2 = Arrays.asList(3, 4);
//        List<int[]> pairs =
//                numbers1.stream()
//                        .flatMap(i -> numbers2.stream()
//                                .map(j -> new int[]{i, j})
//                        )
//                        .collect(toList());
//        (3) 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
//        答案：你在前面看到了， filter 可以配合谓词使用来筛选流中的元素。因为在 flatMap
//        操作后，你有了一个代表数对的 int[] 流，所以你只需要一个谓词来检查总和是否能被3整除
//        就可以了：
//        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
//        List<Integer> numbers2 = Arrays.asList(3, 4);
//        List<int[]> pairs =
//                numbers1.stream()
//                        .flatMap(i ->
//                                numbers2.stream()
//                                        .filter(j -> (i + j) % 3 == 0)
//                                        .map(j -> new int[]{i, j})
//                        )
//                        .collect(toList());
//        其结果是[(2, 4), (3, 3)]。


        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerList = numbers2.stream().map(n -> n * n).collect(toList());

        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4, 5);
        List<int[]> ints = numbers3.stream()
                .flatMap(s -> numbers4.stream()
                        .map(j -> new int[]{s, j})
                ).collect(toList());


        List<Integer> numbers5 = Arrays.asList(1, 2, 3);
        List<Integer> numbers6 = Arrays.asList(3, 4);

//        List<int[]> ints2 = numbers5.stream()
//                .flatMap(s -> numbers6.stream()
//                .filter(f -> (f + s) % 3 == 0)
//                .map(f -> new int[]{s, f}))
//                .collect(toList());

        List<int[]> ints1 = numbers5.stream()
                .flatMap(i -> numbers6
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                ).collect(toList());
        System.out.println(ints1);
    }
}
