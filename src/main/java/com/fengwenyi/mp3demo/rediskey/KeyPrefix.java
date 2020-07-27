package com.fengwenyi.mp3demo.rediskey;

/**
 * @author : Caixin
 * @date 2019/4/25 15:52
 */
public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}
