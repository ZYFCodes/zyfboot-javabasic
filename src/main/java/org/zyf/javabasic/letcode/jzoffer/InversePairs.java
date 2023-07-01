package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 在一个数组中，前面的数字比后面的数字大的情况下，将这样的数字对称为一个逆序对。
 * 求出数组中逆序对的总数。
 * @date 2023/6/3  22:30
 */
public class InversePairs {
    // 逆序对的计数器
    private int count;

    /**
     * 可以利用归并排序的思想来解决这个问题。归并排序的过程中，将两个有序的子数组合并成一个有序的数组时，可以统计逆序对的个数。具体步骤如下：
     * 	1.	将原数组不断二分，直到划分成只有一个元素的子数组。
     * 	2.	将相邻的两个子数组合并，合并的过程中统计逆序对的个数。
     * 	3.	将合并后的子数组继续合并，直到最终合并成一个完整的有序数组。
     * 	4.	返回逆序对的总数。
     * 时间复杂度为 O(nlogn)，其中 n 为数组的长度。
     */
    public int inversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        mergeSort(array, 0, array.length - 1);

        return count;
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int p1 = start, p2 = mid + 1;
        int index = 0;

        while (p1 <= mid && p2 <= end) {
            if (array[p1] <= array[p2]) {
                temp[index++] = array[p1++];
            } else {
                temp[index++] = array[p2++];
                // 统计逆序对个数
                count += mid - p1 + 1;
            }
        }

        while (p1 <= mid) {
            temp[index++] = array[p1++];
        }

        while (p2 <= end) {
            temp[index++] = array[p2++];
        }

        System.arraycopy(temp, 0, array, start, temp.length);
    }

    public static void main(String[] args) {
        InversePairs solution = new InversePairs();

        // 验证例子: 数组 {7, 5, 6, 4} 中的逆序对个数
        int[] array = {7, 5, 6, 4};
        int result = solution.inversePairs(array);
        System.out.println("数组中的逆序对个数为: " + result);
    }

}
