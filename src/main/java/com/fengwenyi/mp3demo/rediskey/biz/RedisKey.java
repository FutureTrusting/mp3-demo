package com.fengwenyi.mp3demo.rediskey.biz;

import com.fengwenyi.mp3demo.constant.RedisKeyConst;
import com.fengwenyi.mp3demo.rediskey.BasePrefix;

/**
 * @author : Caixin
 * @date 2019/4/25 15:53
 */

public class RedisKey extends BasePrefix {

    public static RedisKey getRedisConstantKey = new RedisKey(RedisKeyConst.REDIS_KEY_CONSTANT);

    public RedisKey(String prefix) {
        super(prefix);
    }

    public RedisKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}

