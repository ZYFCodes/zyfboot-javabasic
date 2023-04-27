package org.zyf.javabasic.distributedlock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yanfengzhang
 * @description 简单的基于数据库的分布式锁的示例
 * @date 2023/4/24  23:09
 */
public class DistributedLockForDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/zyfbasic";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // 获取锁
    public static boolean acquireLock(String lockName, int timeout) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO `locks` (`name`, `holder`) VALUES (?, ?)")) {
                stmt.setString(1, lockName);
                stmt.setString(2, "node1");
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                // 锁已经存在，说明已经被其他节点持有
                if (System.currentTimeMillis() - startTime > timeout) {
                    // 超时退出
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    // 线程被中断
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // 释放锁
    public static void releaseLock(String lockName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `locks` WHERE `name` = ?")) {
            stmt.setString(1, lockName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    // 使用锁
    public static void doSomethingWithLock() {
        String lockName = "my_lock";
        if (acquireLock(lockName, 5000)) {
            try {
                // 执行需要加锁的操作
                System.out.println("Lock acquired. Doing something...");
                Thread.sleep(5000);
                System.out.println("Finished.");
            } catch (InterruptedException e) {
                // 线程被中断
                Thread.currentThread().interrupt();
            } finally {
                releaseLock(lockName);
            }
        } else {
            System.out.println("Failed to acquire lock.");
        }
    }

    // 主程序
    public static void main(String[] args) {
        doSomethingWithLock();
    }
}
