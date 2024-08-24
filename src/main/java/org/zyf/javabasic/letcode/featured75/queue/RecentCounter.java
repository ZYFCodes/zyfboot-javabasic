package org.zyf.javabasic.letcode.featured75.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 最近的请求次数
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:27
 **/
public class RecentCounter {
    // 定义队列来存储请求的时间戳
    private Queue<Integer> queue;

    // 初始化计数器，创建队列
    public RecentCounter() {
        queue = new LinkedList<>();
    }

    // 在时间 t 添加一个新请求，并返回过去 3000 毫秒内的请求数
    public int ping(int t) {
        // 将当前请求时间戳加入队列
        queue.add(t);
        // 移除不在 [t-3000, t] 范围内的请求
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        // 返回队列的大小，即为在过去 3000 毫秒内的请求数
        return queue.size();
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();

        // 测试用例
        System.out.println(recentCounter.ping(1));   // 输出: 1
        System.out.println(recentCounter.ping(100)); // 输出: 2
        System.out.println(recentCounter.ping(3001));// 输出: 3
        System.out.println(recentCounter.ping(3002));// 输出: 3
    }
}
