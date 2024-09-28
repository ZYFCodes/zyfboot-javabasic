package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 从两个整形数组中分别取出一个元素，求出这两个元素差值的绝对值，最终返回两个数组中元素差值绝对值的最小值。
 * @author: zhangyanfeng
 * @create: 2024-09-14 15:23
 **/
public class MinAbsDifference {
    public static int findMinAbsDifference(int[] arr1, int[] arr2) {
        // 边界情况处理，确保输入数组不为空
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("输入数组不能为空");
        }

        // 对两个数组排序
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0;
        int minDiff = Integer.MAX_VALUE;  // 初始化最小差值为最大值

        // 使用双指针法遍历两个数组
        while (i < arr1.length && j < arr2.length) {
            // 计算当前两个指针指向元素的差值
            int diff = Math.abs(arr1[i] - arr2[j]);

            // 更新最小差值
            minDiff = Math.min(minDiff, diff);

            // 如果发现最小差值为0，直接返回
            if (minDiff == 0) {
                return 0;
            }

            // 移动指针：较小的元素对应的指针前移
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return minDiff;  // 返回最小差值
    }

    public static void main(String[] args) {
        // 示例输入
        int[] arr1 = {1, 3, 15, 11, 2};
        int[] arr2 = {23, 127, 235, 19, 8};
        // 输出最小绝对差值
        System.out.println("最小绝对差值: " + findMinAbsDifference(arr1, arr2));
    }
}
