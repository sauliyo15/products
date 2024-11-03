package com.sauliyo15.productstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(8);
        executor.setCorePoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ProductRegistryThread-");
        executor.initialize();
        return executor;
    }
}
