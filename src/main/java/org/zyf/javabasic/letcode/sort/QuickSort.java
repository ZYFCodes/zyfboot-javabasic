package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 快速排序（Quick Sort）
 * 基本思路是选择一个元素作为基准值（Pivot），将待排序序列分割成两个子序列：
 * 小于等于基准值的元素序列和大于基准值的元素序列，然后对这两个子序列递归地进行快速排序。
 * @date 2023/4/15  23:28
 */
public class QuickSort {
    /**
     * 快速排序算法
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
        /*进行分割操作*/
        int pivotIndex = partition(arr, left, right);
        /*递归地对小于等于基准值的子序列进行快速排序*/
        quickSort(arr, left, pivotIndex - 1);
        /*递归地对大于基准值的子序列进行快速排序*/
        quickSort(arr, pivotIndex + 1, right);
    }

    /**
     * 分割操作
     *
     * @param arr   待分割的数组
     * @param left  待分割序列的左端点
     * @param right 待分割序列的右端点
     * @return 分割元素的下标
     */
    private static int partition(int[] arr, int left, int right) {
        /*选取基准值*/
        int pivot = arr[left];
        int i = left, j = right;
        /*当 i 和 j 相遇时退出循环*/
        while (i < j) {
            /*从右往左找到第一个小于基准值的元素*/
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            /*将该元素放到基准值的左边*/
            arr[i] = arr[j];
            /*从左往右找到第一个大于基准值的元素*/
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            /*将该元素放到基准值的右边*/
            arr[j] = arr[i];
        }
        /*将基准值放到最终位置*/
        arr[i] = pivot;
        /*返回基准值的下标*/
        return i;
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
