package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Discount;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Quote;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * @author : Caixin
 * @date 2019/7/24 10:15
 */
public class CompletableFutureApi {
    private final Random random=new Random(1234567890);
    //第 11 章  CompletableFuture ：组合式异步编程
    //本章内容
    //    创建异步计算，并获取计算结果

    //    使用非阻塞操作提升吞吐量
    //    设计和实现异步API
    //    如何以异步的方式使用同步的API
    //    如何对两个或多个异步操作进行流水线和合并操作
    //    如何处理异步操作的完成状态

    //11.1  Future 接口
    // Future 接口在Java 5中被引入，设计初衷是对将来某个时刻会发生的结果进行建模。它建模
    // 了一种异步计算，返回一个执行运算结果的引用，当运算结束后，这个引用被返回给调用方。在
    // Future 中触发那些潜在耗时的操作把调用线程解放出来，让它能继续执行其他有价值的工作，
    // 不再需要呆呆等待耗时的操作完成。打个比方，你可以把它想象成这样的场景：你拿了一袋子衣
    // 服到你中意的干洗店去洗。干洗店的员工会给你张发票，告诉你什么时候你的衣服会洗好（这就
    // 是一个 Future 事件）。衣服干洗的同时，你可以去做其他的事情。 Future 的另一个优点是它比
    // 更底层的 Thread 更易用。要使用 Future ，通常你只需要将耗时的操作封装在一个 Callable 对
    // 象中，再将它提交给 ExecutorService ，就万事大吉了。下面这段代码展示了Java 8之前使用
    // Future 的一个例子。

    @Test
    //代码清单11-1 使用 Future 以异步的方式执行一个耗时的操作
    public void testCompletableFuture() {
        //创建ExecutorService ，通过它你可以向线程池提交任务
        ExecutorService executor = Executors.newCachedThreadPool();
        //向 ExecutorService 提交一个Callable 对象
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() {
                //以异步方式在新的线程中执行耗时的操作
//                return doSomeLongComputation();
                return null;
            }
        });
        //异步操作进行的同时,你可以做其他的事情
           doSomethingElse();
        try {
            //获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 计算抛出一个异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            // 在Future对象完成之前超过已过期
            e.printStackTrace();
        }
        // 正像图11-3介绍的那样，这种编程方式让你的线程可以在 ExecutorService 以并发方式调
        // 用另一个线程执行耗时操作的同时，去执行一些其他的任务。接着，如果你已经运行到没有异步
        // 操作的结果就无法继续任何有意义的工作时，可以调用它的 get 方法去获取操作的结果。如果操
        // 作已经完成，该方法会立刻返回操作的结果，否则它会阻塞你的线程，直到操作完成，返回相应的结果。

        //11.1.1  Future 接口的局限性
        // 通过第一个例子，我们知道 Future 接口提供了方法来检测异步计算是否已经结束（使用
        // isDone 方法），等待异步操作结束，以及获取计算的结果。但是这些特性还不足以让你编写简洁
        // 的并发代码。比如，我们很难表述 Future 结果之间的依赖性；从文字描述上这很简单，“当长时
        // 间计算任务完成时，请将该计算的结果通知到另一个长时间运行的计算任务，这两个计算任务都
        // 完成后，将计算的结果与另一个查询操作结果合并”。但是，使用 Future 中提供的方法完成这样
        // 的操作又是另外一回事。这也是我们需要更具描述能力的特性的原因，比如下面这些

        //    将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果。
        //    等待 Future 集合中的所有任务都完成。
        //    仅等待 Future 集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同一个值），并返回它的结果。
        //    通过编程方式完成一个 Future 任务的执行（即以手工设定异步操作结果的方式）。
        //    应对 Future 的完成事件（即当 Future 的完成事件发生时会收到通知，并能使用 Future
        //  计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果）。
        // 这一章中，你会了解新的 CompletableFuture 类（它实现了 Future 接口）如何利用Java 8
        // 的新特性以更直观的方式将上述需求都变为可能。 StreamUtil 和 CompletableFuture 的设计都遵循
        // 了类似的模式：它们都使用了Lambda表达式以及流水线的思想。从这个角度，你可以说
        // CompletableFuture 和 Future 的关系就跟 StreamUtil 和 Collection 的关系一样。

        //11.1.2 使用 CompletableFuture 构建异步应用
        //为了展示 CompletableFuture 的强大特性，我们会创建一个名为“最佳价格查询器”
        //（best-price-finder）的应用，它会查询多个在线商店，依据给定的产品或服务找出最低的价格。这个过程中，你会学到几个重要的技能。

        //    首先，你会学到如何为你的客户提供异步API（如果你拥有一间在线商店的话，这是非常有帮助的）。
        //    其次，你会掌握如何让你使用了同步API的代码变为非阻塞代码。你会了解如何使用流水
        //  线将两个接续的异步操作合并为一个异步计算操作。这种情况肯定会出现，比如，在线
        //  商店返回了你想要购买商品的原始价格，并附带着一个折扣代码——最终，要计算出该
        //  商品的实际价格，你不得不访问第二个远程折扣服务，查询该折扣代码对应的折扣比率。
        //    你还会学到如何以响应式的方式处理异步操作的完成事件，以及随着各个商店返回它的
        //  商品价格，最佳价格查询器如何持续地更新每种商品的最佳推荐，而不是等待所有的商
        //  店都返回他们各自的价格（这种方式存在着一定的风险，一旦某家商店的服务中断，用
        //  户可能遭遇白屏）。

