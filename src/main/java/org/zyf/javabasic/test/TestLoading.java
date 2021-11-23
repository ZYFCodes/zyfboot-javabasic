package org.zyf.javabasic.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/11/23  14:17
 */
public class TestLoading {
    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, Long> brandCache = CacheBuilder.newBuilder()
                .maximumSize(200000)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String wmPoiId) throws Exception {

                        return 555L;
                    }

                });
        brandCache.put("java金融",555L);
        Thread.sleep(1*1000);
        System.out.println(brandCache.getUnchecked("java金融"));
        Thread.sleep(1*1000);
        System.out.println(brandCache.getUnchecked("java金融"));
        Thread.sleep(1*1000);
        System.out.println(brandCache.getUnchecked("java金融"));

        Thread.sleep(1000);
        System.out.println(brandCache.getUnchecked("java金融"));
        Thread.sleep(3000);
        System.out.println(brandCache.getUnchecked("java金融"));

        Thread.sleep(3001);
        System.out.println(brandCache.getUnchecked("java金融"));
    }
}
