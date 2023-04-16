package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 基数排序（Radix Sort）
 * 根据元素的位数或字符的位置来进行排序。
 * 基数排序的基本思想是将待排序的元素按照各位或字符的值，从低位到高位依次进行排序，
 * 直到最高位排序完成后，整个序列就变成了有序的。
 * @date 2023/4/16  14:39
 */
public class RadixSort {
    /**
     * 基数排序函数
     */
    public static void radixSort(int[] arr) {
        /*获取数组中的最大值，确定排序的位数*/
        int max = getMax(arr);

        /*对每一位进行计数排序*/
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    /**
     * 获取数组中的最大值
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 对数组按照指定位数进行计数排序
     */
    public static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        /*统计当前位的数字出现次数*/
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        /*计算累计次数*/
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        /*从后往前遍历原数组，按照当前位的数字放置到输出数组中*/
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        /*将输出数组复制回原数组*/
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("原始数组: " + Arrays.toString(arr));

        radixSort(arr);

        System.out.println("排序后数组: " + Arrays.toString(arr));
    }
}
