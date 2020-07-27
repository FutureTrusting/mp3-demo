package com.fengwenyi.mp3demo.threadpool;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Caixin
 * @date 2019/7/9 17:13
 */
public class CompletableFutureException {

    /**
     * 异常处理
     *
     * @param args
     */

    public static void main(String[] args) {
        /**
         * 下面的示例代码展示了如何使用exceptionally()方法来处理异常，exceptionally()的使用非常类似于try{}catch{}中的catch{}，但是由于支持链式编程方式，所以相对更简单。既然有try{}catch{}，那就一定还有try{}finally{}，whenComplete()和handle()系列方法就类似于try{}finally{}中的finally{}，无论是否发生异常都会执行whenComplete()中的回调函数consumer和handle()中的回调函数fn。whenComplete()和handle()的区别在于whenComplete()不支持返回结果，而handle()是支持返回结果的。
         */
        CompletableFuture<Integer>
                f0 = CompletableFuture
                .supplyAsync(()->7/0)
                .thenApply(r->r*10)
                .exceptionally(e->0);
        System.out.println(f0.join());

    }
}