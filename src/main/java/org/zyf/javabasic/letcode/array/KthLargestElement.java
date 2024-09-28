package org.zyf.javabasic.letcode.array;

import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 给定一个二维矩阵 matrix，
 * 其中每一行和每一列都按非递减顺序排列，
 * 同时给定一个整数 k，求矩阵中第 k 大的数。
 * @date 2023/6/14  23:43
 */
public class KthLargestElement {
    /**
     * 一种常见的解法是使用最小堆（Min Heap）来解决该问题。具体步骤如下：
     * <p>
     * 1.	创建一个大小为 k 的最小堆 minHeap。
     * 2.	遍历二维矩阵 matrix 的每个元素，将元素加入最小堆 minHeap 中。
     * 3.	如果最小堆 minHeap 的大小超过了 k，则将堆顶元素（即当前最小的元素）移除，保持堆的大小为 k。
     * 4.	遍历完所有元素后，最小堆 minHeap 中的堆顶元素即为第 k 大的数。
     * <p>
     * 通过上述步骤，我们可以找到二维矩阵中第 k 大的数。
     * 该算法的时间复杂度为 O(mnlog(k))，其中 m 和 n 分别是矩阵的行数和列数。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int findKthLargest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 创建最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 遍历二维矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minHeap.offer(matrix[i][j]);

                // 如果堆的大小超过了 k，移除堆顶元素
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        // 返回堆顶元素
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5},
                {2, 4, 7},
                {6, 8, 9}
        };
        int k = 5;

        KthLargestElement kthLargest = new KthLargestElement();
        int kthLargestElement = kthLargest.findKthLargest(matrix, k);
        // 输出 Kth Largest Element: 5
        System.out.println("Kth Largest Element: " + kthLargestElement);
    }
}
