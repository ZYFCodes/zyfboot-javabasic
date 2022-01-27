package org.zyf.javabasic.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/1/25  11:41
 */
public class TestCache {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<String, List<String>> mockRuleCache = CacheBuilder.newBuilder()
                /*初始化加载一次，后续每隔十分钟刷新缓存数据*/
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                /*构建缓存*/
                .build(new CacheLoader<String, List<String>>() {
                    /*初始化加载数据的缓存信息*/
                    @Override
                    public List<String> load(String str) {
                        List<String> test = Lists.newArrayList();
                        test.add("z");
                        test.add("y");
                        test.add("f");
                        return test;
                    }
                });

        ConcurrentMap<String, List<String>> newO= mockRuleCache.asMap();

        System.out.println(mockRuleCache.get("e"));
        System.out.println(mockRuleCache.getUnchecked("r"));
        Thread.sleep(3000);
        System.out.println(mockRuleCache.getUnchecked("e"));
        Thread.sleep(3100);
        System.out.println(mockRuleCache.getUnchecked("e"));
    }
}