//      同步API与异步API
//        同步API其实只是对传统方法调用的另一种称呼：你调用了某个方法，调用方在被调用方
//        运行的过程中会等待，被调用方运行结束返回，调用方取得被调用方的返回值并继续运行。即
//        使调用方和被调用方在不同的线程中运行，调用方还是需要等待被调用方结束运行，这就是 阻
//        塞式调用这个名词的由来。
//        与此相反， 异步API会直接返回，或者至少在被调用方计算完成之前，将它剩余的计算任
//        务交给另一个线程去做，该线程和调用方是异步的——这就是 非阻塞式调用的由来。执行剩余
//        计算任务的线程会将它的计算结果返回给调用方。返回的方式要么是通过回调函数，要么是由
//        调用方再次执行一个“等待，直到计算完成”的方法调用。这种方式的计算在I/O系统程序设
//        计中非常常见：你发起了一次磁盘访问，这次访问和你的其他计算操作是异步的，你完成其他
//        的任务时，磁盘块的数据可能还没载入到内存，你只需要等待数据的载入完成。

        //11.2 实现异步 API
        // 很明显，这个API的使用者（这个例子中为最佳价格查询器）调用该方法时，它依旧会被
        // 阻塞。为等待同步事件完成而等待1秒钟，这是无法接受的，尤其是考虑到最佳价格查询器对
        // 网络中的所有商店都要重复这种操作。本章接下来的小节中，你会了解如何以异步方式使用同
        // 步API解决这个问题。但是，出于学习如何设计异步API的考虑，我们会继续这一节的内容，假
        // 装我们还在深受这一困难的烦扰：你是一个睿智的商店店主，你已经意识到了这种同步API会
        // 为你的用户带来多么痛苦的体验，你希望以异步API的方式重写这段代码，让用户更流畅地访
        // 问你的网站。

        //11.2.1 将同步方法转换为异步方法
