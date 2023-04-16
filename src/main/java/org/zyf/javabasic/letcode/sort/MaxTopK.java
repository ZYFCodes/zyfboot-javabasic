package org.zyf.javabasic.letcode.sort;

import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 使用堆排序（最大堆）求最大 Top K
 * @date 2023/4/16  12:14
 */
public class MaxTopK {
    public static int[] getMaxTopK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }

        /*创建一个最大堆，使用自定义的比较器*/
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        /*遍历数组，将前 k 个元素加入最大堆*/
        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }

        /*遍历数组中剩余的元素，与最大堆的堆顶元素比较*/
        /*若当前元素比堆顶元素大，则将堆顶元素出堆，当前元素入堆*/
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }

        /*将最大堆中的元素取出放入结果数组*/
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 8, 1, 7, 6};
        int k = 3;
        int[] result = getMaxTopK(arr, k);
        System.out.println("最大的 " + k + " 个元素是：");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
