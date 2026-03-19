package com.orchestration.instantcard.config;

import org.mockito.Mockito;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;

@Configuration
@EnableCaching
public class NoOpSchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        return Mockito.mock(TaskScheduler.class);
    }
}