//        我们在本章开头已经提到，Java 5引入了 java.util.concurrent.Future 接口表示一个异
//        步计算（即调用线程可以继续运行，不会因为调用方法而阻塞）的结果。这意味着 Future 是一
//        个暂时还不可知值的处理器，这个值在计算完成后，可以通过调用它的 get 方法取得。因为这样
//        的设计， getPriceAsync 方法才能立刻返回，给调用线程一个机会，能在同一时间去执行其他
//        有价值的计算任务。新的 CompletableFuture 类提供了大量的方法，让我们有机会以多种可能
//        的方式轻松地实现这个方法，比如下面就是这样一段实现代码。

        // 在这段代码中，你创建了一个代表异步计算的 CompletableFuture 对象实例，它在计算完
        // 成时会包含计算的结果。接着，你调用 fork 创建了另一个线程去执行实际的价格计算工作，不
        // 等该耗时计算任务结束，直接返回一个 Future 实例。当请求的产品价格最终计算得出时，你可
        // 以使用它的 complete 方法，结束 completableFuture 对象的运行，并设置变量的值。很显然，
        // 这个新版 Future 的名称也解释了它所具有的特性。使用这个API的客户端，可以通过下面的这段
        // 代码对其进行调用。
        //代码清单11-5 使用异步API
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        //查询商店，试图取得商品的价格
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime+ " msecs");
        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时
        try {
            //从 Future 对象中读取价格，如果价格未知，会发生阻塞
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
        // 我们看到这段代码中，客户向商店查询了某种商品的价格。由于商店提供了异步API，该次
        // 调用立刻返回了一个 Future 对象，通过该对象客户可以在将来的某个时刻取得商品的价格。这
        // 种方式下，客户在进行商品价格查询的同时，还能执行一些其他的任务，比如查询其他家商店中
        // 商品的价格，不会呆呆地阻塞在那里等待第一家商店返回请求的结果。最后，如果所有有意义的
        // 工作都已经完成，客户所有要执行的工作都依赖于商品价格时，再调用 Future 的 get 方法。执行
        // 了这个操作后，客户要么获得 Future 中封装的值（如果异步任务已经完成），要么发生阻塞，直
        // 到该异步任务完成，期望的值能够访问。代码清单11-5产生的输出可能是下面这样：

        //为了让客户端能了解商店无法提供请求商品价格的原因，你需要使用
        //CompletableFuture 的 completeExceptionally 方法将导致 CompletableFuture 内发生问
        //题的异常抛出。对代码清单11-4优化后的结果如下所示。代码清单11-6 抛出 CompletableFuture 内的异常

        //使用工厂方法 supplyAsync 创建 CompletableFuture
        //比如，采用 supplyAsync 方法后，你可以用一行语句重写代码清单11-4中的 getPriceAsync 方法，
        // 如下所示。代码清单11-7 使用工厂方法 supplyAsync 创建 CompletableFuture 对象

        //11.3 让你的代码免受阻塞之苦
        //所以，你已经被要求进行“最佳价格查询器”应用的开发了，不过你需要查询的所有商
        //店都如11.2节开始时介绍的那样，只提供了同步API。换句话说，你有一个商家的列表，如下
        //所示：

//        public List<String> findPrices(String product);

        //代码清单11-9 验证 findPrices 的正确性和执行性能
        long start2 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //11.3.1 使用并行流对请求进行并行操作
        long start3 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");

        //11.3.2 使用 CompletableFuture 发起异步请求
        //你已经知道我们可以使用工厂方法 supplyAsync 创建 CompletableFuture 对象。让我们把它利用起来：

        String product="myPhone27S";
        List<CompletableFuture<String>> completableFutures = shops.stream()
                .map(sh -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(product))))
                .collect(toList());
        // 使用这种方式，你会得到一个 List<CompletableFuture<String>> ，列表中的每个
        // CompletableFuture 对象在计算完成后都包含商店的 String 类型的名称。但是，由于你用
        // CompletableFutures 实现的 findPrices 方法要求返回一个 List<String> ，你需要等待所有
        // 的 future 执行完毕，将其包含的值抽取出来，填充到列表中才能返回。

        // 为了实现这个效果，你可以向最初的 List<CompletableFuture<String>> 施加第二个
        // map 操作，对 List 中的所有 future 对象执行 join 操作，一个接一个地等待它们运行结束。注意
        // CompletableFuture 类中的 join 方法和 Future 接口中的 get 有相同的含义，并且也声明在
        // Future 接口中，它们唯一的不同是 join 不会抛出任何检测到的异常。使用它你不再需要使用
        // try / catch 语句块让你传递给第二个 map 方法的Lambda表达式变得过于臃肿。所有这些整合在一
        // 起，你就可以重新实现 findPrices 了，具体代码如下。代码清单11-11 使用 CompletableFuture 实现 findPrices 方法
        List<String> stringList = findPrices3(product);

        //11.3.3 寻找更好的方案
        //11.3.4 使用定制的执行器
//      调整线程池的大小
//       《Java并发编程实战》（http://mng.bz/979c）一书中，Brian Goetz和合著者们为线程池大小
//               的优化提供了不少中肯的建议。这非常重要，如果线程池中线程的数量过多，最终它们会竞争
//               稀缺的处理器和内存资源，浪费大量的时间在上下文切换上。反之，如果线程的数目过少，正
//               如你的应用所面临的情况，处理器的一些核可能就无法充分利用。Brian Goetz建议，线程池大
//               小与处理器的利用率之比可以使用下面的公式进行估算：
//               N threads = N CPU * U CPU * (1 + W/C)
//               其中：
//       ❑N CPU 是处理器的核的数目，可以通过 Runtime.getRuntime().availableProcessors() 得到
//       ❑U CPU 是期望的CPU利用率（该值应该介于0和1之间）
//       ❑W/C是等待时间与计算时间的比率

        //代码清单11-12 为“最优价格查询器”应用定制的执行器

        // 注意，你现在正创建的是一个由守护线程构成的线程池。Java程序无法终止或者退出一个正
        // 在运行中的线程，所以最后剩下的那个线程会由于一直等待无法发生的事件而引发问题。与此相
        // 反，如果将线程标记为守护进程，意味着程序退出时它也会被回收。这二者之间没有性能上的差
        // 异。现在，你可以将执行器作为第二个参数传递给 supplyAsync 工厂方法了。比如，你现在可
        // 以按照下面的方式创建一个可查询指定商品价格的 CompletableFuture 对象：
        CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
                shop.getPrice(product), executor2);


