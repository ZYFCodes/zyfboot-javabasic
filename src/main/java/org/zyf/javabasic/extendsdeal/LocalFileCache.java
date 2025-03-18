package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;

/**
 * @program: zyfboot-javabasic
 * @description: 正确设计：使用线程池管理线程
 * @author: zhangyanfeng
 * @create: 2025-03-08 21:31
 **/
@Slf4j
@Component
public class LocalFileCache {
    private final Executor executor;

    public LocalFileCache(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    @Async
    public void scheduleTask() {
        executor.execute(this::autoClean);
    }

    private void autoClean() {
        log.info("【正确设计】正在执行缓存清理任务...");
        saveToDisk();
    }

    private void saveToDisk() {
        try {
            Thread.sleep(500); // 模拟耗时操作
        } catch (InterruptedException e) {
            log.error("缓存保存线程被中断", e);
        }
        log.info("【正确设计】缓存保存完成");
    }

    @PreDestroy
    public void shutdown() {
        if (executor instanceof ThreadPoolTaskExecutor) {
            ((ThreadPoolTaskExecutor) executor).shutdown();
        }
    }
}
