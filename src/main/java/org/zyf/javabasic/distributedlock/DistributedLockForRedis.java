//package org.zyf.javabasic.distributedlock;
//
//import redis.clients.jedis.Jedis;
//
///**
// * @author yanfengzhang
// * @description  基于缓存的分布式锁的简单示例，使用Java语言和Redis作为缓存存储
// * @date 2023/4/24  23:14
// */
//public class DistributedLockForRedis {
//    private static final String REDIS_HOST = "localhost";
//    private static final int REDIS_PORT = 6379;
//
//    // 获取锁
//    public static boolean acquireLock(String lockName, int timeout) {
//        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
//        long startTime = System.currentTimeMillis();
//        while (true) {
//            String result = jedis.set(lockName, "holder", "NX", "EX", timeout);
//            if (result != null && result.equalsIgnoreCase("OK")) {
//                // 成功获取到锁
//                return true;
//            } else if (System.currentTimeMillis() - startTime > timeout) {
//                // 超时退出
//                return false;
//            } else {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    // 线程被中断
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }
//
//    // 释放锁
//    public static void releaseLock(String lockName) {
//        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
//        jedis.del(lockName);
//    }
//
//    // 使用锁
//    public static void doSomethingWithLock() {
//        String lockName = "my_lock";
//        if (acquireLock(lockName, 5000)) {
//            try {
//                // 执行需要加锁的操作
//                System.out.println("Lock acquired. Doing something...");
//                Thread.sleep(5000);
//                System.out.println("Finished.");
//            } catch (InterruptedException e) {
//                // 线程被中断
//                Thread.currentThread().interrupt();
//            } finally {
//                releaseLock(lockName);
//            }
//        } else {
//            System.out.println("Failed to acquire lock.");
//        }
//    }
//
//    // 主程序
//    public static void main(String[] args) {
//        doSomethingWithLock();
//    }
//
//}
