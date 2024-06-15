package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: zyfboot-javabasic
 * @description: 账户类 Account
 * @author: zhangyanfeng
 * @create: 2024-06-08 12:33
 **/
public class Account {
    private AtomicReference<Double> balance;

    public Account(double initialBalance) {
        this.balance = new AtomicReference<>(initialBalance);
    }

    public void updateBalance(double amount) {
        boolean success = false;
        do {
            Double currentBalance = balance.get();
            Double newBalance = currentBalance + amount;
            success = balance.compareAndSet(currentBalance, newBalance);
        } while (!success);
    }

    public double getBalance() {
        return balance.get();
    }
}