//        并行——使用流还是 CompletableFutures ？
//        目前为止，你已经知道对集合进行并行计算有两种方式：要么将其转化为并行流，利用 map
//        这样的操作开展工作，要么枚举出集合中的每一个元素，创建新的线程，在 Completable-
//                Future 内对其进行操作。后者提供了更多的灵活性，你可以调整线程池的大小，而这能帮助
//        你确保整体的计算不会因为线程都在等待I/O而发生阻塞。
//        我们对使用这些API的建议如下。
//        ❑如果你进行的是计算密集型的操作，并且没有I/O，那么推荐使用 StreamUtil 接口，因为实
//        现简单，同时效率也可能是最高的（如果所有的线程都是计算密集型的，那就没有必要
//        创建比处理器核数更多的线程）。
//        ❑反之，如果你并行的工作单元还涉及等待I/O的操作（包括网络连接等待），那么使用
//        CompletableFuture 灵活性更好，你可以像前文讨论的那样，依据等待/计算，或者
//        W/C的比率设定需要使用的线程数。这种情况不使用并行流的另一个原因是，处理流的
//        流水线中如果发生I/O等待，流的延迟特性会让我们很难判断到底什么时候触发了等待。

        //11.4 对多个异步任务进行流水线操作

        //让我们假设所有的商店都同意使用一个集中式的折扣服务。该折扣服务提供了五个不同的折
        //扣代码，每个折扣代码对应不同的折扣率。你使用一个枚举型变量 Discount.Code 来实现这一想法，具体代码如下所示。

        //11.4.1 实现折扣服务
        //11.4.2 使用 Discount 服务

        //  由于 Discount 服务是一种远程服务，你还需要增加1秒钟的模拟延迟，代码如下所示。和在
        //  11.3节中一样，首先尝试以最直接的方式（坏消息是，这种方式是顺序而且同步执行的）重新实
        //  现 findPrices ，以满足这些新增的需求。
        // 代码清单11-15 以最简单的方式实现使用 Discount 服务的 findPrices 方法

        // 通过在 shop 构成的流上采用流水线方式执行三次 map 操作，我们得到了期望的结果。
        //   第一个操作将每个 shop 对象转换成了一个字符串，该字符串包含了该  shop 中指定商品的
        // 价格和折扣代码。
        //   第二个操作对这些字符串进行了解析，在 Quote 对象中对它们进行转换。
        //   最终，第三个 map 会操作联系远程的 Discount 服务，计算出最终的折扣价格，并返回该
        // 价格及提供该价格商品的 shop 。
        // 你可能已经猜到，这种实现方式的性能远非最优，不过我们还是应该测量一下。跟之前一样，
        // 通过运行基准测试，我们得到下面的数据：
        List<String> prices6 = findPrices6(product);
        //11.4.3 构造同步和异步操作
        //代码清单11-16 使用 CompletableFuture 实现 findPrices 方法

        // 你所进行的这三次 map 操作和代码清单11-5中的同步方案没有太大的区别，不过你使用
        // CompletableFuture 类提供的特性，在需要的地方把它们变成了异步操作。
//        1. 获取价格
//        这三个操作中的第一个你已经在本章的各个例子中见过很多次，只需要将Lambda表达式作
//        为参数传递给 supplyAsync 工厂方法就可以以异步方式对 shop 进行查询。第一个转换的结果是
//        一个 StreamUtil<CompletableFuture<String>> ，一旦运行结束，每个 CompletableFuture 对
//        象中都会包含对应 shop 返回的字符串。注意，你对 CompletableFuture 进行了设置，用代码清
//        单11-12中的方法向其传递了一个订制的执行器 Executor 。
//        2. 解析报价
//        现在你需要进行第二次转换将字符串转变为订单。由于一般情况下解析操作不涉及任何远程
//        服务，也不会进行任何I/O操作，它几乎可以在第一时间进行，所以能够采用同步操作，不会带
//        来太多的延迟。由于这个原因，你可以对第一步中生成的 CompletableFuture 对象调用它的
//        thenApply ，将一个由字符串转换 Quote 的方法作为参数传递给它。
//        注意到了吗？直到你调用的 CompletableFuture 执行结束，使用的 thenApply 方法都不会
//        阻塞你代码的执行。这意味着 CompletableFuture 最终结束运行时，你希望传递Lambda表达式
//        给 thenApply 方法，将 StreamUtil 中的每个 CompletableFuture<String> 对象转换为对应的
//        CompletableFuture<Quote> 对象。你可以把这看成是为处理 CompletableFuture 的结果建
//        立了一个菜单，就像你曾经为 StreamUtil 的流水线所做的事儿一样。
//        3. 为计算折扣价格构造 Future
//        第三个 map 操作涉及联系远程的 Discount 服务，为从商店中得到的原始价格申请折扣率。
//        这一转换与前一个转换又不大一样，因为这一转换需要远程执行（或者，就这个例子而言，它需
//        要模拟远程调用带来的延迟），出于这一原因，你也希望它能够异步执行。
//        为了实现这一目标，你像第一个调用传递 getPrice 给 supplyAsync 那样，将这一操作以
//        Lambda表达式的方式传递给了 supplyAsync 工厂方法，该方法最终会返回另一个 Completable-
//                Future 对象。到目前为止，你已经进行了两次异步操作，用了两个不同的 CompletableFutures
//        对象进行建模，你希望能把它们以级联的方式串接起来进行工作。
//          从 shop 对象中获取价格，接着把价格转换为 Quote 。
//          拿到返回的 Quote 对象，将其作为参数传递给 Discount 服务，取得最终的折扣价格。
//        Java 8的  CompletableFuture API提供了名为 thenCompose 的方法，它就是专门为这一目
//        的而设计的， thenCompose 方法允许你对两个异步操作进行流水线，第一个操作完成时，将其
//        结果作为参数传递给第二个操作。换句话说，你可以创建两个 CompletableFutures 对象，对
//        第一个 CompletableFuture 对象调用 thenCompose ，并向其传递一个函数。当第一个
//        CompletableFuture 执行完毕后，它的结果将作为该函数的参数，这个函数的返回值是以第一
//        个 CompletableFuture 的返回做输入计算出的第二个 CompletableFuture 对象。使用这种方
//        式，即使 Future 在向不同的商店收集报价，主线程还是能继续执行其他重要的操作，比如响应
//        UI事件。

        //通过下面的示例代码，你可以看一下thenApply()方法是如何使用的。首先通过supplyAsync()启动一个异步流程，之后是两个串行操作，
        // 整体看起来还是挺简单的。不过，虽然这是一个异步流程，但任务①②③却是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
        CompletableFuture<String> f0 =
          CompletableFuture.supplyAsync(
            () -> "Hello World")      //①
          .thenApply(s -> s + " QQ")  //②
          .thenApply(String::toUpperCase);//③
        System.out.println(f0.join());
        ////输出结果
        //HELLO WORLD QQ

       //11.4.4 将两个 CompletableFuture 对象整合起来，无论它们是否存在依赖
       // 代码清单11-16中，你对一个 CompletableFuture 对象调用了 thenCompose 方法，并向其
       // 传递了第二个 CompletableFuture ，而第二个 CompletableFuture 又需要使用第一个
       // CompletableFuture 的执行结果作为输入。但是，另一种比较常见的情况是，你需要将两个完
       // 全不相干的 CompletableFuture 对象的结果整合起来，而且你也不希望等到第一个任务完全结
       // 束才开始第二项任务。
