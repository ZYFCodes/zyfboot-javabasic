package org.zyf.javabasic.letcode.hot100.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 数据流的中位数（困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 15:01
 **/
public class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // 用于存储较小的一半元素
    private PriorityQueue<Integer> minHeap; // 用于存储较大的一半元素

    /**
     * 初始化数据结构
     */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 最大堆
        minHeap = new PriorityQueue<>(); // 最小堆
    }

    /**
     * 添加一个数字到数据结构中
     */
    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll()); // 平衡两个堆

        // 保证 maxHeap 的元素数量比 minHeap 多最多一个
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * 返回目前所有元素的中位数
     */
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); // 输出 1.5
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // 输出 2.0
    }
}
