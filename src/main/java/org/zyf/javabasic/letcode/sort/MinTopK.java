package org.zyf.javabasic.letcode.sort;

import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 使用堆排序（最小堆）求最小 Top K
 * @date 2023/4/16  12:13
 */
public class MinTopK {
    public static int[] getMinTopK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }

        /*创建一个最小堆，使用默认的初始容量和比较器*/
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        /*遍历数组，将前 k 个元素加入最小堆*/
        for (int i = 0; i < k; i++) {
            minHeap.offer(arr[i]);
        }

        /*遍历数组中剩余的元素，与最小堆的堆顶元素比较*/
        /*若当前元素比堆顶元素小，则将堆顶元素出堆，当前元素入堆*/
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(arr[i]);
            }
        }

        /*将最小堆中的元素取出放入结果数组*/
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 8, 1, 7, 6};
        int k = 3;
        int[] result = getMinTopK(arr, k);
        System.out.println("最小的 " + k + " 个元素是：");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
