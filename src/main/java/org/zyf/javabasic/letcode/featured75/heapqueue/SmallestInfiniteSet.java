package org.zyf.javabasic.letcode.featured75.heapqueue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 无限集中的最小数字
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:53
 **/
public class SmallestInfiniteSet {
    private int current;  // 当前最小整数
    private PriorityQueue<Integer> minHeap;  // 优先队列存储回退的整数
    private Set<Integer> inHeap;  // 记录优先队列中的元素，避免重复添加

    public SmallestInfiniteSet() {
        this.current = 1;  // 初始化当前最小整数为 1
        this.minHeap = new PriorityQueue<>();  // 初始化优先队列
        this.inHeap = new HashSet<>();  // 初始化哈希集合用于跟踪优先队列中的元素
    }

    // 移除并返回集合中的最小整数
    public int popSmallest() {
        if (minHeap.isEmpty()) {
            // 如果优先队列为空，则返回并移除当前最小整数，并更新当前最小整数
            return current++;
        } else {
            // 否则，从优先队列中取出并返回最小的回退整数
            int smallest = minHeap.poll();
            inHeap.remove(smallest);  // 从哈希集合中移除该整数
            return smallest;
        }
    }

    // 将一个整数添加回集合
    public void addBack(int num) {
        // 只有在 num 小于当前最小整数且不在优先队列中时才添加
        if (num < current && !inHeap.contains(num)) {
            minHeap.offer(num);  // 将 num 加入优先队列
            inHeap.add(num);  // 将 num 加入哈希集合
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();

        smallestInfiniteSet.addBack(2);
        System.out.println(smallestInfiniteSet.popSmallest()); // 1
        System.out.println(smallestInfiniteSet.popSmallest()); // 2
        System.out.println(smallestInfiniteSet.popSmallest()); // 3
        smallestInfiniteSet.addBack(1);
        System.out.println(smallestInfiniteSet.popSmallest()); // 1
        System.out.println(smallestInfiniteSet.popSmallest()); // 4
        System.out.println(smallestInfiniteSet.popSmallest()); // 5
    }
}
