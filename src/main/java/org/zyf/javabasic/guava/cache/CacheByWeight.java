package org.zyf.javabasic.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @program: zyfboot-javabasic
 * @description: 基于权重（maximumWeight）缓存回收策略
 * @author: zhangyanfeng
 * @create: 2025-02-04 17:53
 **/
public class CacheByWeight {
    public static void main(String[] args) {
        // 创建缓存，设置最大权重为 10
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(10)  // 设置最大权重
                .weigher((String key, String value) -> value.length())  // 根据值的长度作为权重
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println("移除缓存: " + notification.getKey() + " -> " + notification.getValue());
                    }
                })
                .build();

        // 存入数据
        cache.put("A", "Apple");  // 权重 5
        cache.put("B", "Banana"); // 权重 6
        cache.put("C", "Cherry"); // 权重 6
        cache.put("D", "Date");   // 权重 4

        System.out.println("A: " + cache.getIfPresent("A"));
        System.out.println("B: " + cache.getIfPresent("B"));
        System.out.println("C: " + cache.getIfPresent("C"));
        System.out.println("D: " + cache.getIfPresent("D"));
    }
}