//        这种情况，你应该使用 thenCombine 方法，它接收名为 BiFunction 的第二参数，这个参数
//        定义了当两个 CompletableFuture 对象完成计算后，结果如何合并。同 thenCompose 方法一样，
//        thenCombine 方法也提供有一个 Async 的版本。这里，如果使用 thenCombineAsync 会导致
//        BiFunction 中定义的合并操作被提交到线程池中，由另一个任务以异步的方式执行。
//        回到我们正在运行的这个例子，你知道，有一家商店提供的价格是以欧元（EUR）计价的，
//        但是你希望以美元的方式提供给你的客户。你可以用异步的方式向商店查询指定商品的价格，同
//        时从远程的汇率服务那里查到欧元和美元之间的汇率。当二者都结束时，再将这两个结果结合起
//        来，用返回的商品价格乘以当时的汇率，得到以美元计价的商品价格。用这种方式，你需要使用
//        第 三 个 CompletableFuture 对 象 ， 当 前 两 个 CompletableFuture 计 算 出 结 果 ， 并 由
//        BiFunction 方法完成合并后，由它来最终结束这一任务，代码清单如下所示。

        //代码清单11-17 合并两个独立的 CompletableFuture 对象
//        Future<Double> futurePriceInUSD =CompletableFuture
//                //创建第一个任务查询,商店取得商品的价格
//                .supplyAsync(() -> shop.getPrice(product))
//                        //创建第二个独立任务，查询美元和欧元之间的转换汇率
//                        .thenCombine(
//                                CompletableFuture.supplyAsync(() -> exchangeService.getRate(Money.EUR, Money.USD)),
//                                //通过乘法整合得到的商品价格和汇率
//                                (price, rate) -> price * rate
//                        );

        //这里整合的操作只是简单的乘法操作，用另一个单独的任务对其进行操作有些浪费资源，所
        //以你只要使用 thenCombine 方法，无需特别求助于异步版本的 thenCombineAsync 方法。图11-6
        //展示了代码清单11-17中创建的多个任务是如何在线程池中选择不同的线程执行的，以及它们最
        //终的运行结果又是如何整合的。

        //11.4.5 对 Future 和 CompletableFuture 的回顾
        //  前文介绍的最后两个例子，即代码清单11-16和代码清单11-17，非常清晰地呈现了相对于采
        //  用Java 8之前提供的 Future 实现， CompletableFuture 版本实现所具备的巨大优势。
        //  CompletableFuture 利用Lambda表达式以声明式的API提供了一种机制，能够用最有效的方式，
        //  非常容易地将多个以同步或异步方式执行复杂操作的任务结合到一起。为了更直观地感受一下使
        //  用 CompletableFuture 在代码可读性上带来的巨大提升，你可以尝试仅使用Java 7中提供的特
        //  性，重新实现代码清单11-17的功能。代码清单11-18展示了如何实现这一效果。

        //代码清单11-18 利用Java 7的方法合并两个 Future 对象

        //11.4.5 对 Future 和 CompletableFuture 的回顾
            //创建一个 ExecutorService 将任务提交到线程池
//        ExecutorService executor8 = Executors.newCachedThreadPool();
            //创建一个查询欧元到美元转换汇率的Future
