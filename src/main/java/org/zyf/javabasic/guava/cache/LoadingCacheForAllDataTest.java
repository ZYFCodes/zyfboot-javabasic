package org.zyf.javabasic.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.zyf.javabasic.aop.complex.service.activity.impl.AutoRenewalActivityServiceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/6/25  20:01
 */
public class LoadingCacheForAllDataTest {


    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            // 设置缓存在写入10秒后，通过CacheLoader的load方法进行刷新
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            //构建缓存
            .build(new CacheLoader<String, String>() {
                //此处实现如果根据key找不到value需要去如何获取
                @Override
                public String load(String s) throws Exception {
                    AutoRenewalActivityServiceImpl autoRenewalActivityService = new AutoRenewalActivityServiceImpl();
                    System.out.println(autoRenewalActivityService.test());
                    return "我先是这个数据哦！";
                }
            });

    public void testGetCache() {
        for (int i = 0; i < 20; i++) {
            String str = "oo";
            try {
                System.out.println(cache.get(str));
                Thread.sleep(1000);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException {

        LoadingCacheForAllDataTest loadingCacheForAllDataTest = new LoadingCacheForAllDataTest();
        loadingCacheForAllDataTest.testGetCache();
    }

}
