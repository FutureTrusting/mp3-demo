package com.fengwenyi.mp3demo.java8.anonymous;

import com.fengwenyi.mp3demo.dto.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author : Caixin
 * @date 2019/7/10 11:31
 */
public class ApplesFilter {

    public static void main(String[] args) {

//        List<Apple> inventory = new ArrayList<>();
//        Apple inventory1 = new Apple("red", 2000);
//        Apple inventory2 = new Apple("GREEN", 150);
//        inventory.add(inventory1);
//        inventory.add(inventory2);

        List<Apple> inventory = Arrays.asList(new Apple("red", 2000), new Apple("GREEN", 150));

        //过滤苹果color weight
        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);
        //[Apple(color=red, weight=2000)]


        //打印苹果weight、color
        prettyPrintApple(inventory, new AppleFancyFormatter());
        //A heavy red apple
        //A light GREEN apple

        //第五次尝试：使用匿名类 创建一个用匿名类实现 ApplePredicate 的对象，重写筛选的例子
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        //GUI应用程序中经常使用匿名类来创建事件处理器对象（下面的例子使用的是Java FX API，一种现代的Java UI平台）：
//        ButtonBase button = null;
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Woooo a click!!");
//            }
//        });

        // 第六次尝试：使用 Lambda 表达式
        //上面的代码在Java 8里可以用Lambda表达式重写为下面的样子：
        List<Apple> result =filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        //第七次尝试：将 List 类型抽象化
        // filterApples 方法还只适用于 Apple 。你还可以将 List 类型抽象化，从而超越你眼前要处理的问题
        List<Apple> appleList = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers =filter(numbers, (Integer i) -> i % 2 == 0);


        //Lambda是 Consumer 中 accept 方法的实现
        //你如果需要访问类型 T 的对象，并对其执行某些操作，就可以使用这个接口
        forEach(Arrays.asList(1,2,3,4,5),(Integer i) -> System.out.println(i));

        //它接受一个泛型 T 的对象，并返回一个泛型 R 的对象。如果你需要定义一个Lambda，
        // 将输入对象的信息映射到输出，就可以使用这个接口（比如提取苹果的重量，或把字符串映射为它的长度）。
        //Lambda是 Function 接口的 apply 方法的实现
        List<Integer> len = map(Arrays.asList("lambdas","in","action"),(String s) -> s.length());
        System.out.println(len);

        //Java 8为我们前面所说的函数式接口带来了一个专门的版本，以便在输入和输出都是原始类
        //型时避免自动装箱的操作。比如，在下面的代码中，使用 IntPredicate 就避免了对值 1000 进行
        //装箱操作，但要是用 Predicate<Integer> 就会把参数 1000 装箱到一个 Integer 对象中
        //true （无装箱）
        IntPredicate evenNumber = (int i) -> i % 2 == 0;
        evenNumber.test(1000);

        //false （装箱）
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        oddNumbers.test(1000);


    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }


    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }


    /**
     * 谓词对象封装了测试苹果的条件
     *
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 你需要告诉 prettyPrintApple 方法接受 AppleFormatter 对象，并在内部使用
     * 它们。你可以给 prettyPrintApple 加上一个参数
     *
     * @param inventory
     * @param formatter
     */
    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
