package com.fengwenyi.mp3demo.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.dao.IdcardDao;
import com.fengwenyi.mp3demo.model.Idcard;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@Slf4j
public class TestInsert extends Mp3DemoApplicationTests {

    @Autowired
    IdcardDao idcardDao;
    // 请求总数
    public static int clientTotal = 500;
    // 同时并发执行的线程数
    public static int threadTotal = 10;

    @Test
    public void testRedisson() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    IntStream.range(0,10)
                            .boxed()
                            .forEach(x->{
                                List<Idcard> idcards = idcardDao.selectByIdCode(Long.valueOf(x),String.valueOf(x));
                                if(CollectionUtil.isEmpty(idcards)){
                                    Idcard idcard = new Idcard();
                                    idcard.setId(Long.valueOf(x));
                                    idcard.setCode(String.valueOf(x));
                                    int insert = idcardDao.insert(idcard);
                                    System.err.println(insert>0);
                                }
                            });
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }




}
