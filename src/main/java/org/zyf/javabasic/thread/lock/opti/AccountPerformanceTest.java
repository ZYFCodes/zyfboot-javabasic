package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: 乐观锁和悲观锁
 * @author: zhangyanfeng
 * @create: 2024-06-08 12:34
 **/
public class AccountPerformanceTest {
    private static final int THREAD_COUNT = 100;
    private static final int ITERATIONS = 1000000;

    public static void main(String[] args) throws InterruptedException {
        Account optimisticAccount = new Account(1000);
        Account pessimisticAccount = new Account(1000);

        long startTime;
        long endTime;

        // 测试乐观锁性能
        startTime = System.currentTimeMillis();
        Thread[] optimisticThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            Account account = new Account(1000); // 创建新的账户对象
            optimisticThreads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    account.updateBalance(10);
                }
            });
            optimisticThreads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            optimisticThreads[i].join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("乐观锁总耗时：" + (endTime - startTime) + " 毫秒");
        System.out.println("乐观锁最终余额：" + optimisticAccount.getBalance());

        // 测试悲观锁性能
        startTime = System.currentTimeMillis();
        Thread[] pessimisticThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            Account account = new Account(1000); // 创建新的账户对象
            pessimisticThreads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    synchronized (account) {
                        account.updateBalance(10);
                    }
                }
            });
            pessimisticThreads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            pessimisticThreads[i].join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("悲观锁总耗时：" + (endTime - startTime) + " 毫秒");
        System.out.println("悲观锁最终余额：" + pessimisticAccount.getBalance());
    }
}
