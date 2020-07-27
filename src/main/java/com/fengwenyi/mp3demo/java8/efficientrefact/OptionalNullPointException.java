package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Car;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Insurance;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Person;
import com.fengwenyi.mp3demo.java8.utils.OptionalUtility;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Caixin
 * @date 2019/7/22 18:27
 */
public class OptionalNullPointException {
    //10.1 如何为缺失的值建模
    @Test
    public void testOptional() {
//        String insuranceName = getCarInsuranceName4(new Person());
        //10.1.1 采用防御式检查减少 NullPointerException
        // 这个方法每次引用一个变量都会做一次 null 检查，如果引用链上的任何一个遍历的解变量
        // 值为 null ，它就返回一个值为“Unknown”的字符串。唯一的例外是保险公司的名字，你不需
        // 要对它进行检查，原因很简单，因为任何一家公司必定有个名字。注意到了吗，由于你掌握业务
        // 领域的知识，避免了最后这个检查，但这并不会直接反映在你建模数据的Java类之中。

        //10.1.2  null 带来的种种问题
//          它是错误之源。
//            NullPointerException 是目前Java程序开发中最典型的异常。
//          它会使你的代码膨胀。
//                它让你的代码充斥着深度嵌套的 null 检查，代码的可读性糟糕透顶。
//          它自身是毫无意义的。
//                null 自身没有任何的语义，尤其是，它代表的是在静态类型语言中以一种错误的方式对
//                缺失变量值的建模。
//          它破坏了Java的哲学。
//                Java一直试图避免让程序员意识到指针的存在，唯一的例外是： null 指针。
//          它在Java的类型系统上开了个口子。
//            null 并不属于任何类型，这意味着它可以被赋值给任意引用类型的变量。这会导致问题，
//            原因是当这个变量被传递到系统中的另一个部分后，你将无法获知这个 null 变量最初的
//            赋值到底是什么类型。
//        为了解业界针对这个问题给出的解决方案，我们一起简单看看其他语言提供了哪些功能。
        //10.1.3 其他语言中 null 的替代品
        //10.2  OptionalNullPointException 类入门
        //10.3 应用 OptionalNullPointException 的几种模式
        //10.3.1 创建 OptionalNullPointException 对象
        //1. 声明一个空的 Optional, 正如前文已经提到，你可以通过静态工厂方法 OptionalNullPointException.empty ，创建一个空的 Optional对象：
        Optional<Car> optCar = Optional.empty();
        //2. 依据一个非空值创建 Optional,你还可以使用静态工厂方法 Optional.of ，依据一个非空值创建一个 Optional 对象：
        Car car = new Car();
        Optional<Car> optCar1 = Optional.of(car);
        //如果 car 是一个 null ，这段代码会立即抛出一个 NullPointerException ，而不是等到你试图访问 car 的属性值时才返回一个错误。
        //3. 可接受 null 的 Optional,最后，使用静态工厂方法 Optional.ofNullable ，你可以创建一个允许 null 值的 Optional对象：
        Optional<Car> optionalCar = Optional.ofNullable(car);
        //如果 car 是 null ，那么得到的 Optional 对象就是个空对象。
    }

