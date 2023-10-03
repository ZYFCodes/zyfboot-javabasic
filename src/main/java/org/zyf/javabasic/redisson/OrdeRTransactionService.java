package org.zyf.javabasic.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

/**
 * @program: zyfboot-javabasic
 * @description: 假设有一个在线购物系统，用户下单时需要满足以下条件：
 * 商品库存充足。 用户账户余额充足。 支付渠道可用。
 * 只有当以上三个条件都满足时，用户的订单才能成功下单。
 * @author: zhangyanfeng
 * @create: 2023-10-03 14:41
 **/
public class OrdeRTransactionService {
    private static final String PRODUCT_STOCK_KEY = "product:12345:stock";
    private static final String USER_BALANCE_KEY = "user:1001:balance";
    private static final String PAYMENT_CHANNEL_KEY = "payment:channel:available";

    public static void main(String[] args) {
        // 创建Redisson客户端
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        RedissonClient redisson = Redisson.create(config);

        // 获取各个锁
        RLock productStockLock = redisson.getLock(PRODUCT_STOCK_KEY);
        RLock userBalanceLock = redisson.getLock(USER_BALANCE_KEY);
        RLock paymentChannelLock = redisson.getLock(PAYMENT_CHANNEL_KEY);

        try {
            // 创建事务
            TransactionOptions options = TransactionOptions.defaults();
            RTransaction transaction = redisson.createTransaction(options);

            // 加锁并提交事务
            productStockLock.lock();
            userBalanceLock.lock();
            paymentChannelLock.lock();

            transaction.commit();

            // 所有锁都成功加锁，执行订单下单操作
            System.out.println("订单下单成功");
        } catch (Exception e) {
            e.printStackTrace();
            // 事务失败时回滚锁
            productStockLock.unlock();
            userBalanceLock.unlock();
            paymentChannelLock.unlock();
        } finally {
            // 关闭Redisson客户端
            redisson.shutdown();
        }
    }
}
