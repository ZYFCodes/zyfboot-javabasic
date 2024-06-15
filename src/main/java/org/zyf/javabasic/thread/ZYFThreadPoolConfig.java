package org.zyf.javabasic.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义的线程池
 * @author: zhangyanfeng
 * @create: 2022-02-09 22:54
 **/
@Configuration
public class ZYFThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor customThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(100);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("custom-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
