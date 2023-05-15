package org.zyf.javabasic.thread.base;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/5/1  17:47
 */
public class OrderProcessor {
    // 创建线程池
    private ExecutorService executor;

    public OrderProcessor() {
        // 创建一个固定大小的线程池，核心线程数为5
        executor = Executors.newFixedThreadPool(5);
    }

    public void processOrder(Order order) {
        // 提交订单处理任务给线程池
        executor.execute(() -> {
            // 执行订单处理的逻辑
            // ...
            System.out.println("Processing order: " + order.getId());
            // ...
        });
    }

    public void shutdown() {
        // 关闭线程池
        executor.shutdown();
    }

    @Data
    static class Order{
        private int id;

        Order(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();
        // 模拟提交订单
        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i);
            orderProcessor.processOrder(order);
        }

        // 关闭线程池
        orderProcessor.shutdown();
    }
}
