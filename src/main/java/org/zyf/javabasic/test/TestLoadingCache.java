package org.zyf.javabasic.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * 描述：测试LoadingCache缓存
 *
 * @author yanfengzhang
 * @date 2019-12-24 15:05
 */
public class TestLoadingCache {
    private static LoadingCache<String, String> prometheusRouterCacheConfig = CacheBuilder.newBuilder().maximumSize(3000).expireAfterWrite(600, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String name) throws Exception {
                    //在这里可以初始化加载数据的缓存信息，读取数据库中信息或者是加载文件中的某些数据信息
                    return null;
                }
            });

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            System.out.println(test());
        }
    }

    public static String test() {
        String key = "efv";
        String value = "pp";
        try {
            if (key != null) {
                return prometheusRouterCacheConfig.get(key);
            }
        } catch (Exception e) {
        }

        if (value != null) {
            prometheusRouterCacheConfig.put(key, value);
        }
        return "test";

    }
}