//        final Future<Double> futureRate = executor8.submit(new Callable<Double>() {
//            @Override
//            public Double call() {
//                return exchangeService.getRate(Money.EUR, Money.USD);
//            }});
//        Future<Double> futurePriceInUSD = executor8.submit(new Callable<Double>() {
//            @Override
//            public Double call() throws ExecutionException, InterruptedException {
                    //在第二个 Future中查询指定商店中特定商品的价格
//                double priceInEUR = shop.getPrice(product);
                    //在查找价格操作的同一个 Future 中，将价格和汇率做乘法计算出汇后价格
//                return priceInEUR * futureRate.get();
//            }});

        // 你的“最佳价格查询器”应用基本已经完成，不过还缺失了一些元素。你会希望尽快将不同
        // 商店中的商品价格呈现给你的用户（这是车辆保险或者机票比价网站的典型需求），而不是像你之
        // 前那样，等所有的数据都完备之后再呈现。接下来的一节，你会了解如何通过响应 CompletableFuture
        // 的 completion 事件实现这一功能（与此相反，调用 get 或者 join 方法只会造成阻塞，直
        // 到 CompletableFuture 完成才能继续往下运行）。

        //11.5 响应 CompletableFuture 的 completion 事件
        //由于这些原因，你希望购买的商品在某些商店的查询速度要比另一些商店更快。为了说明本
        //章的内容，我们以下面的代码清单为例，使用 randomDelay 方法取代原来的固定延迟。


        //目前为止，你实现的 findPrices 方法只有在取得所有商店的返回值时才显示商品的价格。
        //而你希望的效果是，只要有商店返回商品价格就在第一时间显示返回值，
        // 不再等待那些还未返回的商店（有些甚至会发生超时）。你如何实现这种更进一步的改进要求呢？

        //11.5.1 对最佳价格查询器应用的优化
        // 你要避免的首要问题是，等待创建一个包含了所有价格的 List 创建完成。你应该做的是直
        // 接处理 CompletableFuture 流，这样每个 CompletableFuture 都在为某个商店执行必要的操
        // 作。为了实现这一目标，在下面的代码清单中，你会对代码清单11-12中代码实现的第一部分进
        // 行重构，实现 findPricesStream 方法来生成一个由 CompletableFuture 构成的流。

        //代码清单11-20 重构 findPrices 方法返回一个由 Future 构成的流

        // 现在，你为 findPricesStream 方法返回的 StreamUtil 添加了第四个 map 操作，在此之前，你
        // 已 经 在 该 方 法 内 部 调 用 了 三 次 map 。 这 个 新 添 加 的 操 作 其 实 很 简 单 ， 只 是 在 每 个
        // CompletableFuture 上注册一个操作，该操作会在 CompletableFuture 完成执行后使用它的
        // 返回值。Java 8的 CompletableFuture 通过 thenAccept 方法提供了这一功能，它接收
        // CompletableFuture 执行完毕后的返回值做参数。在这里的例子中，该值是由 Discount 服务
        // 返回的字符串值，它包含了提供请求商品的商店名称及折扣价格，你想要做的操作也很简单，只
        // 是将结果打印输出：
        findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println));

        // 注意，和你之前看到的 thenCompose 和 thenCombine 方法一样， thenAccept 方法也提供
        // 了一个异步版本，名为 thenAcceptAsync 。异步版本的方法会对处理结果的消费者进行调度，
        // 从线程池中选择一个新的线程继续执行，不再由同一个线程完成 CompletableFuture 的所有任
        // 务。因为你想要避免不必要的上下文切换，更重要的是你希望避免在等待线程上浪费时间，尽快
        // 响应 CompletableFuture 的 completion 事件，所以这里没有采用异步版本。

        // 由于 thenAccept 方法已经定义了如何处理 CompletableFuture 返回的结果，一旦
        // CompletableFuture 计算得到结果，它就返回一个 CompletableFuture<Void> 。所以， map
        // 操作返回的是一个 StreamUtil<CompletableFuture<Void>> 。对这个 <CompletableFuture-
        // <Void>> 对象，你能做的事非常有限，只能等待其运行结束，不过这也是你所期望的。你还希望
        // 能给最慢的商店一些机会，让它有机会打印输出返回的价格。为了实现这一目的，你可以把构成
        // StreamUtil 的所有 CompletableFuture<Void> 对象放到一个数组中，等待所有的任务执行完成，
        // 代码如下所示。

        //代码清单11-21 响应 CompletableFuture 的 completion 事件
        CompletableFuture[] futures = findPricesStream("myPhone")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();

        // allOf 工厂方法接收一个由 CompletableFuture 构成的数组，数组中的所有 CompletableFuture
        // 对象执行完成之后，它返回一个 CompletableFuture<Void> 对象。这意味着，如果你需
        // 要等待最初 StreamUtil 中的所有 CompletableFuture 对象执行完毕，对 allOf 方法返回的
        // CompletableFuture 执行 join 操作是个不错的主意。这个方法对“最佳价格查询器”应用也是
        // 有用的，因为你的用户可能会困惑是否后面还有一些价格没有返回，使用这个方法，你可以在执
        // 行完毕之后打印输出一条消息“All shops returned results or timed out”。
        // 然而在另一些场景中，你可能希望只要 CompletableFuture 对象数组中有任何一个执行完
        // 毕就不再等待，比如，你正在查询两个汇率服务器，任何一个返回了结果都能满足你的需求。在
        // 这种情况下，你可以使用一个类似的工厂方法 anyOf 。该方法接收一个 CompletableFuture 对象
        // 构成的数组，返回由第一个执行完毕的 CompletableFuture 对象的返回值构成的 CompletableFuture<Object> 。

        //11.5.2 付诸实践
        // 正如我们在本节开篇所讨论的，现在你可以通过代码清单11-19中的 randomDelay 方法模拟
        // 远程方法调用，产生一个介于0.5秒到2.5秒的随机延迟，不再使用恒定1秒的延迟值。代码清单
        // 11-21应用了这一改变，执行这段代码你会看到不同商店的价格不再像之前那样总是在一个时刻
        // 返回，而是随着商店折扣价格返回的顺序逐一地打印输出。为了让这一改变的效果更加明显，我
        // 们对代码进行了微调，在输出中打印每个价格计算所消耗的时间：
        long start16 = System.nanoTime();
        CompletableFuture[] futures16 = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +((System.nanoTime() - start16) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures16).join();
        System.out.println("All shops have now responded in "+ ((System.nanoTime() - start16) / 1_000_000) + " msecs");

        // BuyItAll price is 184.74 (done in 2005 msecs)
        // MyFavoriteShop price is 192.72 (done in 2157 msecs)
        // LetsSaveBig price is 135.58 (done in 3301 msecs)
        // ShopEasy price is 167.28 (done in 3869 msecs)
        // BestPrice price is 110.93 (done in 4188 msecs)
        // All shops have now responded in 4188 msecs

