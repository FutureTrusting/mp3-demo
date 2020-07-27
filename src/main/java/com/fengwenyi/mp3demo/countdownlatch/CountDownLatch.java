package com.fengwenyi.mp3demo.countdownlatch;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : Caixin
 * @date 2019/7/9 13:45
 */
public class CountDownLatch {

    public static void main(String[] args) {
        // 创建 2 个线程的线程池
        Executor executor =Executors.newFixedThreadPool(2);
        Iterable<String> split = Splitter.on(',')
                .trimResults() // 将结果中的空格删除
                .omitEmptyStrings() //移去结果中的空字符串
                .splitToList("foo,bar,,   qux");
        List<String> arrayList = Lists.newArrayList(split);
        System.out.println(arrayList);

        //[foo, bar, qux]

//        while(存在未对账订单){
//            // 计数器初始化为 2
//            CountDownLatch latch =
//                    new CountDownLatch(2);
//            // 查询未对账订单
//            executor.execute(()-> {
//                pos = getPOrders();
//                latch.countDown();
//            });
//            // 查询派送单
//            executor.execute(()-> {
//                dos = getDOrders();
//                latch.countDown();
//            });
//
//            // 等待两个查询操作结束
//            latch.await();
//
//            // 执行对账操作
//            diff = check(pos, dos);
//            // 差异写入差异库
//            save(diff);
//        }

    }

}
