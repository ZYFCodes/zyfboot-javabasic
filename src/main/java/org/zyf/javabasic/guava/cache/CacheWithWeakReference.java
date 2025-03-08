package org.zyf.javabasic.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @program: zyfboot-javabasic
 * @description: 基于引用（weakKeys / weakValues / softValues）缓存回收策略
 * @author: zhangyanfeng
 * @create: 2025-02-04 19:29
 **/
public class CacheWithWeakReference {
    public static void main(String[] args) {
        // 创建缓存，键和值都使用弱引用
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .weakKeys()  // 键使用弱引用
                .weakValues()  // 值使用弱引用
                .build();

        // 存入数据
        String key = new String("key");
        String value = new String("value");
        cache.put(key, value);

        System.out.println("缓存中的值: " + cache.getIfPresent(key));

        // 强制 GC，模拟内存回收
        key = null;
        value = null;
        System.gc();

        // 可能已经被回收
        System.out.println("GC 后缓存中的值: " + cache.getIfPresent("key"));
    }
}
