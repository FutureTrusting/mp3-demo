package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.dto.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.fengwenyi.mp3demo.java8.expression.LambdaExpression.filter;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Caixin
 * @date 2019/7/22 16:12
 */
public class TestLambda {

    //8.3 测试 Lambda 表达式

    public static void main(String[] args) {
        //8.3.1 测试可见 Lambda 函数的行为
        //8.3.2 测试使用 Lambda 的方法的行为
        //8.3.3 将复杂的 Lambda 表达式分到不同的方法
        //8.3.4 高阶函数的测试

//        这就是流操作方法 peek 大显身手的时候。 peek 的设计初衷就是在流的每个元素恢复运行之
//        前，插入执行一个动作。但是它不像 forEach 那样恢复整个流的运行，而是在一个元素上完成操
//        作之后，它只会将操作顺承到流水线中的下一个操作。图8-4解释了 peek 的操作流程。下面的这
//        段代码中，我们使用 peek 输出了Stream流水线操作之前和操作之后的中间值：
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);

        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);

        List<Integer> result =
                numbers.stream()
                        //输出来自数据源的当前元素值
                        .peek(x -> System.out.println("from stream: " + x))
                        .map(x -> x + 17)
                        //输 出 map 操作的结果
                        .peek(x -> System.out.println("after map: " + x))
                        .filter(x -> x % 2 == 0)
                        //输出经过 filter操作之后，剩下的元素个数
                        .peek(x -> System.out.println("after filter: " + x))
                        .limit(3)
                        //输出经过 limit 操作之后，剩下的元素个数
                        .peek(x -> System.out.println("after limit: " + x))
                        .collect(toList());
        //from stream: 2
        //after map: 19
        //from stream: 3
        //after map: 20
        //after filter: 20
        //after limit: 20
        //from stream: 4
        //after map: 21
        //from stream: 5
        //after map: 22
        //after filter: 22
        //after limit: 22
        System.out.println(result);

        //8.5 小结
        //    Lambda表达式能提升代码的可读性和灵活性。
        //    如果你的代码中使用了匿名类，尽量用Lambda表达式替换它们，但是要注意二者间语义
        //  的微妙差别，比如关键字 this ，以及变量隐藏。
        //    跟Lambda表达式比起来，方法引用的可读性更好 。
        //    尽量使用Stream API替换迭代式的集合处理。
        //    Lambda表达式有助于避免使用面向对象设计模式时容易出现的僵化的模板代码，典型的
        //  比如策略模式、模板方法、观察者模式、责任链模式，以及工厂模式。
        //    即使采用了Lambda表达式，也同样可以进行单元测试，但是通常你应该关注使用了
        //  Lambda表达式的方法的行为。
        //    尽量将复杂的Lambda表达式抽象到普通方法中。
        //    Lambda表达式会让栈跟踪的分析变得更为复杂。
        //    流提供的 peek 方法在分析Stream流水线时，能将中间变量的值输出到日志中，是非常有
        //  用的工具。

    }

    @Test
    public void testFilter() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = filter(numbers, i -> i < 3);
        assertEquals(Arrays.asList(2, 4), even);
        assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }

    @Test
    public void testMoveAllPointsRightBy() throws Exception {
        List<Point> points =
                Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints =
                Arrays.asList(new Point(15, 5), new Point(20, 5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, newPoints);
    }


    @Test
    public void testComparingTwoPoints() throws Exception {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        assertEquals(-1, result);
    }

    @Test
    public void testMoveRightBy() throws Exception {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);
        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }

}