    public static void main(String[] args) {
        //10.3.2 使用 map 从 Optional 对象中提取和转换值

        //从对象中提取信息是一种比较常见的模式。比如，你可能想要从 insurance 公司对象中提取
        //公司的名称。提取名称之前，你需要检查 insurance 对象是否为 null ，代码如下所示：
        Insurance insurance = new Insurance();
//        String name = null;
//        if (insurance != null) {
//            name = insurance.getName();
//        }
        //为了支持这种模式， Optional 提供了一个 map 方法。它的工作方式如下（这里，我们继续借用了代码清单10-4的模式）：
        Optional<Insurance> insurance1 = Optional.ofNullable(insurance);
        Optional<String> name2 = insurance1.map(Insurance::getName);

        //10.3.3 使用 flatMap 链接 Optional 对象
        Person person = new Person();
        Optional<Person> optPerson = Optional.ofNullable(person);
//        Optional<String> uOptional = optPerson.map(Person::getCar)
//                .map(Car::getInsurance)
//                .map(Insurance::getName);

//        Optional<String> stringOptional = optPerson.flatMap(Person::getCar)
//                .flatMap(Car::getInsurance)
//                .flatMap(Insurance::getName);
//        String unknown = stringOptional.orElse("unknown");
//        System.out.println(unknown);

        // 不幸的是，这段代码无法通过编译。为什么呢？ optPerson 是 Optional<Person> 类型的
        // 变量， 调用 map 方法应该没有问题。但 getCar 返回的是一个 Optional<Car> 类型的对象（如代
        // 码清单10-4所示），这意味着 map 操作的结果是一个 Optional<Optional<Car>> 类型的对象。因
        // 此，它对 getInsurance 的调用是非法的，因为最外层的 optional 对象包含了另一个 optional
        // 对象的值，而它当然不会支持 getInsurance 方法。图10-3说明了你会遭遇的嵌套式 optional结构。

        // 所以，我们该如何解决这个问题呢？让我们再回顾一下你刚刚在流上使用过的模式：
        // flatMap 方法。使用流时， flatMap 方法接受一个函数作为参数，这个函数的返回值是另一个流。
        // 这个方法会应用到流中的每一个元素，最终形成一个新的流的流。但是 flagMap 会用流的内容替
        // 换每个新生成的流。换句话说，由方法生成的各个流会被合并或者扁平化为一个单一的流。这里
        // 你希望的结果其实也是类似的，但是你想要的是将两层的 optional 合并为一个。

        //1. 使用 Optional 获取 car 的保险公司名称
        //相信现在你已经对 Optional 的 map 和 flatMap 方法有了一定的了解，让我们看看如何应用。
        //代码清单10-2和代码清单10-3的示例用基于 Optional 的数据模式重写之后，如代码清单10-5所示。


//        在域模型中使用 Optional ，以及为什么它们无法序列化
//        在代码清单10-4中，我们展示了如何在你的域模型中使用 Optional ，将允许缺失或者暂
//        无定义的变量值用特殊的形式标记出来。然而， Optional 类设计者的初衷并非如此，他们构
//        思时怀揣的是另一个用例。这一点，Java语言的架构师Brian Goetz曾经非常明确地陈述过，
//        Optional 的设计初衷仅仅是要支持能返回 Optional 对象的语法。
//        由于 Optional 类设计时就没特别考虑将其作为类的字段使用，所以它也并未实现
//        Serializable 接口。由于这个原因，如果你的应用使用了某些要求序列化的库或者框架，在
//        域模型中使用 Optional ，有可能引发应用程序故障。然而，我们相信，通过前面的介绍，你
//        已经看到用 Optional 声明域模型中的某些类型是个不错的主意，尤其是你需要遍历有可能全
//        部或部分为空，或者可能不存在的对象时。如果你一定要实现序列化的域模型，作为替代方案，
//        我们建议你像下面这个例子那样，提供一个能访问声明为 Optional 、变量值可能缺失的接口，
//        代码清单如下：
//        public class Person {
//            private Car car;
//            public Optional<Car> getCarAsOptional() {
//                return Optional.ofNullable(car);
//            }
//        }

        //10.3.4 默认行为及解引用 Optional 对象
//        我们决定采用 orElse 方法读取这个变量的值，使用这种方式你还可以定义一个默认值，遭遇空的 Optional 变量时，默认值会作为该方法的调用返回值。 Optional 类提供了多种方法读取

//        Optional 实例中的变量值。
//          get() 是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量
//                值，否则就抛出一个 NoSuchElementException 异常。所以，除非你非常确定 Optional
//                变量一定包含值，否则使用这个方法是个相当糟糕的主意。此外，这种方式即便相对于嵌套式的 null 检查，也并未体现出多大的改进。
//          orElse(T other) 是我们在代码清单10-5中使用的方法，正如之前提到的，它允许你在
//                Optional 对象不包含值时提供一个默认值。
//          orElseGet(Supplier<? extends T> other) 是 orElse 方法的延迟调用版， Supplier
//                方法只有在 Optional 对象不含值时才执行调用。如果创建默认值是件耗时费力的工作，
//                你应该考虑采用这种方式（借此提升程序的性能），或者你需要非常确定某个方法仅在
//                Optional 为空时才进行调用，也可以考虑该方式（这种情况有严格的限制条件）。
//          orElseThrow(Supplier<? extends X> exceptionSupplier) 和 get 方法非常类似，
//                它们遭遇 Optional 对象为空时都会抛出一个异常，但是使用 orElseThrow 你可以定制希
//                望抛出的异常类型。
//          ifPresent(Consumer<? super T>) 让你能在变量值存在时执行一个作为参数传入的
//                方法，否则就不进行任何操作。
//                Optional 类和 StreamUtil 接口的相似之处，远不止 map 和 flatMap 这两个方法。还有第三个方
//                法 filter ，它的行为在两种类型之间也极其相似，我们会在10.3.6节做进一步的介绍。

        //10.3.5 两个 Optional 对象的组合

        // 我们还假设你想要该方法的一个 null -安全的版本，它接受两个 Optional 对象作为参数，
        // 返回值是一个 Optional<Insurance> 对象，如果传入的任何一个参数值为空，它的返回值亦为
        // 空。 Optional 类还提供了一个 isPresent 方法，如果 Optional 对象包含值，该方法就返回 true ，
        // 所以你的第一想法可能是通过下面这种方式实现该方法：

//        测验10.1：以不解包的方式组合两个 Optional 对象
//        结合本节中介绍的 map 和 flatMap 方法，用一行语句重新实现之前出现的 nullSafeFindCheapestInsurance() 方法。
//        答案：你可以像使用三元操作符那样，无需任何条件判断的结构，以一行语句实现该方法，
//        代码如下。
//        public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
//            return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
//        }
//        这段代码中，你对第一个 Optional 对象调用 flatMap 方法，如果它是个空值，传递给它
//        的Lambda表达式不会执行，这次调用会直接返回一个空的 Optional 对象。反之，如果 person
//        对象存在，这次调用就会将其作为函数 Function 的输入，并按照与 flatMap 方法的约定返回
//        一个 Optional<Insurance> 对象。这个函数的函数体会对第二个 Optional 对象执行 map 操
//        作，如果第二个对象不包含 car ，函数 Function 就返回一个空的 Optional 对象，整个
//        nullSafeFindCheapestInsuranc 方法的返回值也是一个空的 Optional 对象。最后，如果
//        person 和 car 对象都存在，作为参数传递给 map 方法的Lambda表达式能够使用这两个值安全
//        地调用原始的 findCheapestInsurance 方法，完成期望的操作。

        //10.3.6 使用 filter 剔除特定的值
        // 你经常需要调用某个对象的方法，查看它的某些属性。比如，你可能需要检查保险公司的名
        // 称是否为“Cambridge-Insurance”。为了以一种安全的方式进行这些操作，你首先需要确定引用指
        // 向的 Insurance 对象是否为 null ，之后再调用它的 getName 方法，如下所示：
        Insurance insurance2 = new Insurance();
        if (insurance2 != null && "CambridgeInsurance".equals(insurance.getName())) {
            System.out.println("ok");
        }
        //使用 Optional 对象的 filter 方法，这段代码可以重构如下：
        Optional<Insurance> insurance3 = Optional.ofNullable(insurance2);
        insurance3.filter(x -> "CambridgeInsurance".equals(x.getName()))
                .ifPresent(System.out::println);

        Insurance orElse1 = insurance3.filter(x -> "CambridgeInsurance".equals(x.getName()))
                .orElse(null);

        // filter 方法接受一个谓词作为参数。如果 Optional 对象的值存在，并且它符合谓词的条件，
        // filter 方法就返回其值；否则它就返回一个空的 Optional 对象。如果你还记得我们可以将
        // Optional 看成最多包含一个元素的 StreamUtil 对象，这个方法的行为就非常清晰了。如果 Optional
        // 对象为空，它不做任何操作，反之，它就对 Optional 对象中包含的值施加谓词操作。如果该操
        // 作的结果为 true ，它不做任何改变，直接返回该 Optional 对象，否则就将该值过滤掉，将
        // Optional 的值置空。通过测验10.2，可以测试你对 filter 方法工作方式的理解

//        测验10.2：对 Optional 对象进行过滤
//        假设在我们的 Person / Car / Insurance 模型中， Person 还提供了一个方法可以取得
//        Person 对象的年龄，请使用下面的签名改写代码清单10-5中的 getCarInsuranceName 方法：
//        public String getCarInsuranceName(Optional<Person> person, int minAge)
//        找出年龄大于或者等于 minAge 参数的 Person 所对应的保险公司列表。
//        答案：你可以对 Optional 封装的 Person 对象进行 filter 操作，设置相应的条件谓词，
//        即如果 person 的年龄大于 minAge 参数的设定值，就返回该值，并将谓词传递给 filter 方法，
//        代码如下所示。
//        public String getCarInsuranceName(Optional<Person> person, int minAge) {
//            return person.filter(p -> p.getAge() >= minAge)
//                    .flatMap(Person::getCar)
//                    .flatMap(Car::getInsurance)
//                    .map(Insurance::getName)
//                    .orElse("Unknown");
//        }

        //10.4 使用 Optional 的实战示例
        //10.4.1 用 Optional 封装可能为 null 的值

        //我们接着用 Map 做例子，假设你有一个 Map<String, Object> 方法，访问由 key 索引的值时，
        // 如果 map中没有与 key 关联的值，该次调用就会返回一个 null 。
        Map<String, Object> map = new HashMap<>(88);
        Object value = map.get("key");

        //使用 Optional 封装 map 的返回值，你可以对这段代码进行优化。
        // 要达到这个目的有两种方式：你可以使用笨拙的 if-then-else 判断语句，毫无疑问这种方式会增加代码的复杂度；
        // 或者你可以采用我们前文介绍的 Optional.ofNullable 方法：
        Optional<Object> value2 = Optional.ofNullable(map.get("key"));
        //每次你希望安全地对潜在为 null 的对象进行转换，将其替换为 Optional 对象时，都可以考虑使用这种方法

        //10.4.2 异常与 Optional 的对比

        // 你也可以用空的 Optional 对象，对遭遇无法转换的 String 时返回的非法值进行建模，这时
        // 你期望 parseInt 的返回值是一个 optional 。我们无法修改最初的Java方法，但是这无碍我们进
        // 行需要的改进，你可以实现一个工具方法，将这部分逻辑封装于其中，最终返回一个我们希望的
        // Optional 对象，代码如下所示。

        Integer orElse2 = stringToInt("").orElse(88888888);
        System.out.println(orElse2);


        //10.4.3 把所有内容整合起来

        // 为了展示之前介绍过的 Optional 类的各种方法整合在一起的威力，我们假设你需要向你的程序传递一些属性。
        // 为了举例以及测试你开发的代码，你创建了一些示例属性，如下所示：
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(0, readDuration(props, "c"));
        assertEquals(0, readDuration(props, "d"));
        assertEquals(5, readDuration2(props, "a"));
        assertEquals(0, readDuration2(props, "b"));


//        测验10.3：使用 Optional 从属性中读取 duration
//        请尝试使用 Optional 类提供的特性及代码清单10-6中提供的工具方法，通过一条精炼的
//        语句重构代码清单10-7中的方法。

//        答案：如果需要访问的属性值不存在， Properties.getProperty(String) 方法的返回
//        值就是一个 null ，使用 ofNullable 工厂方法非常轻易地就能把该值转换为 Optional 对象。接
//        着，你可以向它的 flatMap 方法传递代码清单10-6中实现的 OptionalUtility.stringToInt
//        方法的引用，将 Optional<String> 转换为 Optional<Integer> 。最后，你非常轻易地就可
//        以过滤掉负数。这种方式下，如果任何一个操作返回一个空的 Optional 对象，该方法都会返
//        回 orElse 方法设置的默认值 0 ；否则就返回封装在 Optional 对象中的正整数。下面就是这段
//        简化的实现：
//        public int readDuration(Properties props, String name) {
//            return Optional.ofNullable(props.getProperty(name))
//                    .flatMap(OptionalUtility::stringToInt)
//                    .filter(i -> i > 0)
//                    .orElse(0);
//        }

        //注意到使用 Optional 和 StreamUtil 时的那些通用模式了吗？它们都是对数据库查询过程的反思，查询时，多种操作会被串接在一起执行

//        10.5 小结
//        这一章中，你学到了以下的内容。
//          null 引用在历史上被引入到程序设计语言中，目的是为了表示变量值的缺失。
//          Java 8中引入了一个新的类 java.util.Optional<T> ，对存在或缺失的变量值进行建模。
//          你可以使用静态工厂方法 Optional.empty 、 Optional.of 以及 Optional.ofNullable 创建 Optional 对象。
//          Optional 类支持多种方法，比如 map 、 flatMap 、 filter ，它们在概念上与 StreamUtil 类中对应的方法十分相似。
//          使用 Optional 会迫使你更积极地解引用 Optional 对象，以应对变量值缺失的问题，最终，你能更有效地防止代码中出现不期而至的空指针异常。
//          使用 Optional 能帮助你设计更好的API，用户只需要阅读方法签名，就能了解该方法是否接受一个 Optional 类型的值。

    }

