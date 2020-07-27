package com.fengwenyi.mp3demo.guavaretryer;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Caixin
 * @date 2019/7/11 9:06
 */

@Slf4j
@Service
public class GuavaRetryerBuilder implements GuavaRetryer {


    private Boolean uploadToOdps(Integer integers) {
        log.error("1111" + integers);
        System.out.println(integers);
        if (integers >= 3) {
            System.out.println("重试次数:" + integers);
            return true;
        } else {
            System.out.println("重试次数:" + integers);
        }
        return false;
    }

    @Override
    public void uploadOdps(Integer integer) {
        // RetryerBuilder 构建重试实例 guavaretryer,可以设置重试源且可以支持多个重试源，可以配置重试次数或重试超时时间，以及可以配置等待时间间隔
        //设置自定义段元重试源，
        //特别注意：这个apply返回true说明需要重试，与操作逻辑的语义要区分
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                ////设置异常重试源
                .retryIfException()
                //返回false需要重试
                .retryIfResult(aBoolean -> Objects.equals(aBoolean, false))
                //设置重试5次，同样可以设置重试超时时间
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                //第【{}】次调用失败监听
//                .withRetryListener(new RetryListener() {
//                    @Override
//                    public <V> void onRetry(Attempt<V> attempt) {
//                        log.error("第【{}】次调用失败", attempt.getAttemptNumber());
//                    }
//                })
                //设置每次重试间隔
                .withWaitStrategy(WaitStrategies.fixedWait(100L, TimeUnit.MILLISECONDS)).build();
        try {
            //重试入口采用call方法，用的是java.util.concurrent.Callable<V>的call方法,所以执行是线程安全的
            boolean result = retryer.call(() -> {
                try {
                    //特别注意：返回false说明无需重试，返回true说明需要继续重试
                    return uploadToOdps(integer);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Guava RetryException failed:{}", e.getMessage());
                    return true;
                }
            });

        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }
}
