package com.fengwenyi.mp3demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * created by liurenjin on 2019/2/21
 * 参考
 * https://www.cnblogs.com/dennyzhangdd/p/7010972.html
 */
public class CommonCompletableFutureUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonCompletableFutureUtil.class);

    /*组合多个CompletableFuture为一个CompletableFuture,所有子任务全部完成，组合后的任务才会完成。带返回值，可直接get.
     *
     * */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futuresList) {
        log.info("CompletableFuture sequence start ");
        //1.构造一个空CompletableFuture，子任务数为入参任务list size
        //2.流式（总任务完成后，每个子任务join取结果，后转换为list）
        return CompletableFuture.allOf(futuresList.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> futuresList.stream()
                        .map(CompletableFuture::join)
                        .collect(toList())
                );
    }

    //任何一个任务 异常
    public static <T> CompletableFuture<List<T>> sequenceExceptionallyCompleteMeetFirstFailure(List<CompletableFuture<T>> futuresList) {
        log.info("CompletableFuture sequenceExceptionallyCompleteMeetFirstfFailure start ");
        CompletableFuture<List<T>> result = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> futuresList.stream()
                        .map(CompletableFuture::join)
                        .collect(toList())
                );
        futuresList.forEach(f -> f.whenComplete((t, ex) -> {
            if (ex != null) {
                log.info("CompletableFuture  exception ");
                result.completeExceptionally(ex);
            }
        }));
        log.info("CompletableFuture sequenceExceptionallyCompleteMeetFirstfFailure end ");
        return result;
    }
}