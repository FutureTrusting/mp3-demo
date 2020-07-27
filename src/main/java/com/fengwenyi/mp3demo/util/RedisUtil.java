package com.fengwenyi.mp3demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : Caixin
 * @date 2019/9/25 15:03
 */
public class RedisUtil {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    public void set(Object key, Object value) {
        if (key == null || value == null) {
            return;
        }
        redisTemplate.opsForValue().set(key, value.toString());
    }

    /**
     * 如果已经存在返回false，否则返回true
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setNx(Object key, Object value, Long expireTime, TimeUnit mimeUnit) {
        if (key == null || value == null) {
            return false;
        }
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, mimeUnit);
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public Object get(Object key) {

        if (key == null) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Boolean remove(Object key) {

        if (key == null) {
            return false;
        }

        return redisTemplate.delete(key);
    }

    /**
     * 加锁
     *
     * @param key
     * @param waitTime 等待时间
     * @param expireTime 过期时间
     */
    public Boolean lock(String key, Long waitTime, Long expireTime) {
        String value = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        Boolean flag = setNx(key, value, expireTime, TimeUnit.MILLISECONDS);
        // 尝试获取锁 成功返回
        if (flag) {
            return flag;
        } else {
            // 获取失败
            // 现在时间
            long newTime = System.currentTimeMillis();
            // 等待过期时间
            long loseTime = newTime + waitTime;
            // 不断尝试获取锁成功返回
            while (System.currentTimeMillis() < loseTime) {
                Boolean testFlag = setNx(key, value, expireTime, TimeUnit.MILLISECONDS);
                if (testFlag) {
                    return testFlag;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param key
     * @return
     */
    public Boolean unLock(Object key) {
        return remove(key);
    }
}
