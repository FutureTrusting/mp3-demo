package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.java8.efficientrefact.service.impl.G;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.impl.Z;
import org.junit.jupiter.api.Test;

/**
 * @author : Caixin
 * @date 2019/7/22 17:27
 */
public class DefaultInterface {
    //  Java 8中的抽象类和抽象接口
    //  那么抽象类和抽象接口之间的区别是什么呢？它们不都能包含抽象方法和包含方法体的
    //  实现吗？
    //  首先，一个类只能继承一个抽象类，但是一个类可以实现多个接口。
    //  其次，一个抽象类可以通过实例变量（字段）保存一个通用状态，而接口是不能有实例变
    //  量的。

    //  测验9.1： removeIf
    //  这个测验里，假设你是Java语言和API的一个负责人。你收到了关于 removeIf 方法的很多
    //  请求，希望能为 ArrayList 、 TreeSet 、 LinkedList 以及其他集合类型添加 removeIf 方法。
    //  removeIf 方法的功能是删除满足给定谓词的所有元素。你的任务是找到添加这个新方法、优
    //  化Collection API的最佳途径。
    //  答案：改进Collection API破坏性最大的方式是什么？你可以把 removeIf 的实现直接复制
    //  到Collection API的每个实体类中，但这种做法实际是在对Java界的犯罪。还有其他的方式吗？
    //  你知道吗，所有的 Collection 类都实现了一个名为 java.util.Collection 的接口。太好
    //  了，那么我们可以在这里添加一个方法？是的！你只需要牢记，默认方法是一种以源码兼容方
    //  式向接口内添加实现的方法。这样实现 Collction 的所有类（包括并不隶属Collection API的
    //  用户扩展类）都能使用 removeIf 的默认实现。 removeIf 的代码实现如下（它实际就是Java 8
    //  Collection API的实现）。它是 Collection 接口的一个默认方法：
    //    default boolean removeIf(Predicate<? super E> filter) {
    //        Objects.requireNonNull(filter);
    //        boolean removed = false;
    //        final Iterator<E> each = iterator();
    //        while (each.hasNext()) {
    //            if (filter.test(each.next())) {
    //                each.remove();
    //                removed = true;
    //            }
    //        }
    //        return removed;
    //    }

    //现在你已经了解了默认方法怎样以兼容的方式演进库函数了。
    //这一节中，我们会介绍使用默认方法的两种用例：可选方法和行为的多继承。
    //9.3.1 可选方法
    //9.3.2 行为的多继承
    //默认方法让之前无法想象的事儿以一种优雅的方式得以实现，即行为的多继承。这是一种让类从多个来源重用代码的能力，如图9-3所示。

    // 1. 类型的多继承
//    这个例子中 ArrayList 继承了一个类，实现了六个接口。因此 ArrayList 实际是七个类型
//    的直接子类，分别是： AbstractList 、 List 、 RandomAccess 、 Cloneable 、 Serializable 、
//    Iterable 和 Collection 。所以，在某种程度上，我们早就有了类型的多继承。

    //2. 利用正交方法的精简接口
    //3. 组合接口

    //Monster 类会自动继承 Rotatable 、 Moveable 和 Resizable 接口的默认方法。这个例子中，
//    Monster 继承了 rotateBy 、 moveHorizontally 、 moveVertically 和 setRelativeSize 的
//    实现。
    //通过组合这些接口，你现在可以为你的游戏创建不同的实体类。比如， Monster 可以移动、旋转和缩放。
    public class Monster implements Rotatable, Moveable, Resizable {

        @Override
        public void setRotationAngle(int angleInDegrees) {

        }

        @Override
        public int getRotationAngle() {
            return 0;
        }

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public void setWidth(int width) {

        }

        @Override
        public void setHeight(int height) {

        }

