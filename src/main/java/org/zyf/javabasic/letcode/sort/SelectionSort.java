package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 选择排序算法
 * 选择排序的基本思路是：每次从待排序的序列中找到最小（或最大）的元素，放到已排序序列的末尾。
 * 具体实现时，我们通过两层循环来找到最小元素的下标，然后将该元素与序列的起始位置进行交换，
 * 从而将该元素放到已排序序列的末尾。通过不断重复这个过程，直到整个序列都排好序为止。
 * @date 2023/4/15  23:10
 */
public class SelectionSort {
    /**
     * 选择排序算法
     *
     * @param arr 待排序的数组
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        /*依次选择未排序区间的最小值并将其放到已排序区间的末尾*/
        for (int i = 0; i < n - 1; i++) {
            /*记录未排序区间的最小值下标*/
            int minIndex = i;
            /*在未排序区间中寻找最小值*/
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            /*将未排序区间的最小值与已排序区间的末尾交换位置*/
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    /**
     * 交换数组中的两个元素
     *
     * @param arr 数组
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
        selectionSort(array);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
