package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 计数排序（Counting Sort）
 * 基本思想是将输入数组中的元素作为计数数组的索引，
 * 然后通过遍历输入数组，统计每个元素的出现次数，将统计结果保存在计数数组中。
 * 接着，根据计数数组中的统计结果，将输入数组中的元素放置到正确的位置上，从而得到排序后的输出数组。
 * @date 2023/4/16  12:49
 */
public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        /*找出输入数组中的最大值和最小值*/
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        /*创建计数数组并统计每个元素的出现次数*/
        int[] count = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        /*根据计数数组中的统计结果，将元素放置到正确的位置上*/
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("Before sorting: " + Arrays.toString(arr));
        countingSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr));
    }
}