//        11.6 小结
//              这一章中，你学到的内容如下。
//            执行比较耗时的操作时，尤其是那些依赖一个或多个远程服务的操作，使用异步任务可
//                  以改善程序的性能，加快程序的响应速度。
//            你应该尽可能地为客户提供异步API。使用 CompletableFuture 类提供的特性，你能够
//                  轻松地实现这一目标。
//            CompletableFuture 类还提供了异常管理的机制，让你有机会抛出/管理异步任务执行
//                  中发生的异常。
//            将同步API的调用封装到一个 CompletableFuture 中，你能够以异步的方式使用其结果。
//            如果异步任务之间相互独立，或者它们之间某一些的结果是另一些的输入，你可以将这
//                  些异步任务构造或者合并成一个。
//            你可以为 CompletableFuture 注册一个回调函数，在 Future 执行完毕或者它们计算的
//                  结果可用时，针对性地执行一些程序。
//            你可以决定在什么时候结束程序的运行，是等待由 CompletableFuture 对象构成的列表
//                  中所有的对象都执行完毕，还是只要其中任何一个首先完成就中止程序的运行。

    }

        //代码清单11-20 重构 findPrices 方法返回一个由 Future 构成的流
        public Stream<CompletableFuture<String>> findPricesStream(String product) {
            return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(
                            () -> shop.getPrice6(product), executor2))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(quote ->
                            CompletableFuture.supplyAsync(
                                    () -> Discount.applyDiscount(quote), executor2)));
        }


        //代码清单11-19 一个模拟生成0.5秒至2.5秒随机延迟的方法
        private static final Random random11 = new Random();
        public static void randomDelay() {
            int delay = 500 + random11.nextInt(2000);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    //让我们再次使用 CompletableFuture 提供的特性，以异步方式重新实现 findPrices 方法。
    // 详细代码如下所示。如果你发现有些内容不太熟悉，不用太担心，我们很快会进行针对性的介绍。
    //代码清单11-16 使用 CompletableFuture 实现 findPrices 方法
    //13、thenCompose 方法
    //thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
    public List<String> findPrices7(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        //以异步方式取得每个 shop 中指定产品的原始价格
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice6(product), executor2))
                        //Quote 对象存在时，对其返回的值进行转换
                        .map(future -> future.thenApply(Quote::parse))
                        //使用另一个异步任务构造期望的 Future ，申请折扣
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote), executor2)))
                        .collect(toList());
        //等待流中的所有 Future 执行完毕，并提取各自的返回值
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }



    //代码清单11-15 以最简单的方式实现使用 Discount 服务的 findPrices 方法
    public List<String> findPrices6(String product) {
        return shops.stream()
                //取得每个 shop 对象中商品的原始价格
                .map(shop -> shop.getPrice6(product))
                //在 Quote 对象中对 shop 返回的字符串进行转换
                .map(Quote::parse)
                //联系 Discount 服务，为每个 Quote申请折扣
                .map(Discount::applyDiscount)
                .collect(toList());
    }


    public String getPrice6(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }

    final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    //代码清单11-12 为“最优价格查询器”应用定制的执行器
    //创建一个线程池，线程池中线程的数目为100和商店数目二者中较小的一个值
    private final Executor executor2 =Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            //使用守护线程——这种方式不会阻止程序的关停
                            t.setDaemon(true);
                            return t;
                        }
                    });

    //代码清单11-11 使用 CompletableFuture 实现 findPrices 方法
    public List<String> findPrices3(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        //使用 CompletableFuture以异步方式计算每种商品的价格
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product)))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                //等待所有异步操作结束
                .map(CompletableFuture::join)
                .collect(toList());
    }



    //代码清单11-10 对 findPrices 进行并行操作
    public List<String> findPrices2(String product) {
        //使用并行流并行地从不同的商店获取价格
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }


    //代码清单11-8 采用顺序查询所有商店的方式实现的 findPrices 方法
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }




    //代码清单11-7 使用工厂方法 supplyAsync 创建 CompletableFuture 对象
    // supplyAsync 方法接受一个生产者（ Supplier ）作为参数，返回一个 CompletableFuture
    // 对象，该对象完成异步执行后会读取调用生产者方法的返回值。生产者方法会交由 ForkJoinPool
    // 池中的某个执行线程（ Executor ）运行，但是你也可以使用 supplyAsync 方法的重载版本，传
    // 递第二个参数指定不同的执行线程执行生产者方法。一般而言，向 CompletableFuture 的工厂
    // 方法传递可选参数，指定生产者方法的执行线程是可行的，在11.3.4节中，你会使用这一能力，我
    // 们会在该小节介绍如何使用适合你应用特性的执行线程改善程序的性能。
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    //代码清单11-6 抛出 CompletableFuture 内的异常
    //客户端现在会收到一个 ExecutionException 异常，该异常接收了一个包含失败原因的
    //Exception 参数，即价格计算方法最初抛出的异常。所以，举例来说，如果该方法抛出了一个运
    //行时异常“product not available”，客户端就会得到像下面这样一段 ExecutionException ：
    //java.util.concurrent.ExecutionException: java.lang.RuntimeException: product
    //not available
    //at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2237)
    //at lambdasinaction.chap11.AsyncShopClient.main(AsyncShopClient.java:14)
    //... 5 more
    //Caused by: java.lang.RuntimeException: product not available
    //at lambdasinaction.chap11.AsyncShop.calculatePrice(AsyncShop.java:36)
    //at lambdasinaction.chap11.AsyncShop.lambda$getPrice$0(AsyncShop.java:23)
    //at lambdasinaction.chap11.AsyncShop$$Lambda$1/24071475.run(Unknown Source)
    //at java.lang.Thread.run(Thread.java:744)
    public Future<Double> getPriceAsync3(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                //如果价格计算正常结束，完成 Future 操作并设置商品价格
                futurePrice.complete(price);
            } catch (Exception ex) {
                //否则就抛出导致失败的异常，完成这次 Future 操作
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }


    private void doSomethingElse() {
        delay();
    }

    //为了实现这个目标，你首先需要将 getPrice 转换为 getPriceAsync 方法，并修改它的返回值：
    public Future<Double> getPriceAsync2(String product) {
        //创建 CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            //在另一个线程中以异步方式执行计算
            double price = calculatePrice(product);
            //需长时间计算的任务结束并得出结果时，设置Future 的返回值
            futurePrice.complete(price);
        }).start();
        //无需等待还没结束的计算，直接返回 Future 对象
        return futurePrice;
    }



    //为了实现最佳价格查询器应用，让我们从每个商店都应该提供的API定义入手。首先，商店应该声明依据指定产品名称返回价格的方法：
    @Getter
    @Setter
    public class Shop {
        private String name;
        private Double price;
        public Shop(String name) {
            this.name = name;
        }

        public String getPrice6(String product) {
            return String.valueOf(calculatePrice(product));
        }


        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            //创建 CompletableFuture对象，它会包含计算的结果
            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            new Thread(() -> {
                //在另一个线程中以异步方式执行计算
                double price = calculatePrice(product);
                //需长时间计算的任务结束并得出结果时，设置Future 的返回值
                futurePrice.complete(price);
            }).start();
            //无需等待还没结束的计算，直接返回 Future 对象
            return futurePrice;
        }

    }
    //该方法的内部实现会查询商店的数据库，但也有可能执行一些其他耗时的任务，比如联系其
    //他外部服务（比如，商店的供应商，或者跟制造商相关的推广折扣）。我们在本章剩下的内容中，
    //采用 delay 方法模拟这些长期运行的方法的执行，它会人为地引入1秒钟的延迟，方法声明如下。
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
//            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //代码清单11-3 在 getPrice 方法中引入一个模拟的延迟
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
