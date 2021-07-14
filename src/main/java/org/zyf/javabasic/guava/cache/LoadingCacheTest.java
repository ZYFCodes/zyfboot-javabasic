package org.zyf.javabasic.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 描述：Guava提供了一套缓存类，如果希望系统牺牲内存空间去执行的更快速，可以使用它
 *
 * @author yanfengzhang
 * @date 2019-12-24 20:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoadingCacheTest {
    LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            //设置缓存大小
            .maximumSize(1000)
            //设置到期时间
            .expireAfterWrite(10, TimeUnit.MINUTES)
            //设置缓存里的值两分钟刷新一次
            .refreshAfterWrite(2, TimeUnit.MINUTES)
            // 设置缓存在写入10分钟后，通过CacheLoader的load方法进行刷新
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            //开启缓存的统计功能
            .recordStats()
            //构建缓存
            .build(new CacheLoader<String, Object>() {
                //此处实现如果根据key找不到value需要去如何获取
                @Override
                public Object load(String s) throws Exception {
                    return new User();
                }

                //如果批量加载有比反复调用load更优的方法则重写这个方法
                @Override
                public Map<String, Object> loadAll(Iterable<? extends String> keys) throws Exception {
                    return super.loadAll(keys);
                }
            });

    /**
     * 功能描述：基本测试 注意value不能为null
     *
     * @author yanfengzhang
     * @date 2019-12-24 20:46
     */
    @Test
    public void testGet() throws ExecutionException {
        cache.put("aa", "rf");
        cache.get("aa");
        System.out.println("当key存在时返回对应值：aa--->rf  ======> 输入aa，返回：" + cache.get("aa"));
        System.out.println("当key不存在时返回对应值： 输入rfg，返回：" + cache.get("rfg"));
        TestCase.assertEquals("rf", cache.get("aa"));
    }

    /**
     * 功能描述：除了在build的时候设置没有key的调用方法外我们还能在调用的时候手动写
     *
     * @author yanfengzhang
     * @date 2019-12-24 20:47
     */
    @Test
    public void testGetBuild() throws ExecutionException {
        String key = "rfxd";
        cache.get(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "fv";
            }
        });
        System.out.println("当key不存在时返回默认值： 输入rfg，返回：" + cache.get("key"));
    }

    /**
     * 功能描述：缓存回收 除了不能超过大小和设定的时间自动回收外还可以调用方法手动回收
     *
     * @author yanfengzhang
     * @date 2019-12-24 20:57
     */
    @Test
    public void testClean() {
        //个别清除
        cache.invalidate("aa");
        //批量清除
        cache.invalidateAll(Lists.newArrayList("cc", "dd"));
        //清除所有缓存项
        cache.invalidateAll();
        //清理的时机：在写操作时顺带做少量的维护工作，或者偶尔在读操作时做——如果写操作实在太少的话
        //如果想自己维护则可以调用Cache.cleanUp();
        cache.cleanUp();
    }

    /**
     * 功能描述：有时候需要缓存中的数据做出变化重载一次,这个过程可以异步执行
     *
     * @author yanfengzhang
     * @date 2019-12-24 20:58
     */
    @Test
    public void testRefresh() {
        cache.refresh("aa");
    }

    /**
     * 功能描述：可以调用一下缓存的统计查看缓存的使用情况(需要在构建时开启)
     *
     * @author yanfengzhang
     * @date 2019-12-24 21:03
     */
    @Test
    public void testCacheStats() {
        CacheStats cacheStats = cache.stats();

        //缓存命中率
        cacheStats.hitRate();
        //加载新值的平均时间，单位为纳秒
        cacheStats.averageLoadPenalty();
        //缓存项被回收的总数，不包括显式清除
        cacheStats.evictionCount();
    }

    class User {
        private String name;
        private Integer age;
    }
}