    public static int readDuration2(Properties props, String name) {
        Optional<String> optionalS = Optional.ofNullable(props.getProperty(name));
        Integer integer = optionalS.flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
        return integer;
    }

    //代码清单10-7 以命令式编程的方式从属性中读取 duration 值
    public static int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        //确保名称对应的属性存在
        if (value != null) {
            try {
                //将 String 属性转换为数字类型
                int i = Integer.parseInt(value);
                //检查返回的数字是否为正数
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
            }
        }
        //如果前述的条件都不满足，返回0
        return 0;
    }

    //代码清单10-6 将 String 转换为 Integer ，并返回一个 Optional 对象
    public static Optional<Integer> stringToInt(String s) {
        try {
            //如果 String 能转换为对应的 Integer ，将其封装在 Optioal 对象中返回
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            //否则返回一个空的 Optional 对象
            return Optional.empty();
        }
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
        Optional<Insurance> insurance = person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
        return insurance;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        return new Insurance();
    }

    public class Person1 {
        private Car car;

        public Optional<Car> getCar() {
            return Optional.ofNullable(car);
        }
    }


    //代码清单10-5 使用 Optional 获取 car 的 Insurance 名称
    public String getCarInsuranceName4(Person person) {
        Optional<Person> optPerson = Optional.ofNullable(person);
        String orElse = optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                //如果 Optional 的结果值为空，设置默认值
                .orElse("Unknown");
        return orElse;
    }


    //代码清单10-3  null -安全的第二种尝试：过多的退出语句
