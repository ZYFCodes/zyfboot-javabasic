package org.zyf.javabasic.thread.threadLocal;

import org.junit.Test;

import java.util.concurrent.ExecutorService;

/**
 * @program: zyfboot-javabasic
 * @description: 验证 ContextAwareThreadPoolExecutor 是否正确传递和恢复上下文
 * @author: zhangyanfeng
 * @create: 2024-06-02 20:25
 **/
public class ContextManagerTest {
    @Test
    public void testContextAwareThreadPoolExecutor() {
        ContextManager.beginContext();
        try {
            ContextManager.getCurrentContext().set("key", "value out of thread pool");
            Runnable r = () -> {
                String value = (String) ContextManager.getCurrentContext().get("key");
                System.out.println("Value in thread pool: " + value);
            };

            ExecutorService executor = ContextAwareThreadPoolExecutor.newFixedThreadPool(10);
            executor.execute(r);
            executor.submit(r);
        } finally {
            ContextManager.endContext();
        }

        /** 执行结果
         * Value in thread pool: value out of thread pool
         * Value in thread pool: value out of thread pool
         */
    }

    @Test
    public void testContextAwareThreadPoolExecutorWithNewContext() {
        ContextManager.runWithNewContext(() -> {
            ContextManager.getCurrentContext().set("key", "value out of thread pool");
            Runnable r = () -> {
                String value = (String) ContextManager.getCurrentContext().get("key");
                System.out.println("Value in thread pool: " + value);
            };

            ExecutorService executor = ContextAwareThreadPoolExecutor.newFixedThreadPool(10);
            executor.execute(r);
            executor.submit(r);
        });

        /** 执行结果
         * Value in thread pool: value out of thread pool
         * Value in thread pool: value out of thread pool
         */
    }
}
