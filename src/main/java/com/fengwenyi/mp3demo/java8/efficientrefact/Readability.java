package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.dao.StudentDao;
import com.fengwenyi.mp3demo.dto.*;
import com.fengwenyi.mp3demo.enums.CaloricLevel;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.Task;
import com.fengwenyi.mp3demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.*;

/**
 * @author : Caixin
 * @date 2019/7/22 11:25
 */

@RestController
public class Readability {

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }

    public static void main(String[] args) throws IOException {
        List<Dish> menu = Arrays.asList(
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("salmon", false, 450, Dish.Type.FISH));


        //传统的方式，使用匿名类
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello1");
            }
        };
        //新的方式，使用Lambda表达式
        Runnable r2 = () -> System.out.println("Hello2");
        r1.run();
        r2.run();
        // 但是某些情况下，将匿名类转换为Lambda表达式可能是一个比较复杂的过程
        // ① 。 首先，匿名
        // 类和Lambda表达式中的 this 和 super 的含义是不同的。在匿名类中， this 代表的是类自身，但
        // 是在Lambda中，它代表的是包含类。其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不
        // 能（它们会导致编译错误），譬如下面这段代码：
        //编译错误
//        int a = 10;
//        Runnable r3 = () -> {
//            int a = 2;
//            System.out.println(a);
//        };
        //一切正常
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
//        最后，在涉及重载的上下文里，将匿名类转换为Lambda表达式可能导致最终的代码更加晦
//        涩。实际上，匿名类的类型是在初始化时确定的，而Lambda的类型取决于它的上下文。通过下
//        面这个例子，我们可以了解问题是如何发生的。我们假设你用与 Runnable 同样的签名声明了一
//        个函数接口，我们称之为 Task （你希望采用与你的业务模型更贴切的接口名时，就可能做这样
//        的变更）：
//        interface Task{
//            public void execute();
//        }
//        public static void doSomething(Runnable r){ r.run(); }
//        public static void doSomething(Task a){ a.execute(); }
        // 现在，你再传递一个匿名类实现的 Task ，不会碰到任何问题：
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });

        //麻 烦 来 了 ： doSomething(Runnable) 和doSomething(Task)都匹配该类型
//      doSomething(() -> System.out.println("Danger danger!!"));

        //你可以对 Task 尝试使用显式的类型转换来解决这种模棱两可的情况：
        doSomething((Task) () -> System.out.println("Danger2 danger2!!"));

        //8.1.3 从 Lambda 表达式到方法引用的转换
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream()
                        .collect(
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }));
        //你可以将Lambda表达式的内容抽取到一个单独的方法中，将其作为参数传递给 groupingBy方法。变换之后，代码变得更加简洁，程序的意图也更加清晰了：
        //将Lambda表达式抽取到一个方法内
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel2 =
                menu.stream().collect(groupingBy(Dish::getCaloricLevel));
        List<Apple> inventory = Arrays.asList(new Apple("red", 2000), new Apple("GREEN", 150));
        //你需要考虑如何实现比较算法
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //读起来就像问题描述，非常清晰
        inventory.sort(Comparator.comparing(Apple::getWeight));
        // 此外，很多通用的归约操作，比如 sum 、 maximum ，都有内建的辅助方法可以和方法引用结合使用。
        // 比如，在我们的示例代码中，使用 Collectors 接口可以轻松得到和或者最大值，
        // 与采用Lambada表达式和底层的归约操作比起来，这种方式要直观得多。与其编写：
        int reduce = menu.stream()
                .mapToInt(Dish::getCalories)
                .reduce(0, (x, y) -> x + y);
        // 不如尝试使用内置的集合类，它能更清晰地表达问题陈述是什么。
        // 下面的代码中，我们使用了集 合类 summingInt （方法的名词很直观地解释了它的功能）：
        Integer integer = menu.stream()
                .collect(summingInt(Dish::getCalories));
        //
        Integer integer2 = menu.stream().mapToInt(Dish::getCalories).sum();

        //8.1.4 从命令式的数据处理切换到 StreamUtil
        //比如，下面的命令式代码使用了两种模式：筛选和抽取，这两种模式被混在了一起，这样的
        //代码结构迫使程序员必须彻底搞清楚程序的每个细节才能理解代码的功能。此外，实现需要并行
        //运行的程序所面对的困难也多得多（具体细节可以参考7.2节的分支/合并框架）：
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }
        //替代方案使用Stream API，采用这种方式编写的代码读起来更像是问题陈述，并行化也非常容易：
        List<String> stringList = menu.parallelStream()
                .filter(x -> x.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
        //8.1.5 增加代码的灵活性
//        1 采用函数接口
//        首先，你必须意识到，没有函数接口，你就无法使用Lambda表达式。因此，你需要在代码
//        中引入函数接口。听起来很合理，但是在什么情况下使用它们呢？这里我们介绍两种通用的模式，
//        你可以依照这两种模式重构代码，利用Lambda表达式带来的灵活性，它们分别是：有条件的延
//        迟执行和环绕执行。除此之外，在下一节，我们还将介绍一些基于面向对象的设计模式，比如策
//        略模式或者模板方法，这些在使用Lambda表达式重写后会更简洁。

//        2. 有条件的延迟执行
//        我们经常看到这样的代码，控制语句被混杂在业务逻辑代码之中。典型的情况包括进行安全
//        性检查以及日志输出。比如，下面的这段代码，它使用了Java语言内置的 Logger 类：
//        if (logger.isLoggable(Log.FINER)){
//            logger.finer("Problem: " + generateDiagnostic());
//        }
//        这段代码有什么问题吗？其实问题不少。
//          日志器的状态（它支持哪些日志等级）通过 isLoggable 方法暴露给了客户端代码。
//          为什么要在每次输出一条日志之前都去查询日志器对象的状态？这只能搞砸你的代码。
//        更好的方案是使用 log 方法，该方法在输出日志消息之前，会在内部检查日志对象是否已经
//        设置为恰当的日志等级：
//        logger.log(Level.FINER, "Problem: " + generateDiagnostic());

//        Java 8的API设计者们已经意识到这个问题，并由此引入了一个对 log 方法的重载版本，这个版本
//        的 log 方法接受一个 Supplier 作为参数。这个替代版本的 log 方法的函数签名如下：
//        public void log(Level level, Supplier<String> msgSupplier)
//        你可以通过下面的方式对它进行调用：
//        logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
//        如果日志器的级别设置恰当， log 方法会在内部执行作为参数传递进来的Lambda表达式。
//        这里介绍的 Log 方法的内部实现如下：
//        public void log(Level level, Supplier<String> msgSupplier){
//            if(logger.isLoggable(level)){
        //执行Lambda表达式
//                log(level, msgSupplier.get());
//            }
//        }

        //3. 环绕执行
        //传入一个Lambda表达式
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        //传 入 另 一个 Lambda表达式
        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());

        //8.2 使用 Lambda 重构面向对象的设计模式
        //这一节中，我们会针对五个设计模式展开讨论，它们分别是：
        //      策略模式
        //      模板方法
        //      观察者模式
        //      责任链模式
        //      工厂模式

        // 8.2.1 策略模式
