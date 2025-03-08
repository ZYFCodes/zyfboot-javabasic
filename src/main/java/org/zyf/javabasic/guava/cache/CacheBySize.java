package org.zyf.javabasic.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @program: zyfboot-javabasic
 * @description: 基于容量（maximumSize）缓存回收策略
 * @author: zhangyanfeng
 * @create: 2025-02-04 17:50
 **/
public class CacheBySize {
    public static void main(String[] args) {
        // 创建缓存，设置最大容量为 3
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)  // 设置最大容量
                .build();

        // 存入数据
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");
        cache.put("D", "Date");  // A 会被移除，因为它是最旧的

        // 获取缓存中的元素
        System.out.println("A: " + cache.getIfPresent("A"));  // null，因为 A 被移除了
        System.out.println("B: " + cache.getIfPresent("B"));
        System.out.println("C: " + cache.getIfPresent("C"));
        System.out.println("D: " + cache.getIfPresent("D"));
    }
}
