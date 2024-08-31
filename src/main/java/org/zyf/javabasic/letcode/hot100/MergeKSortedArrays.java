package org.zyf.javabasic.letcode.hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: MergeKSortedArrays
 * @author: zhangyanfeng
 * @create: 2024-08-30 14:50
 **/
public class MergeKSortedArrays {
    // 定义一个内部类来存储数组的值及其来源
    static class ArrayEntry implements Comparable<ArrayEntry> {
        // 当前值
        int value;
        // 来源数组的索引
        int arrayIndex;
        // 当前元素在数组中的索引
        int elementIndex;

        public ArrayEntry(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(ArrayEntry other) {
            return Integer.compare(this.value, other.value); // 按值升序排列
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        // 使用最小堆（优先队列）来合并n个有序数组
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(); // 最小堆
        List<Integer> result = new ArrayList<>(); // 用于存储最终合并后的List

        // 初始化最小堆，将每个数组的第一个元素放入堆中
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                // 将数组的第一个元素放入堆中
                minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
            }
        }

        // 开始合并数组
        while (!minHeap.isEmpty()) {
            // 取出堆顶元素，即当前最小值
            ArrayEntry current = minHeap.poll();
            // 将最小值加入结果List中
            result.add(current.value);

            // 如果当前数组还有剩余元素，则将下一个元素加入堆中
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                minHeap.offer(new ArrayEntry(
                        arrays[current.arrayIndex][current.elementIndex + 1],
                        current.arrayIndex,
                        current.elementIndex + 1
                ));
            }
        }

        // 返回合并后的List
        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        // 调用mergeKSortedArrays方法合并数组
        List<Integer> result = mergeKSortedArrays(arrays);

        // 输出合并后的结果List
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