//      策略模式包含三部分内容，如图8-1所示。
//       一个代表某个算法的接口（它是策略模式的接口）。
//       一个或多个该接口的具体实现，它们代表了算法的多种实现（比如，实体类 ConcreteStrategyA 或者 ConcreteStrategyB ）。
//       一个或多个使用策略对象的客户。

        //8.2.2 模板方法
//      如果你需要采用某个算法的框架，同时又希望有一定的灵活度，能对它的某些部分进行改进，
//      那么采用模板方法设计模式是比较通用的方案。好吧，这样讲听起来有些抽象。换句话说，模板
//      方法模式在你“希望使用这个算法，但是需要对其中的某些行进行改进，才能达到希望的效果”时是非常有用的。

//      让我们从一个例子着手，看看这个模式是如何工作的。假设你需要编写一个简单的在线银行应用。
//      通常，用户需要输入一个用户账户，之后应用才能从银行的数据库中得到用户的详细信息，
//      最终完成一些让用户满意的操作。不同分行的在线银行应用让客户满意的方式可能还略有不同，
//      比如给客户的账户发放红利，或者仅仅是少发送一些推广文件。你可能通过下面的抽象类方式来
//      实现在线银行应用：

        //8.2.3 观察者模式
//        观察者模式是一种比较常见的方案，某些事件发生时（比如状态转变），
//        如果一个对象（通常我们称之为主题）需要自动地通知其他多个对象（称为观察者），就会采用该方案。
//        创建图形   用户界面（GUI）程序时，你经常会使用该设计模式。这种情况下，你会在图形用户界面组件（比
//        如按钮）上注册一系列的观察者。如果点击按钮，观察者就会收到通知，并随即执行某个特定的
//        行为。 但是观察者模式并不局限于图形用户界面。比如，观察者设计模式也适用于股票交易的
//        情形，多个券商可能都希望对某一支股票价格（主题）的变动做出响应。图8-2通过UML图解释
//        了观察者模式。

        //8.2.4 责任链模式
//        责任链模式是一种创建处理对象序列（比如操作序列）的通用方案。一个处理对象可能需要
//        在完成一些工作之后，将结果传递给另一个对象，这个对象接着做一些工作，再转交给下一个处
//        理对象，以此类推。
//        通常，这种模式是通过定义一个代表处理对象的抽象类来实现的，在抽象类中会定义一个字
//        段来记录后续对象。一旦对象完成它的工作，处理对象就会将它的工作转交给它的后继。代码中，
//        这段逻辑看起来是下面这样：


        //8.2.5 工厂模式
