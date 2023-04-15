package org.zyf.javabasic.letcode.sort;

import java.util.Random;
import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 迭代快速排序
 * @date 2023/4/15  23:37
 */
public class IterativeQuickSort {
    /**
     * 迭代快速排序
     *
     * @param arr   待排序的数组
     * @param left  待排序序列的左端点
     * @param right 待排序序列的右端点
     */
    public static void iterativeQuickSort(int[] arr, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()) {
            int r = stack.pop();
            int l = stack.pop();
            if (l >= r) {
                continue;
            }
            int pivotIndex = partition(arr, l, r);
            stack.push(l);
            stack.push(pivotIndex - 1);
            stack.push(pivotIndex + 1);
            stack.push(r);
        }
    }

    /**
     * 分割操作
     *
     * @param arr   待分割的数组
     * @param left  待分割序列的左端点
     * @param right 待分割序列的右端点
     * @return 基准值的下标
     */
    private static int partition(int[] arr, int left, int right) {
        // 选择基准值并将其交换到序列的左端点*/
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap(arr, left, pivotIndex);
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, left, j);
        return j;
    }

    /**
     * 交换数组中两个位置的元素
     *
     * @param arr 待交换元素所在的数组
     * @param i   第一个元素的下标
     * @param j   第二个元素的下标
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        /*创建一个待排序的数组*/
        int[] array = {5, 3, 8, 6, 4};
        /*对数组进行排序*/
        iterativeQuickSort(array, 0, 4);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
