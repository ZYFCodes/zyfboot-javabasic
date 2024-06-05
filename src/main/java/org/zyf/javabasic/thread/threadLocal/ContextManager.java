package org.zyf.javabasic.thread.threadLocal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * @program: zyfboot-javabasic
 * @description: 用于管理上下文信息
 * @author: zhangyanfeng
 * @create: 2024-06-02 13:48
 **/
public class ContextManager {
    // 静态变量，维护不同线程的上下文
    private static final ThreadLocal<ContextManager> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    // 实例变量，维护每个上下文中所有的状态数据
    private final ConcurrentMap<String, Object> values = new ConcurrentHashMap<>();

    // 获取当前线程的上下文
    public static ContextManager getCurrentContext() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    // 在当前上下文设置一个状态数据
    public void set(String key, Object value) {
        if (value != null) {
            values.put(key, value);
        } else {
            values.remove(key);
        }
    }

    // 在当前上下文读取一个状态数据
    public Object get(String key) {
        return values.get(key);
    }

    // 开启一个新的上下文
    public static ContextManager beginContext() {
        ContextManager context = CONTEXT_THREAD_LOCAL.get();
        if (context != null) {
            throw new IllegalStateException("A context is already started in the current thread.");
        }
        context = new ContextManager();
        CONTEXT_THREAD_LOCAL.set(context);
        return context;
    }

    // 关闭当前上下文
    public static void endContext() {
        CONTEXT_THREAD_LOCAL.remove();
    }

    // 执行带有新的上下文的任务
    public static <X extends Throwable> void runWithNewContext(Runnable task) throws X {
        beginContext();
        try {
            task.run();
        } finally {
            endContext();
        }
    }

    // 在新的上下文中执行任务，并返回结果
    public static <T, X extends Throwable> T supplyWithNewContext(Supplier<T> supplier) throws X {
        beginContext();
        try {
            return supplier.get();
        } finally {
            endContext();
        }
    }

    public static <X extends Throwable> void runWithExistingContext(ContextManager context, Runnable task) throws X {
        supplyWithExistingContext(context, () -> {
            task.run();
            return null;
        });
    }

    public static <T, X extends Throwable> T supplyWithExistingContext(ContextManager context, Supplier<T> supplier) throws X {
        ContextManager oldContext = CONTEXT_THREAD_LOCAL.get();
        CONTEXT_THREAD_LOCAL.set(context);
        try {
            return supplier.get();
        } finally {
            if (oldContext != null) {
                CONTEXT_THREAD_LOCAL.set(oldContext);
            } else {
                CONTEXT_THREAD_LOCAL.remove();
            }
        }
    }

}