//    public String getCarInsuranceName3(Person person) {
//        if (person == null) {
//            //每个 null 检查都会添加新的退出点
//            return "Unknown";
//        }
//        Car car = person.getCar();
//        if (car == null) {
//            //每个 null 检查都会添加新的退出点
//            return "Unknown";
//        }
//        Insurance insurance = car.getInsurance();
//        if (insurance == null) {
//            //每个 null 检查都会添加新的退出点
//            return "Unknown";
//        }
//        return insurance.getName();
//    }

    //代码清单10-2  null -安全的第一种尝试：深层质疑
//    public String getCarInsuranceName2(Person person) {
//        //每个 null 检查都会增加调用链上剩余代码的嵌套层数
//        if (person != null) {
//            Car car = person.getCar();
//            //每个 null 检查都会增加调用链上剩余代码的嵌套层数
//            if (car != null) {
//                Insurance insurance = car.getInsurance();
//                //每个 null 检查都会增加调用链上剩余代码的嵌套层数
//                if (insurance != null) {
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unknown";
//    }

    //那么，下面这段代码存在怎样的问题呢？
//    public String getCarInsuranceName(Person person) {
//        return person.getCar().getInsurance().getName();
//    }


    //假设你需要处理下面这样的嵌套对象，这是一个拥有汽车及汽车保险的客户。
    //代码清单10-1  Person / Car / Insurance 的数据模型
//    @Getter
//    public class Person {
//        private Car car;
//    }

//    @Getter
//    public class Car {
//        private Insurance insurance;
//    }

//    @Getter
//    public class Insurance {
//        private String name;
//    }

}