//        使用工厂模式，你无需向客户暴露实例化的逻辑就能完成对象的创建。


    }

    //比如，我们假设你希望保存具有三个参数（两个参数为 Integer 类型，一个参数为 String
    //类型）的构造函数；为了完成这个任务，你需要创建一个特殊的函数接口 TriFunction 。最终
    //的结果是 Map 变得更加复杂
    public interface TriFunction<T, U, V, R>{
        R apply(T t, U u, V v);
    }
    Map<String, TriFunction<Integer, Integer, String, Product>> map= new HashMap<>();


    @Test
    //8.2.5 工厂模式
    @GetMapping("/factoryMode")
    public void factoryMode() {
        Product p = ProductFactory.createProduct("loan");
        System.out.println(p);
        // 使用Lambda表达式
        // 第3章中，我们已经知道可以像引用方法一样引用构造函数。比如，下面就是一个引用贷款
        // （ Loan ）构造函数的示例：
        Supplier<Product> loanSupplier = Loan::new;
        Loan loan = (Loan) loanSupplier.get();

    }
    //通过这种方式，你可以重构之前的代码，创建一个 Map ，将产品名映射到对应的构造函数：
    final static Map<String, Supplier<Product>> map2 = new HashMap<>();
    static {
        map2.put("loan", Loan::new);
        map2.put("stock", Stock::new);
        map2.put("bond", Bond::new);
    }

    //现在，你可以像之前使用工厂设计模式那样，利用这个 Map 来实例化不同的产品。
    public static Product createProduct(String name){
        Supplier<Product> p = map2.get(name);
        if(p != null) {
            return p.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }










    //可能你已经注意到，这就是8.2.2节介绍的模板方法设计模式。 handle 方法提供了如何进行
    //工作处理的框架。不同的处理对象可以通过继承 ProcessingObject 类，提供 handleWork 方法来进行创建
    public abstract class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }

    //下面让我们看看如何使用该设计模式。你可以创建两个处理对象，它们的功能是进行一些文本处理工作
    public class HeaderTextProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    public class SpellCheckerProcessing extends ProcessingObject<String> {
        @Override
        public String handleWork(String text) {
            //糟糕，我们漏掉了Lambda中的m字符
            return text.replaceAll("labda", "lambda");
        }
    }

    @Test
    //8.2.4 责任链模式
    @GetMapping("/chainOfResponsibilityModel")
    public void chainOfResponsibilityModel() {
        //现在你就可以将这两个处理对象结合起来，构造一个操作序列！
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        //将两个处理对象链接起来
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        //打印输出“From Raoul, Mario and Alan: Aren't lambdas really sexy?!!”
        System.out.println(result);
        //  使用Lambda表达式
        //  稍等！这个模式看起来像是在链接（也即是构造） 函数。第3章中我们探讨过如何构造Lambda
        //  表达式。你可以将处理对象作为函数的一个实例，或者更确切地说作为 UnaryOperator-
        //  <String> 的一个实例。为了链接这些函数，你需要使用 andThen 方法对其进行构造。

        //第一个处理对象
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        //第二个处理对象
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        //将两个方法结合起来，结果就是一个操作链
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result2);
    }


    @Test
    //8.2.3 观察者模式
    @GetMapping("/observerMode")
    public void observerMode() {
        //这是一个非常直观的实现： Feed 类在内部维护了一个观察者列表，一条新闻到达时，它就进行通知。
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen,money,wine said her favourite book is Java 8 in Action!");
        // 毫不意外，《卫报》会特别关注这条新闻！
        // 使用Lambda表达式
        // 你可能会疑惑Lambda表达式在观察者设计模式中如何发挥它的作用。不知道你有没有注意
        // 到， Observer 接口的所有实现类都提供了一个方法： notify 。新闻到达时，它们都只是对同一
        // 段代码封装执行。Lambda表达式的设计初衷就是要消除这样的僵化代码。使用Lambda表达式后，
        // 你无需显式地实例化三个观察者对象，直接传递Lambda表达式表示需要执行的行为即可：
        Feed f2 = new Feed();
        f2.registerObserver((tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println(">>>>>>>>>>>Breaking news in NY! " + tweet);
            }
        }));
        f2.registerObserver((tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println(">>>>>>>>>>>Yet another news in London... " + tweet);
            }
        }));
        f2.notifyObservers("queen.money   fsfdfsdfdfdfdfdfd");
    }

    //首先，你需要一个观察者接口，它将不同的观察者聚合在一起。它仅有一个名为 notify 的方法，一旦接收到一条新的新闻，该方法就会被调用：
    interface Observer {
        void notify(String tweet);
    }

    //现在，你可以声明不同的观察者（比如，这里是三家不同的报纸机构），依据新闻中不同的关键字分别定义不同的行为：
    class NYTimes implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    class Guardian implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        }
    }

    class LeMonde implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

    //你还遗漏了最重要的部分： Subject ！让我们为它定义一个接口：
    interface Subject {
        void registerObserver(Observer o);

        void notifyObservers(String tweet);
    }

    //Subject 使用 registerObserver 方法可以注册一个新的观察者，使用 notifyObservers方法通知它的观察者一个新闻的到来。
    // 让我们更进一步，实现 Feed 类：
    class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(o -> o.notify(tweet));
        }
    }

    @Autowired
    private StudentDao studentDao;

    @Test
    //8.2.2 模板方法
    @GetMapping("/templateMethod")
    public void templateMethod() {
        //现在，你可以很方便地通过传递Lambda表达式，直接插入不同的行为，不再需要继承OnlineBanking 类了：
        new OnlineBankingLambda().processCustomer(10, (Student student) ->
                System.out.println("Hello " + student.getName()));
    }

    abstract class OnlineBanking {
        //processCustomer 方法搭建了在线银行算法的框架：获取客户提供的ID，然后提供服务让用户满意。
        // 不同的支行可以通过继承 OnlineBanking 类，对该方法提供差异化的实现。
        public void processCustomer(int id) {
            Student student = studentDao.selectById(id);
            makeCustomerHappy(student);
//            Customer c = Database.getCustomerWithId(id);
//            makeCustomerHappy(c);
        }

        abstract void makeCustomerHappy(Student student);

        // abstract void makeCustomerHappy(Customer c);
        // 使用你偏爱的Lambda表达式同样也可以解决这些问题（创建算法框架，让具体的实现插入某些部分）。
        // 你想要插入的不同算法组件可以通过Lambda表达式或者方法引用的方式实现。
        // 这里我们向 processCustomer 方法引入了第二个参数，它是一个 Consumer<Customer> 类
        // 型的参数，与前文定义的 makeCustomerHappy 的特征保持一致：
//        public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
//            Customer c = Database.getCustomerWithId(id);
//            makeCustomerHappy.accept(c);
//        }
        public void processCustomer(int id, Consumer<Student> makeCustomerHappy) {
            Student student = studentDao.selectById(id);
            makeCustomerHappy.accept(student);
        }
    }

    public class OnlineBankingLambda extends OnlineBanking {
        @Override
        void makeCustomerHappy(Student student) {

        }
    }


    @Test
    // 8.2.1 策略模式
    public void strategyModeTest() {
        Validator numericValidator = new Validator(new IsNumeric());
        //返回 false
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        //返回 true
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b1);
        System.out.println(b2);
        //  使用Lambda表达式
        //  到现在为止，你应该已经意识到 ValidationStrategy 是一个函数接口了（除此之外，它
        //  还与 Predicate<String> 具有同样的函数描述）。这意味着我们不需要声明新的类来实现不同
        //  的策略，通过直接传递Lambda表达式就能达到同样的目的，并且还更简洁：

        //直接传递Lambda表达式
        Validator numericValidator1 = new Validator(s -> s.matches("[a-z]+"));
        boolean aaaa = numericValidator1.validate("aaaa");
        Validator lowerCaseValidator2 = new Validator(d -> d.matches("\\d+"));
        boolean bbbb = lowerCaseValidator2.validate("bbbb");
        //  正如你看到的，Lambda表达式避免了采用策略设计模式时僵化的模板代码。如果你仔细分
        //  析一下个中缘由，可能会发现，Lambda表达式实际已经对部分代码（或策略）进行了封装，而
        //  这就是创建策略设计模式的初衷。因此，我们强烈建议对类似的问题，你应该尽量使用Lambda
        //  表达式来解决。
    }


    //我们假设你希望验证输入的内容是否根据标准进行了恰当的格式化（比如只包含小写字母或数字）。
    // 你可以从定义一个验证文本（以 String 的形式表示）的接口入手：
    public interface ValidationStrategy {
        boolean execute(String s);
    }

    //其次，你定义了该接口的一个或多个具体实现：
    public class IsAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    public class IsNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    //之后，你就可以在你的程序中使用这些略有差异的验证策略了：
    public class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy v) {
            this.strategy = v;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }


    public static String processFile(BufferedReaderProcessor p) throws
            IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("java8inaction/chap8/data.txt"))) {
            //将 BufferedReaderProcessor 作为执行参数传入
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        //使用Lambda表达式的函数接口，该接口能够抛出一个 IOException
        String process(BufferedReader b) throws IOException;
    }

}
