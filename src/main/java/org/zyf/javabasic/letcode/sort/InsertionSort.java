package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 插入排序（Insertion Sort）
 * 基本思想是将一个记录插入到已经排好序的有序序列中，直到整个序列有序为止。
 * @date 2023/4/15  22:40
 */
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        /*i从1开始，因为第一个元素已经是有序的*/
        for (int i = 1; i < n; i++) {
            /*待插入的元素，当前待排序元素*/
            int key = arr[i];
            /*从已排序序列中的最后一个元素开始比较*/
            int j = i - 1;

            /*将已排序序列中大于待插入元素的元素依次后移*/
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            /*将待插入元素插入到已排序序列中的合适位置*/
            arr[j + 1] = key;
        }
    }

    public static void insertionSortOpti(int[] arr) {
        int n = arr.length;
        /*优化1：二分插入排序*/
        for (int i = 1; i < n; i++) {
            int left = 0, right = i - 1;
            int target = arr[i];
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i; j > left; j--) {
                arr[j] = arr[j - 1];
            }
            arr[left] = target;
        }

        /*优化2：希尔排序*/
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }

        /*优化3：对小规模子序列使用插入排序*/
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }

        /*优化4：优化交换操作*/
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        /*创建一个待排序的数组*/
        int[] array = {5, 3, 8, 6, 4};
        /*对数组进行排序*/
        insertionSort(array);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