        @Override
        public void setAbsoluteSize(int width, int height) {

        }
    }
    //    你现在可以直接调用不同的方法：
    @Test
    public  void testRotatable() {
        //构造函数会设置 Monster 的坐标、高度、宽度及默认仰角
        Monster m = new Monster();
        //调 用 由 Rotatable 中继承而来的 rotateBy
        m.rotateBy(180);
        //调用由 Moveable 中继承而来的 moveVertically
        m.moveVertically(10);
        // 假设你现在需要声明另一个类，它要能移动和旋转，但是不能缩放，比如说 Sun 。这时也无
        // 需复制粘贴代码，你可以像下面这样复用 Moveable 和 Rotatable 接口的默认实现。图9-4是这一场景的UML图表。
    }
    //  //9.4 解决冲突的规则
    public interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }
    public interface B extends A {
        @Override
        default void hello() {
            System.out.println("Hello from B");
        }
    }
    public static class C implements B, A {
        public static void main(String... args) {
            //Hello from B
            new C().hello();
        }
    }
        // 9.4.1 解决问题的三条规则
    //  如果一个类使用相同的函数签名从多个地方（比如另一个类或接口）继承了方法，通过三条
    //  规则可以进行判断。
    //  (1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
    //  (2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
    //      拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体。
    //  (3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现。

        //9.4.2 选择提供了最具体实现的默认方法的接口

    //现在，我们看看如果 C 像下面这样（如图9-6所示）继承自 D ，会发生什么情况：

//    测验9.2：牢记这些判断的规则
//    我们在这个测验中继续复用之前的例子，唯一的不同在于 D 现在显式地覆盖了从 A 接口中
//    继承的 hello 方法。你认为现在的输出会是什么呢？
//    public class D implements A{
//        void hello(){
//            System.out.println("Hello from D");
//        }
//    }
//    public class C extends D implements B, A {
//        public static void main(String... args) {
//            new C().hello();
//        }
//    }
//    答案：由于依据规则(1)，父类中声明的方法具有更高的优先级，所以程序会打印输出“Hello
//    from D”。
//    注意， D 的声明如下：
//    public abstract class D implements A {
//        public abstract void hello();
//    }
//    这样的结果是，虽然在结构上，其他的地方已经声明了默认方法的实现， C 还是必须提供
//    自己的 hello 方法

    //9.4.3 冲突及如何显式地消除歧义

    //到目前为止，你看到的这些例子都能够应用前两条判断规则解决。让我们更进一步，假设 B不再继承 A （如图9-7所示）：
//    public interface A {
//        void hello() {
//            System.out.println("Hello from A");
//        }
//    }
//    public interface B {
//        void hello() {
//            System.out.println("Hello from B");
//        }
//    }
//    public class C implements B, A { }
    // 这时规则(2)就无法进行判断了，因为从编译器的角度看没有哪一个接口的实现更加具体，两
    // 个都差不多。 A 接口和 B 接口的 hello 方法都是有效的选项。所以，Java编译器这时就会抛出一个
    // 编译错误，因为它无法判断哪一个方法更合适：“Error: class  C inherits unrelated defaults for  hello()
    // from types  B and  A .”

    //冲突的解决
//    Java 8中引入了一种新的语法 X.super.m(…) ，其中 X 是你希望调用的 m
//    方法所在的父接口。举例来说，如果你希望 C 使用来自于 B 的默认方法，它的调用方式看起来就如
//    下所示：
//    public class C implements B, A {
//        void hello(){
              // 显式地选择调用接口B 中的方法
//            B.super.hello();
//        }
//    }

