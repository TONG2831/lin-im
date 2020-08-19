package com.xyz.im.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author xyz
 * @date 2019-11-17
 */
@Configuration
@EnableAsync
public class ThreadFactoryConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setKeepAliveSeconds(60);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(2048);
        executor.setThreadNamePrefix("taskService-");
        executor.initialize();
        return executor;
    }

}
