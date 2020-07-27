package com.fengwenyi.mp3demo.java8.expression;

import com.fengwenyi.mp3demo.java8.anonymous.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author : Caixin
 * @date 2019/7/11 14:42
 */
public class LambdaExpression {

    /**
     *         Lambda语法
     *         根据上述语法规则，以下哪个不是有效的Lambda表达式？
     *         (1)  () -> {}
     *         (2)  () -> "Raoul"
     *         (3)  () -> {return "Mario";}
     *         (4)  (Integer i) -> return "Alan" + i;
     *         (5)  (String s) -> {"IronMan";}
     *         答案：只有4和5是无效的Lambda。
     *         (1) 这个Lambda没有参数，并返回 void 。它类似于主体为空的方法： public void run() {} 。
     *         (2) 这个Lambda没有参数，并返回 String 作为表达式。
     *         (3) 这个Lambda没有参数，并返回 String （利用显式返回语句）。
     *         (4)  return 是一个控制流语句。要使此Lambda有效，需要使花括号，如下所示：
     *         (Integer i) -> {return "Alan" + i;} 。
     *         (5)“Iron Man”是一个表达式，不是一个语句。要使此Lambda有效，你可以去除花括号
     *         和分号，如下所示： (String s) -> "Iron Man" 。或者如果你喜欢，可以使用显式返回语
     *         句，如下所示： (String s)->{return "IronMan";} 。
     */

    /**
     * 测验3.2：函数式接口
     * 下面哪些接口是函数式接口？
     * public interface Adder{
     * int add(int a, int b);
     * }
     * public interface SmartAdder extends Adder{
     * int add(double a, double b);
     * }
     * public interface Nothing{
     * }
     * 答案：只有 Adder 是函数式接口。
     * SmartAdder 不是函数式接口，因为它定义了两个叫作 add 的抽象方法（其中一个是从
     * Adder 那里继承来的）。
     * Nothing 也不是函数式接口，因为它没有声明抽象方法。
     * <p>
     * 用函数式接口可以干什么呢？Lambda表达式允许你直接以内联的形式为函数式接口的抽象
     * 方法提供实现，并把整个表达式作为函数式接口的实例（具体说来，是函数式接口一个具体实现
     * 的实例）。
     */


    public static void main(String[] args) throws IOException {
        //使用Lambda
        Runnable r1 = () -> System.out.println("Hello World 1");

        //使用匿名类
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        // 打印“ Hello World 1 ”
        process(r1);
        //打印“ Hello World 2 ”
        process(r2);

        //利用直接传递的Lambda 打印“ Hello World 3 ”
        process(() -> System.out.println("Hello World 3"));


        /**
         *         测验3.3：在哪里可以使用Lambda？
         *         以下哪些是使用Lambda表达式的有效方式？
         *         (1) execute(() -> {});
         *         public void execute(Runnable r){
         *             r.run();
         *         }
         *         (2) public Callable<String> fetch() {
         *             return () -> "Tricky example ;-)";
         *         }
         *         (3) Predicate<Apple> p = (Apple a) -> a.getWeight();
         *         答案：只有1和2是有效的。
         *         第一个例子有效，是因为Lambda () -> {} 具有签名 () -> void ，这和 Runnable 中的
         *         抽象方法 run 的签名相匹配。请注意，此代码运行后什么都不会做，因为Lambda是空的！
         *         第二个例子也是有效的。事实上， fetch 方法的返回类型是 Callable<String> 。
         *         Callable<String> 基本上就定义了一个方法，签名是 () -> String ，其中 T 被 String 代替
         *         了。因为Lambda () -> "Trickyexample;-)" 的签名是 () -> String ，所以在这个上下文
         *         中可以使用Lambda。
         *         第三个例子无效，因为Lambda表达式 (Apple a) -> a.getWeight() 的签名是 (Apple) ->
         *         Integer ，这和 Predicate<Apple>:(Apple) -> boolean 中定义的 test 方法的签名不同。
         */


        /**
         *         @FunctionalInterface 又是怎么回事？
         *         如果你去看看新的Java API，会发现函数式接口带有 @FunctionalInterfaceExpression 的标注（3.4
         *         节中会深入研究函数式接口，并会给出一个长长的列表）。这个标注用于表示该接口会设计成
         *         一个函数式接口。如果你用 @FunctionalInterfaceExpression 定义了一个接口，而它却不是函数式接
         *         口的话，编译器将返回一个提示原因的错误。例如，错误消息可能是“Multiple non-overriding
         *         abstract methods found in interface Foo”，表明存在多个抽象方法。请注意， @FunctionalInter-
         *                 face 不是必需的，但对于为此设计的接口而言，使用它是比较好的做法。它就像是 @Override
         *         标注表示方法被重写了。
         */


        //现在你就可以通过传递不同的Lambda重用 processFile 方法，并以不同的方式处理文件了。
        //处理一行
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);

        //处理两行：
        String processFile = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(processFile);


        List<String> listOfIntegers = Arrays.asList("a", "", "c", "d", "f");

//        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
//        List<String> nonEmpty = filter(listOfIntegers,nonEmptyStringPredicate);

        List<String> nonEmpty = filter(listOfIntegers,(String s) -> !s.isEmpty());
        System.out.println(nonEmpty);

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }


    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\happyGGQ\\Desktop\\加密版本更新.txt"))) {
            //处理 BufferedReader 对象
            return p.process(br);
        }
    }


    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static void process(Runnable r) {
        r.run();
    }
}
