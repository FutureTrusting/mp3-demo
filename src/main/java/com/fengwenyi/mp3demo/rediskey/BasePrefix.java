package com.fengwenyi.mp3demo.rediskey;

/**
 * @author : Caixin
 * @date 2019/4/25 15:52
 */
public class BasePrefix implements KeyPrefix{
    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {//0代表永不过期
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":" + prefix;
    }

    public String getPrefix(String code) {
        String className = getClass().getSimpleName();
        return className+":" + prefix+":"+code;
    }
}

