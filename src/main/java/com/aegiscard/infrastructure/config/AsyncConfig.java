package com.aegiscard.infrastructure.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        return Executors.newThreadPerTaskExecutor(
            r -> Thread.ofVirtual().name("aegiscard-", 0).unstarted(r)
        );
    }
}

