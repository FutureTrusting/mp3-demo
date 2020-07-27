package com.fengwenyi.mp3demo.java8.expression;

import com.fengwenyi.mp3demo.dto.Apple;
import com.fengwenyi.mp3demo.java8.anonymous.TriFunction;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;

/**
 * @author : Caixin
 * @date 2019/7/11 16:00
 */
public class FunctionalInterfaceExpression {
    /**
     * 测验3.4：函数式接口
     * 对于下列函数描述符（即Lambda表达式的签名），你会使用哪些函数式接口？在表3-2中
     * 可以找到大部分答案。作为进一步练习，请构造一个可以利用这些函数式接口的有效Lambda
     * 表达式：
     * (1)  T->R
     * (2)  (int, int)->int
     * (3)  T->void
     * (4)  ()->T
     * (5)  (T, U)->R
     * 答案如下。
     * (1)  Function<T,R> 不错。它一般用于将类型 T 的对象转换为类型 R 的对象（比如
     * Function<Apple, Integer> 用来提取苹果的重量）。
     * (2)  IntBinaryOperator 具有唯一一个抽象方法，叫作 applyAsInt ，它代表的函数描述
     * 符是 (int, int) -> int 。
     * (3)  Consumer<T> 具有唯一一个抽象方法叫作 accept ，代表的函数描述符是 T -> void 。
     * (4)  Supplier<T> 具有唯一一个抽象方法叫作 get ，代表的函数描述符是 ()-> T 。或者，
     * Callable<T> 具有唯一一个抽象方法叫作 call ，代表的函数描述符是 () -> T 。
     * (5)  BiFunction<T, U, R> 具有唯一一个抽象方法叫作 apply ，代表的函数描述符是 (T,
     * U) -> R 。
     */

    public static void main(String[] args) {

        //没有类型推断
        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        //有类型推断
        Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

        ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        /**
         *     测验3.5：类型检查——为什么下面的代码不能编译呢？
         *     你该如何解决这个问题呢？
         *     Object o = () -> {System.out.println("Tricky example"); };
         *     答案：Lambda表达式的上下文是 Object （目标类型）。但 Object 不是一个函数式接口。
         *     为了解决这个问题，你可以把目标类型改成 Runnable ，它的函数描述符是 () -> void ：
         *     Runnable r = () -> {System.out.println("Tricky example"); };
         */

        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        r.run();
        //错误：Lambda表达式引用的局部变量必须是最终的（ final ）或事实上最终的

        List<Apple> inventory = Arrays.asList(new Apple("PURPLE", 120), new Apple("ORANGE", 118));
        System.out.println(inventory);

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //你的第一个 方法引用！
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);


        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);

        /**
         *   测验3.6：方法引用
         *   下列Lambda表达式的等效方法引用是什么？
         *   (1) Function<String, Integer> stringToInteger =
         *   (String s) -> Integer.parseInt(s);
         *   (2) BiPredicate<List<String>, String> contains =
         *   (list, element) -> list.contains(element);
         *   答案如下。
         *   (1) 这个Lambda表达式将其参数传给了 Integer 的静态方法 parseInt 。这种方法接受一
         *   个需要解析的 String ，并返回一个 Integer 。因此，可以使用图3-5中的办法➊（Lambda表达
         *   式调用静态方法）来重写Lambda表达式，如下所示：
         *   Function<String, Integer> stringToInteger = Integer::parseInt;
         *   (2) 这个Lambda使用其第一个参数，调用其 contains 方法。由于第一个参数是 List 类型
         *   的，你可以使用图3-5中的办法➋，如下所示：
         *   BiPredicate<List<String>, String> contains = List::contains;
         *   这是因为，目标类型描述的函数描述符是  (List<String>,String) -> boolean ，而
         *   List::contains 可以被解包成这个函数描述符。
         */

        Function<String, Integer> stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;


        Supplier<Apple> appleSupplier = Apple::new;      // 构造函数引用指向默认   的 Apple() 构造函数
        Apple a1 = appleSupplier.get(); //调用 Supplier 的 get 方法 将产生一个新的 Apple
        //这就等价于：
        Supplier<Apple> appleSupplier2 = () -> new Apple();//利用默认构造函数创建的Lambda表达式
        Apple a2 = appleSupplier2.get();  //调用 Supplier 的 get 方法 将产生一个新的 Apple


        //如果你的构造函数的签名是 Apple(Integer weight) ，那么它就适合 Function 接口的签名，于是你可以这样写
        Function<Integer, Apple> apple3 = Apple::new;//指向 Apple(Integer weight)的构造函数引用
        Apple apply3 = apple3.apply(110);   //调用该 Function 函数的 apply 方法，并给出要求的重量，将产生一个 Apple
        //这就等价于：
        Function<Integer, Apple> apple4 = (weight) -> new Apple(weight);
        Apple apply4 = apple4.apply(110);

        //在下面的代码中，一个由 Integer 构成的 List 中的每个元素都通过我们前面定义的类似的map 方法传递给了 Apple 的构造函数，得到了一个具有不同重量苹果的 List ：
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        //如果你有一个具有两个参数的构造函数 Apple(String color, Integer weight) ，那么它就适合 BiFunction 接口的签名，于是你可以这样写
        BiFunction<String, Integer, Apple> biFunction = Apple::new;//指向 Apple(String color,Integer weight) 的构造函数引用
        Apple apply = biFunction.apply("green", 110); //调用该 BiFunction 函数的 apply方法，并给出要求的颜色和重量，将产生一个新的 Apple 对象

        //但是你需要与构造函数引用的签名匹配的函数式接口。但是语言本身并没有提供这样的函数式接口，你可以自己创建一个：
        TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
        Color color = colorFactory.apply(1, 2, 3);
        System.out.println(color);
    }


    /**
     * 测验3.7：构造函数引用
     * 你已经看到了如何将有零个、一个、两个参数的构造函数转变为构造函数引用。那要怎么
     * 样才能对具有三个参数的构造函数，比如 Color(int, int, int)， 使用构造函数引用呢？
     * 答案：你看，构造函数引用的语法是 ClassName::new ，那么在这个例子里面就是
     * Color::new 。但是你需要与构造函数引用的签名匹配的函数式接口。但是语言本身并没有提
     * 供这样的函数式接口，你可以自己创建一个：
     * public interface TriFunction<T, U, V, R>{
     * R apply(T t, U u, V v);
     * }
     * 现在你可以像下面这样使用构造函数引用了：
     * TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
     */


//    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
//
//    static {
//        Function<Integer, Fruit> apple = map.put("apple", weight -> new Apple(weight));
//    }
//    public static Fruit giveMeFruit(String fruit, Integer weight){
//        return map.get(fruit.toLowerCase())
//                .apply(weight);
//    }
    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }
}
