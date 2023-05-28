package org.zyf.javabasic.distributedlock;

import redis.clients.jedis.Jedis;

/**
 * @author yanfengzhang
 * @description 演示如何使用基于缓存的分布式锁来实现商品购买的并发控制
 * @date 2023/4/24  23:48
 */
public class ProductPurchaseService {
    private static final String PRODUCT_LOCK_PREFIX = "product_lock_";
    private static final int LOCK_TIMEOUT = 5000; // 锁的超时时间，单位为毫秒

    // 模拟一个缓存客户端，这里使用了Redis
    private Jedis jedis;

    // 初始化缓存客户端
    public ProductPurchaseService() {
        jedis = new Jedis("localhost", 6379);
    }

    // 购买商品
    public boolean purchaseProduct(String productId, String userId) {
        // 生成商品锁的key
        String productLockKey = PRODUCT_LOCK_PREFIX + productId;

        // 尝试获取锁
        long startTimestamp = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTimestamp) < LOCK_TIMEOUT) {
            // 使用SETNX命令在缓存中设置锁，仅在key不存在时设置成功
            long result = jedis.setnx(productLockKey, userId);

            // 锁设置成功
            if (result == 1) {
                // 设置锁的过期时间，防止锁无法释放导致死锁
                jedis.expire(productLockKey, LOCK_TIMEOUT / 1000);

                // 执行购买操作，这里省略具体业务逻辑
                System.out.println("用户 " + userId + " 购买了商品 " + productId);

                // 释放锁
                jedis.del(productLockKey);

                return true; // 购买成功
            }

            // 锁设置失败，等待一段时间后重试
            try {
                Thread.sleep(100); // 等待100毫秒后重试
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取锁超时，购买失败
        System.out.println("用户 " + userId + " 购买商品 " + productId + " 失败，锁超时");
        return false;
    }

}