//    测验9.3：几乎完全一样的函数签名
//    这个测试中，我们假设接口 A 和 B 的声明如下所示：
//    public interface A{
//        default Number getNumber(){
//            return 10;
//        }
//    }
//    public interface B{
//        default Integer getNumber(){
//            return 42;
//        }
//    }
//    类 C 的声明如下：
//    public class C implements B, A {
//        public static void main(String... args) {
//            System.out.println(new C().getNumber());
//        }
//    }
//    这个程序的会打印输出什么呢？
//    答案：类 C 无法判断 A 或者 B 到底哪一个更加具体。这就是类 C 无法通过编译的原因。

    public static void main(String... args) {
        System.out.println(new G().getNumber());

        //9.4.4 菱形继承问题
        new Z().hello();
        // 现在，我们看看另一种情况，如果 B 中也提供了一个默认的 hello 方法，并且函数签名跟 A
        // 中的方法也完全一致，这时会发生什么情况呢？根据规则(2)，编译器会选择提供了更具体实现的
        // 接口中的方法。由于 B 比 A 更加具体，所以编译器会选择 B 中声明的默认方法。如果 B 和 C 都使用相
        // 同的函数签名声明了 hello 方法，就会出现冲突，正如我们之前所介绍的，你需要显式地指定使
        // 用哪个方法。

        //顺便提一句，如果你在 C 接口中添加一个抽象的 hello 方法（这次添加的不是一个默认方法），
        //会发生什么情况呢？你可能也想知道答案。

        // C++语言中的菱形问题
        //  C++语言中的菱形问题要复杂得多。首先，C++允许类的多继承。默认情况下，如果类 D
        //  继承了类 B 和类 C ，而类 B 和类 C 又都继承自类 A ，类 D 实际直接访问的是 B 对象和 C 对象的副本。
        //  最后的结果是，要使用 A 中的方法必须显式地声明：这些方法来自于 B 接口，还是来自于 C 接口。
        //  此外，类也有状态，所以修改 B 的成员变量不会在 C 对象的副本中反映出来。

        //  现在你应该已经了解了，如果一个类的默认方法使用相同的函数签名继承自多个接口，解决
        //  冲突的机制其实相当简单。你只需要遵守下面这三条准则就能解决所有可能的冲突。
        //    首先，类或父类中显式声明的方法，其优先级高于所有的默认方法。
        //    如果用第一条无法判断，方法签名又没有区别，那么选择提供最具体实现的默认方法的
        //  接口。
        //    最后，如果冲突依旧无法解决，你就只能在你的类中覆盖该默认方法，显式地指定在你
        //  的类中使用哪一个接口中的方法。


        //9.5 小结
        //  下面是本章你应该掌握的关键概念。
        //    Java 8中的接口可以通过默认方法和静态方法提供方法的代码实现。
        //    默认方法的开头以关键字 default 修饰，方法体与常规的类方法相同。
        //    向发布的接口添加抽象方法不是源码兼容的。
        //    默认方法的出现能帮助库的设计者以后向兼容的方式演进API。
        //    默认方法可以用于创建可选方法和行为的多继承。
        //    我们有办法解决由于一个类从多个接口中继承了拥有相同函数签名的方法而导致的冲突。
        //    类或者父类中声明的方法的优先级高于任何默认方法。如果前一条无法解决冲突，那就选择同函数签名的方法中实现得最具体的那个接口的方法。
        //    两个默认方法都同样具体时，你需要在类中覆盖该方法，显式地选择使用哪个接口中提供的默认方法。

    }









    //你可以定义一个单独的 Rotatable 接口，并提供两个抽象方法 setRotationAngle 和getRotationAngle ，如下所示：
    public interface Rotatable {
        void setRotationAngle(int angleInDegrees);
        int getRotationAngle();
        default void rotateBy(int angleInDegrees){
            setRotationAngle((getRotationAngle ()) % 360);
        }
    }

    //类似地，你可以定义之前看到的两个接口 Moveable 和 Resizable 。它们都包含了默认实现。下面是 Moveable 的代码：
    public interface Moveable {
        int getX();
        int getY();
        void setX(int x);
        void setY(int y);
        default void moveHorizontally(int distance){
            setX(getX() + distance);
        }
        default void moveVertically(int distance){
            setY(getY() + distance);
        }
    }

    //下面是 Resizable 的代码：
    public interface Resizable {
        int getWidth();
        int getHeight();
        void setWidth(int width);
        void setHeight(int height);
        void setAbsoluteSize(int width, int height);
        default void setRelativeSize(int wFactor, int hFactor){
            setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
        }
    }

}
