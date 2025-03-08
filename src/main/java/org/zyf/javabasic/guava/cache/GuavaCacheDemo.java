package org.zyf.javabasic.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: Guava Cache 主要通过 CacheBuilder 来构建，以下是一个最简单的使用示例
 * @author: zhangyanfeng
 * @create: 2025-02-04 17:36
 **/
public class GuavaCacheDemo {
    public static void main(String[] args) {
        // 创建缓存，设置 5 秒过期
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS) // 写入后 5 秒过期
                .maximumSize(100) // 最多缓存 100 个元素
                .build();

        // 存入数据
        cache.put("key1", "value1");
        System.out.println("获取 key1: " + cache.getIfPresent("key1"));

        // 休眠 6 秒，观察缓存是否过期
        try { Thread.sleep(6000); } catch (InterruptedException e) { }
        System.out.println("6 秒后获取 key1: " + cache.getIfPresent("key1"));
    }
}
