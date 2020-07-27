package com.fengwenyi.mp3demo.service;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.dto.SecureUriDTO;
import com.fengwenyi.mp3demo.util.CommonCompletableFutureUtil;
import com.fengwenyi.mp3demo.util.RedisLock;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : Caixin
 * @date 2019/8/12 14:03
 */
@Slf4j
public class RedissonTest extends Mp3DemoApplicationTests {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Redisson redisson;
    // 请求总数
    public static int clientTotal = 1000;
    // 同时并发执行的线程数
    public static int threadTotal = 10;

    private final String prefixKey = "TEST:REDISSON";

    @Test
    public void test2Redisson() {
        redisTemplate.opsForValue().set(prefixKey, "1");
        Integer o = Integer.valueOf(redisTemplate.opsForValue().get(prefixKey));
        String i1 = String.valueOf(o + 1);
        redisTemplate.opsForValue().set(prefixKey, i1);
    }

    @Test
    public void testRedisson() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    String add = add();
                    System.err.println(add);
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

//    private String add3() {
//        Jedis jedis = jedisPool.getResource();
//        String id=null;
//        try {
//            // 尝试获取锁，有超时时间
//             id = RedisLock.tryLock(jedis,5000);
//            //更新redis
//            String s = redisTemplate.opsForValue().get(prefixKey);
//            assert s != null;
//            int o = Integer.parseInt(s);
//            System.err.println("S-----" + o);
//            int i = o + 1;
//            if (i % 222 == 0) {
//                System.err.println(i);
//                TimeUnit.SECONDS.sleep(4);
//            }
//            if (i % 333 == 0) {
//                System.err.println(i);
//                TimeUnit.SECONDS.sleep(5);
//            }
//            redisTemplate.opsForValue().set(prefixKey, String.valueOf(i));
//        }catch (Exception e){
//            System.out.println("获取锁超时"+e.getMessage());
//        }finally {
//            // 释放锁
//            RedisLock.unLock(jedis, id);
//        }
//        return "1";
//    }

    private String add2() {
        RLock rLock = redisson.getLock(prefixKey);
        try {
            if (rLock.tryLock(3, 5, TimeUnit.SECONDS)) {
                System.err.println("取到锁");
                //更新redis
                String s = redisTemplate.opsForValue().get(prefixKey);
//                if(StrUtil.isNotBlank(s)){
//                    lock.unlock();
//                    System.err.println("再return");
//                    return s;
//                }
                assert s != null;
                Integer o = Integer.valueOf(s);
                System.err.println("S-----" + o);
                int i = o + 1;
                if (i % 222 == 0) {
                    System.err.println(i);
                    TimeUnit.SECONDS.sleep(4);
                }
                if (i % 333 == 0) {
                    System.err.println(i);
                    TimeUnit.SECONDS.sleep(5);
                }
                redisTemplate.opsForValue().set(prefixKey, String.valueOf(i));
            } else {
                System.err.println("未获取到锁===========");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                System.err.println("当前线程有锁");
                rLock.unlock();
            }
        }
        return "1";
    }

    private String add() {
        RLock rLock = redisson.getLock(prefixKey);
        try {
            rLock.lock(10, TimeUnit.SECONDS);
            System.err.println("取到锁");
            //更新redis
            String s = redisTemplate.opsForValue().get(prefixKey);
                if(StrUtil.isNotBlank(s)){
//                    System.err.println("再return");
                    return s;
                }
            assert s != null;
            Integer o = Integer.valueOf(s);
            System.err.println("S-----" + o);
            int i = o + 1;
            if (i % 222 == 0) {
                System.err.println(i);
                TimeUnit.SECONDS.sleep(4);
            }
            if (i % 333 == 0) {
                System.err.println(i);
                TimeUnit.SECONDS.sleep(5);
            }
            redisTemplate.opsForValue().set(prefixKey, String.valueOf(i));
//            System.err.println("未获取到锁===========");
//                throw new RuntimeException("没有获得锁");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                System.err.println("解锁===========");
                rLock.unlock();
            }
        }
        return "1";
    }


}
