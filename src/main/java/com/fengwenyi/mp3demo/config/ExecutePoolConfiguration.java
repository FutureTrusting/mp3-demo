package com.fengwenyi.mp3demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhouliang
 * @Date: 2019/2/28 11:23
 */
@Configuration
public class ExecutePoolConfiguration {

    @Autowired
    private ThreadPoolTaskProperties poolTaskProperties;

    @Bean(name="threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(poolTaskProperties.getCorePoolSize());
        pool.setKeepAliveSeconds(poolTaskProperties.getKeepAliveSeconds());
        pool.setQueueCapacity(poolTaskProperties.getQueueCapacity());
        pool.setMaxPoolSize(poolTaskProperties.getMaxPoolSize());
        pool.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        pool.setDaemon(Boolean.TRUE);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

}
