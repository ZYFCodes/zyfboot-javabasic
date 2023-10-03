package org.zyf.javabasic.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: zyfboot-javabasic
 * @description: 假设有一个共享资源，多个线程需要访问这个资源，但需要按照请求的先后顺序获取访问权，以保证公平性。
 * @author: zhangyanfeng
 * @create: 2023-10-03 14:23
 **/
public class SharedResourceService {
    private static final String RESOURCE_KEY = "shared_resource";

    public static void main(String[] args) {
        // 创建Redisson客户端
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        RedissonClient redisson = Redisson.create(config);

        // 获取公平锁
        RLock fairLock = redisson.getFairLock(RESOURCE_KEY);

        try {
            // 尝试获取锁
            fairLock.lock();

            // 执行需要访问共享资源的操作
            System.out.println("Thread " + Thread.currentThread().getId() + " is accessing the shared resource.");
            Thread.sleep(2000); // 模拟访问共享资源的耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            fairLock.unlock();
        }

        // 关闭Redisson客户端
        redisson.shutdown();
    }
}
