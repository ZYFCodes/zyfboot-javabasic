package org.zyf.javabasic.letcode.queue;

import java.util.LinkedList;

/**
 * @author yanfengzhang
 * @description 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value需要返回 -1
 * @date 2023/4/9  16:19
 */
public class MaxQueue {
    /*存储正常数据的队列*/
    private LinkedList<Integer> queue;
    /*存储当前队列中最大值的队列*/
    private LinkedList<Integer> maxQueue;

    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        /*返回最大值队列的队首元素*/
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        /*将元素加入正常队列*/
        queue.offerLast(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            /*将小于当前元素的元素从最大值队列中弹出*/
            maxQueue.pollLast();
        }
        /*将当前元素加入最大值队列*/
        maxQueue.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        /*弹出正常队列的队首元素*/
        int front = queue.pollFirst();
        if (front == maxQueue.peekFirst()) {
            /*如果该元素是最大值队列的队首元素，则同时弹出*/
            maxQueue.pollFirst();
        }
        return front;
    }

    public static void main(String[] args) {
        MaxQueue q = new MaxQueue();
        /*输出 -1*/
        System.out.println(q.max_value());
        q.push_back(1);
        q.push_back(3);
        q.push_back(2);
        /*输出 3*/
        System.out.println(q.max_value());
        /*输出 1*/
        System.out.println(q.pop_front());
        /*输出 3*/
        System.out.println(q.max_value());
        /*输出 3*/
        System.out.println(q.pop_front());
        /*输出 2*/
        System.out.println(q.max_value());
        /*输出 2*/
        System.out.println(q.pop_front());
        /*输出 -1*/
        System.out.println(q.max_value());
    }

}
