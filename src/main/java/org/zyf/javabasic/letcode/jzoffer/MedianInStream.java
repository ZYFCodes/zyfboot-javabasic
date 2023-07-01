package org.zyf.javabasic.letcode.jzoffer;

import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值；
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * @date 2023/6/13  23:33
 */
public class MedianInStream {
    // 最大堆，存储数据流中较小的一半数值
    private PriorityQueue<Integer> maxHeap;
    // 最小堆，存储数据流中较大的一半数值
    private PriorityQueue<Integer> minHeap;

    public MedianInStream() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    public void insert(Integer num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // 调整两个堆的大小，保证最大堆的大小不小于最小堆的大小
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public Double getMedian() {
        // 数据流中的数值个数为奇数，直接返回最大堆的堆顶元素
        if (maxHeap.size() > minHeap.size()) {
            return (double) maxHeap.peek();
        }
        // 数据流中的数值个数为偶数，返回最大堆和最小堆堆顶元素的平均值
        else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianInStream solution = new MedianInStream();

        // 向数据流中插入数值
        solution.insert(1);
        solution.insert(2);
        solution.insert(3);
        solution.insert(4);

        // 获取中位数并打印结果// 输出: 2.5
        System.out.println(solution.getMedian());
    }

}
