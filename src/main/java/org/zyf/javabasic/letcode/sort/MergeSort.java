package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 归并排序算法
 * 采用分治的思想来进行排序，其基本思路可以概括为以下三个步骤：
 * 分解：将待排序的n个元素分成各含n/2个元素的子序列，递归地将子序列进行归并排序，直到子序列中只剩下一个元素。
 * 合并：将两个已排好序的子序列合并成一个有序序列，即将两个有序序列不断地按照大小顺序取出元素，直到其中一个序列的元素全部取完，然后将剩余的序列中的元素直接添加到有序序列的末尾。
 * 输出：当子序列的长度达到n时，整个序列也被排序完成。
 * @date 2023/4/15  23:50
 */
public class MergeSort {
    /**
     * 归并排序算法
     *
     * @param arr   待排序的数组
     * @param left  待排序序列的左端点
     * @param right 待排序序列的右端点
     */
    public static void mergeSort(int[] arr, int left, int right) {
        /*递归结束条件：子序列的长度为1或0*/
        if (left >= right) {
            return;
        }
        /*计算中间位置*/
        int mid = left + (right - left) / 2;
        /*对左半部分进行归并排序*/
        mergeSort(arr, left, mid); 
       /*对右半部分进行归并排序
        mergeSort(arr, mid + 1, right); 
       /*合并左右两个有序序列*/
        merge(arr, left, mid, right);
    }

    /**
     * 将两个有序数组合并成一个有序数组
     *
     * @param arr   待合并的数组
     * @param left  左边有序数组的起始下标
     * @param mid   中间位置下标
     * @param right 右边有序数组的结束下标
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        /*用于存放合并后的有序序列*/
        int[] temp = new int[right - left + 1];
        /*i和j分别指向左右两个有序数组的起始下标，k指向临时数组的起始下标*/
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        /*将临时数组中的元素复制回原数组*/
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        /*创建一个待排序的数组*/
        int[] array = {5, 3, 8, 6, 4};
        /*对数组进行排序*/
        mergeSort(array, 0, 4);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
