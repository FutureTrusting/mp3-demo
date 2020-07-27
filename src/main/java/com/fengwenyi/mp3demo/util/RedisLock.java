package com.fengwenyi.mp3demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * redis锁工具类
 * @author GaoYuan
 * @date 2018/9/17 下午9:06
 */
public class RedisLock {

    /** key */
    static String lockKey = "lock";
    /**
     * 获取锁
     * 利用set key value [EX seconds] [PX milliseconds] [NX|XX] 命令实现锁机制
     * @author GaoYuan
     * @date 2018/9/18 上午8:42
     */
    public static String tryLock(Jedis jedis, int timeout) throws Exception{
        if(timeout == 0){
            timeout = 5000;
        }
        String returnId = null;
        // 生成随机标识
        String id = UUID.randomUUID().toString();
        // 设置锁超时10秒
        int lockExpireMs = 10000;
        long startTime = System.currentTimeMillis();
        // 超时时间内循环获取
        while ((System.currentTimeMillis() - startTime) < timeout){
            String result = jedis.set(lockKey, id, "NX", "PX", lockExpireMs);
            if(result != null){
                returnId = id;
                break;
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
        if(returnId == null){
            // 获取锁超时，抛出异常
            throw new Exception("获取锁超时");
        }
        // 将set的值返回，用于后续的解锁
        return returnId;
    }


    /**
     * 释放锁 - 利用redis的watch + del
     * @author GaoYuan
     * @date 2018/9/18 上午8:42
     */
    public static boolean unLock(Jedis jedis, String id){
        boolean result = false;
        while(true){
            if(jedis.get(lockKey) == null){
                return false;
            }
            // 配置监听
            jedis.watch(lockKey);
            // 这里确保是加锁者进行解锁
            if(id!=null && id.equals(jedis.get(lockKey))){
                Transaction transaction = jedis.multi();
                transaction.del(lockKey);
                List<Object> results = transaction.exec();
                if(results == null){
                    continue;
                }
                result = true;
            }
            // 释放监听
            jedis.unwatch();
            break;
        }
        return result;
    }

    /**
     * 释放锁 - 利用lua脚本
     * @author GaoYuan
     * @date 2018/9/21 下午12:36
     */
    public static boolean unLockByLua(Jedis jedis, String id){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(id));
        if (Objects.equals(1, result)) {
            return true;
        }
        return  false;
    }

}



