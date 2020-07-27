package com.fengwenyi.mp3demo.cyclicbarrier;

import com.fengwenyi.mp3demo.model.City;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : Caixin
 * @date 2019/7/9 13:47
 */
public class CyclicBarrier {

    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        City city = new City();
        boolean allFieldsIsNull = checkObjAllFieldsIsNull(city);
        System.err.println(allFieldsIsNull);
    }

//    public static void main(String[] args) {
//        // 订单队列
//        Vector<P> pos;
//        // 派送单队列
//        Vector<D> dos;
//        // 执行回调的线程池
//        Executor executor =
//                Executors.newFixedThreadPool(1);
//
//        final CyclicBarrier barrier =
//                new CyclicBarrier(2, ()->{
//                    executor.execute(()->check());
//                });
//
//        void check(){
//            P p = pos.remove(0);
//            D d = dos.remove(0);
//            // 执行对账操作
//            diff = check(p, d);
//            // 差异写入差异库
//            save(diff);
//        }
//
//        void checkAll(){
//            // 循环查询订单库
//            Thread T1 = new Thread(()->{
//                while(存在未对账订单){
//                    // 查询订单库
//                    pos.add(getPOrders());
//                    // 等待
//                    barrier.await();
//                }
//            }
//                    T1.start();
//            // 循环查询运单库
//            Thread T2 = new Thread(()->{
//                while(存在未对账订单){
//                    // 查询运单库
//                    dos.add(getDOrders());
//                    // 等待
//                    barrier.await();
//                }
//            }
//                    T2.start();
//        }
//
//    }
}
