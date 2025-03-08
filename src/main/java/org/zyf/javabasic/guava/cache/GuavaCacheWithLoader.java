package org.zyf.javabasic.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: 自动加载数据（CacheLoader）
 * @author: zhangyanfeng
 * @create: 2025-02-04 17:39
 **/
public class GuavaCacheWithLoader {
    public static void main(String[] args) throws ExecutionException {
        // 创建带有自动加载功能的缓存
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return "自动加载: " + key;
                    }
                });

        System.out.println("获取 key1: " + cache.get("key1"));
    }
}
