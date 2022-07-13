package org.zyf.javabasic.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.Data;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/1  15:34
 */
public class GuavaCacheRefreshTest {
    @Data
    public class SkuCache {
        private String skuId;
        private String skuCode;
        private Long realQuantity;
    }

    AtomicInteger loadTimes = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    @Test
    public void testCacheUse() throws Exception {
        LoadingCache<String, SkuCache> loadingCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1000, TimeUnit.MILLISECONDS)
                //Prevent data reloading from failing, but the value of memory remains the same
                .expireAfterWrite(1500, TimeUnit.MILLISECONDS)
                .build(new CacheLoader<String, SkuCache>() {
                    @Override
                    public SkuCache load(String key) {
                        SkuCache skuCache = new SkuCache();
                        skuCache.setSkuCode(key + "---" + (loadTimes.incrementAndGet()));
                        skuCache.setSkuId(key);
                        skuCache.setRealQuantity(100L);
                        System.out.println("load..." + key);
                        return skuCache;
                    }

                    @Override
                    public ListenableFuture<SkuCache> reload(String key, SkuCache oldValue) throws Exception {
                        checkNotNull(key);
                        checkNotNull(oldValue);
                        System.out.println("reload...");
                        //Simulate time consuming operation
//                        Thread.sleep(1000);
                        return Futures.immediateFuture(load(key));
                    }
                });


        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    getValue(loadingCache);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.in.read();
        System.out.println("finish");
    }

    @Test
    public void refreshAfterWrite() {
        LoadingCache<String, Multimap<Long, Long>> bgCategoryQueConfigCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(100, TimeUnit.MILLISECONDS)
                /*构建缓存*/
                .build(new CacheLoader<String, Multimap<Long, Long>>() {
                    /*初始化加载数据的缓存信息*/
                    @Override
                    public Multimap<Long, Long> load(String qualificationInfoConfig) throws Exception {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                        System.out.println("后台类目和资质对应配置信息刷新开始" + dateFormat.format(new java.util.Date()));
                        Multimap<Long, Long> bgCategoryQueMultimap = HashMultimap.create();
                        bgCategoryQueMultimap.put(1L, 3L);
                        bgCategoryQueMultimap.put(1L, 2L);
                        return bgCategoryQueMultimap;
                    }
                });


        for (int i = 0; i < 8; i++) {
            try {
                String BGCATEGORY_QUE_CONFIG = "bgCategoryQueConfig";
                System.out.println("我调用了一次哦！！！" + bgCategoryQueConfigCache.get(BGCATEGORY_QUE_CONFIG));
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println("e.printStackTrace()");
            }
        }
    }


    private void getValue(LoadingCache<String, SkuCache> loadingCache) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300l);
            System.out.println(loadingCache.get("sku").toString() + " - " + count.incrementAndGet());
        }
    }
}
