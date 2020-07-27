package com.fengwenyi.mp3demo.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.UnicodeUtil;
import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.stackoverflow.java8.GroupingByCollectingAndThen;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : Caixin
 * @date 2019/9/25 15:13
 */
@Component
public class RedisLockTest extends Mp3DemoApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    public void test01() {
        String toString = UnicodeUtil.toUnicode("因为java注释也能识别unicode");
        String toString2 = UnicodeUtil.toUnicode("因为java注释也能识别unicode",Boolean.FALSE);
        System.err.println(toString);
        System.err.println(toString2);
        System.err.println("=============================");
        String string = UnicodeUtil.toString(toString);
        String string1 = UnicodeUtil.toString(toString2);
        System.err.println(string);
        System.err.println(string1);

        TimeInterval timer = DateUtil.timer();
        String key = "uid:12011";
        Boolean flag = redisService.lock(key, 10L, 1000L * 60);
        if (!flag) {
            // 获取锁失败
            System.err.println("获取锁失败");
        } else {
            // 获取锁成功
            System.out.println("获取锁成功");
        }
        Console.log(timer.interval());
        // 释放锁
        redisService.unLock(key);
    }
}
