//package org.zyf.javabasic.redisson;
//
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.redisson.api.RedissonRedLock;
//import org.redisson.config.Config;
//
///**
// * @program: zyfboot-javabasic
// * @description: 使用RedissonRedLock的业务场景
// * @author: zhangyanfeng
// * @create: 2023-10-03 15:24
// **/
//public class PurchaseRedService {
//    private static final String PRODUCT_STOCK_KEY = "product:12345:stock";
//    private static final String USER_BALANCE_KEY = "user:1001:balance";
//    private static final int LOCK_TIMEOUT = 10; // 锁超时时间，秒
//
//    public static void main(String[] args) {
//        // 创建Redisson客户端连接多个Redis节点
//        Config config1 = new Config();
//        config1.useSingleServer()
//                .setAddress("redis://host1:6379");
//
//        Config config2 = new Config();
//        config2.useSingleServer()
//                .setAddress("redis://host2:6379");
//
//        Config config3 = new Config();
//        config3.useSingleServer()
//                .setAddress("redis://host3:6379");
//
//        RedissonClient redisson1 = Redisson.create(config1);
//        RedissonClient redisson2 = Redisson.create(config2);
//        RedissonClient redisson3 = Redisson.create(config3);
//
//        // 获取商品库存锁、用户余额锁
//        RLock productStockLock = redisson1.getLock(PRODUCT_STOCK_KEY);
//        RLock userBalanceLock = redisson2.getLock(USER_BALANCE_KEY);
//
//        // 创建红锁，关联多个锁
//        RedissonRedLock redLock = new RedissonRedLock(productStockLock, userBalanceLock);
//
//        try {
//            // 尝试获取红锁，等待10秒，锁超时时间为10秒
//            if (redLock.tryLock(LOCK_TIMEOUT, LOCK_TIMEOUT)) {
//                // 获取红锁成功，执行购买操作
//
//                // 检查库存是否足够
//                int currentStock = getCurrentStock();
//                if (currentStock > 0) {
//                    // 扣减库存
//                    currentStock--;
//                    updateStock(currentStock);
//
//                    // 扣减用户余额
//                    double currentBalance = getCurrentBalance();
//                    double purchaseAmount = 100.0; // 假设购买商品价格为100
//                    if (currentBalance >= purchaseAmount) {
//                        currentBalance -= purchaseAmount;
//                        updateBalance(currentBalance);
//
//                        System.out.println("购买成功，剩余库存：" + currentStock + "，剩余余额：" + currentBalance);
//                    } else {
//                        System.out.println("余额不足，购买失败");
//                    }
//                } else {
//                    System.out.println("库存不足，购买失败");
//                }
//            } else {
//                System.out.println("获取红锁失败，购买失败");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // 释放红锁
//            redLock.unlock();
//
//            // 关闭Redisson客户端
//            redisson1.shutdown();
//            redisson2.shutdown();
//            redisson3.shutdown();
//        }
//    }
//
//    private static int getCurrentStock() {
//        // 模拟从数据库或缓存中获取当前库存数量的操作
//        return 10;
//    }
//
//    private static void updateStock(int newStock) {
//        // 模拟更新数据库或缓存中库存数量的操作
//    }
//
//    private static double getCurrentBalance() {
//        // 模拟从数据库或缓存中获取当前用户余额的操作
//        return 500.0;
//    }
//
//    private static void updateBalance(double newBalance) {
//        // 模拟更新数据库或缓存中用户余额的操作
//    }
//}
