package org.zyf.javabasic.letcode.sort;

import java.util.Random;

/**
 * @author yanfengzhang
 * @description 快速排序算法（优化版）
 * @date 2023/4/15  23:34
 */
public class QuickSortOpti {
    /**
     * 快速排序算法（优化版）
     *
     * @param arr   待排序的数组
     * @param left  待排序序列的左端点
     * @param right 待排序序列的右端点
     */
    public static void quickSort(int[] arr, int left, int right) {
        /*递归结束条件：子序列的长度为1或0*/
        if (left >= right) {
            return;
        }
        /*选择基准值并将其交换到序列的左端点*/
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        swap(arr, left, pivotIndex);
        /*使用三数取中法选择合适的基准值*/
        int pivot = medianOfThree(arr, left, right);
        /*进行分割操作*/
        pivotIndex = partition(arr, left, right, pivot);
        /*递归地对小于等于基准值的子序列进行快速排序*/
        quickSort(arr, left, pivotIndex - 1);
        /*递归地对大于基准值的子序列进行快速排序*/
        quickSort(arr, pivotIndex + 1, right);
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

    /**
     * 三数取中法选择基准值
     *
     * @param arr   待选取的数组
     * @param left  待选取数组的左端点
     * @param right 待选取数组的右端点
     * @return 选取的基准值
     */
    private static int medianOfThree(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        return arr[mid];
    }

    /**
     * 将待排序序列分割成两个子序列
     *
     * @param arr   待分割序列
     * @param left  待分割序列的左端点
     * @param right 待分割序列的右端点
     * @param pivot 基准值
     * @return 基准值的最终位置
     */
    private static int partition(int[] arr, int left, int right, int pivot) {
        int i = left + 1, j = right;
        while (true) {
            /*找到第一个大于基准值的元素*/
            while (i < right && arr[i] <= pivot) {
                i++;
            }
            /*找到第一个小于基准值的元素*/
            while (j > left && arr[j] >= pivot) {
                j--;
            }
            /*循环结束条件*/
            if (i >= j) {
                break;
            }
            /*交换左右两个元素*/
            swap(arr, i, j);
            i++;
            j--;
        }
        /*将基准值交换到正确的位置*/
        swap(arr, left, j);
        return j;
    }

    public static void main(String[] args) {
        /*创建一个待排序的数组*/
        int[] array = {5, 3, 8, 6, 4};
        /*对数组进行排序*/
        quickSort(array, 0, 4);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
