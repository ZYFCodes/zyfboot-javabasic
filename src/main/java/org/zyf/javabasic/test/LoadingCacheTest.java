package org.zyf.javabasic.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/29  16:48
 */
public class LoadingCacheTest {

    /**
     * wmPoiId -> brandId
     */
    public static LoadingCache<Long, Long> brandCache = CacheBuilder.newBuilder()
            .maximumSize(200000)
            .expireAfterAccess(100, TimeUnit.MILLISECONDS)
            .build(new CacheLoader<Long, Long>() {
                @Override
                public Long load(Long wmPoiId) throws Exception {
//                    long brandId = poiService.getPoiBrandId(wmPoiId);
//                    if (brandId == 0L) {
//                        throw new Exception("brandId == 0L ,reload later ...");
//                    }
                    return wmPoiId;
                }
            });


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(brandCache.getUnchecked((long) i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(brandCache.getUnchecked((long) i));
        }

    }
}
