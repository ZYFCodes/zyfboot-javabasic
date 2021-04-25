package org.zyf.javabasic.thread.toolstest.readwritelock;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yanfengzhang
 * @description 实现缓存的按需加载
 * @date 2021/4/15  22:57
 */
public class CacheForLoadOnDemand {
    final Map cache = Maps.newHashMap();
    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    final Lock readCacheLock = readWriteLock.readLock();
    final Lock writeCacheLock = readWriteLock.writeLock();

    Object get(Object key) {
        Object result = null;
        /*读缓存*/
        readCacheLock.lock();
        try {
            result = cache.get(key);
        } finally {
            readCacheLock.unlock();
            ;
        }

        /*如果缓存中存在，则直接返回该值*/
        if (null != result) {
            return result;
        }

        /*缓存中不存在，查询数据库返回并进行设置缓存*/
        writeCacheLock.lock();
        try {
            /*再次进行验证：原因是在高并发的场景下，有可能会有多线程竞争写锁。
            假设缓存是空的，没有缓存任何东西，如果此时有三个线程T1、T2和T3同时调用get()方法，并且参数key也是相同的。
            那么它们会同时执行到代码writeCacheLock.lock()处，但此时只有一个线程能够获得写锁，
            假设是线程T1，线程T1获取写锁之后查询数据库并更新缓存，最终释放写锁。
            此时线程T2和T3会再有一个线程能够获取写锁，假设是T2，如果不采用再次验证的方式，此时T2会再次查询数据库。
            T2释放写锁之后，T3也会再次查询一次数据库。而实际上线程T1已经把缓存的值设置好了，T2、T3完全没有必要再次查询数据库。
            所以，再次验证的方式，能够避免高并发场景下重复查询数据的问题*/
            result = cache.get(key);
            if (null == result) {
                /*todo 查询数据库 valueFromDB*/
                String valueFromDB = "defalut";
                result = valueFromDB;
                /*这个缓存虽然解决了缓存的初始化问题，但是没有解决缓存数据与源头数据的同步问题，这里的数据同步指的是保证缓存数据和源头数据的一致性。
                解决数据同步问题的一个最简单的方案就是超时机制。
                所谓超时机制指的是加载进缓存的数据不是长久有效的，而是有时效的，当缓存的数据超过时效，也就是超时之后，这条数据在缓存中就失效了。
                而访问缓存中失效的数据，会触发缓存重新从源头把数据加载进缓存。
                也可以在源头数据发生变化时，快速反馈给缓存，但这个就要依赖具体的场景了。
                例如MySQL作为数据源头，可以通过近实时地解析binlog来识别数据是否发生了变化，如果发生了变化就将最新的数据推送给缓存。
                另外，还有一些方案采取的是数据库和缓存的双写方案。
                 */
                cache.put(key, result);
            }

        } finally {
            writeCacheLock.unlock();
        }

        return result;
    }
}
