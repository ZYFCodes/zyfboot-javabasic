package org.zyf.javabasic.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: zyfboot-javabasic
 * @description: 假设有一个电子商务网站，用户在购物时需要扣减商品的库存。
 * 由于多个用户可能同时购买相同的商品，需要确保库存的扣减是线程安全的，同时避免超卖的问题。
 * @author: zhangyanfeng
 * @create: 2023-10-03 14:18
 **/
public class InventoryService {
    private static final String PRODUCT_STOCK_KEY = "product:12345:stock";

    public static void main(String[] args) {
        // 创建Redisson客户端
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        RedissonClient redisson = Redisson.create(config);

        // 获取可重入锁
        RLock lock = redisson.getLock(PRODUCT_STOCK_KEY);

        try {
            // 尝试获取锁，最多等待10秒
            if (lock.tryLock(10, 10, java.util.concurrent.TimeUnit.SECONDS)) {
                // 获取锁成功，执行库存扣减操作
                int currentStock = getCurrentStock();
                if (currentStock > 0) {
                    // 扣减库存
                    currentStock--;
                    updateStock(currentStock);
                    System.out.println("库存扣减成功，当前库存：" + currentStock);
                } else {
                    System.out.println("库存不足，无法扣减");
                }
            } else {
                System.out.println("获取锁超时，无法扣减库存");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }

        // 关闭Redisson客户端
        redisson.shutdown();
    }

    private static int getCurrentStock() {
        // 模拟从数据库或缓存中获取当前库存数量的操作
        return 10;
    }

    private static void updateStock(int newStock) {
        // 模拟更新数据库或缓存中库存数量的操作
    }
}
