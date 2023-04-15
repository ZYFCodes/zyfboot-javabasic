package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 堆排序的时间复杂度为 O(nlogn)，空间复杂度为 O(1)。
 * 使用的是最大堆进行排序
 * @date 2023/4/16  00:13
 */
public class HeapSort {

    /**
     * 堆排序算法
     *
     * @param arr 待排序的数组
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length;
        /*构建初始堆*/
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        /*排序*/
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    /**
     * 对指定节点进行堆调整
     *
     * @param arr  待调整的数组
     * @param i    待调整的节点
     * @param size 堆的大小
     */
    private static void heapify(int[] arr, int i, int size) {
        int largest = i;
        /*左孩子节点*/
        int l = 2 * i + 1;
        /*右孩子节点*/
        int r = 2 * i + 2;
        if (l < size && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < size && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, size);
        }
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
        heapSort(array);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
