package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.business.AppBusiness;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.fengwenyi.mp3demo.threadpool.ConcurrentCompletableFuture.sleep;

/**
 * @author : Caixin
 * @date 2019/8/12 14:03
 */
public class AppBusinessTest extends Mp3DemoApplicationTests {
    @Autowired
    AppBusiness appBusiness;

    @Autowired
    private Map<String,AppBusiness> businessMap;

    @Test
    public void testAppBusinessV2(){
        String businessImpl = businessMap.get("appBusinessImpl").testAppService();
        String businessImpl2 = businessMap.get("appBusinessV2Impl").testAppService();
        System.err.println(businessImpl);
        System.err.println(businessImpl2);
    }

    @Test
    public void testAppBusiness(){
        String appService = appBusiness.testAppService();
        System.out.println(appService);
    }

    @Resource(name = "importPool")
    private ThreadPoolExecutor poolExecutor;

   @Test
    public void testCompletableFuture(){
       System.out.println(poolExecutor);
       //任务1：洗水壶->烧开水
       CompletableFuture<String> f1 =
               CompletableFuture.supplyAsync(()->{
                   System.out.println("T1:洗水壶...");
                   sleep(1, TimeUnit.SECONDS);

                   System.out.println("T1:烧开水...");
                   sleep(15, TimeUnit.SECONDS);
                   return "白开水";
               },poolExecutor);
       //任务2：洗茶壶->洗茶杯->拿茶叶
       CompletableFuture<String> f2 =
               CompletableFuture.supplyAsync(()->{
                   System.out.println("T2:洗茶壶...");
                   sleep(1, TimeUnit.SECONDS);

                   System.out.println("T2:洗茶杯...");
                   sleep(2, TimeUnit.SECONDS);

                   System.out.println("T2:拿茶叶...");
                   sleep(1, TimeUnit.SECONDS);
                   return "龙井";
               },poolExecutor);
       //任务3：任务1和任务2完成后执行：泡茶
       CompletableFuture<String> f3 =
               f1.thenCombineAsync(f2, (f11, f22)->{
                   System.out.println("T1:拿到水:" + f11);
                   System.out.println("T1:泡茶..."+f22);
                   return "上茶:" + f11;
               },poolExecutor);
        //等待任务3执行结果
       System.out.println(f3.join());
   }
}
