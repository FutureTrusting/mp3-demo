package com.fengwenyi.mp3demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : Caixin
 * @date 2019/5/27 9:55
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix="thread")
public class ThreadPoolTaskProperties {
    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 线程池运行的最大线程数
     */
    private int maxPoolSize;

    /**
     * 阻塞队列的长度
     */
    private int queueCapacity;

    /**
     * 最大空闲线程存活时间
     */
    private int keepAliveSeconds;
}
