package org.zyf.javabasic.letcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 要求输出所有可能达到最大长度的数组
 * @author: zhangyanfeng
 * @create: 2024-10-19 15:36
 **/
public class MaxConsecutiveOnes {
    // 方法：计算修改K次0后的最大连续1的长度，同时输出所有符合条件的数组
    public static List<int[]> longestOnesWithArrays(int[] nums, int K) {
        int left = 0; // 滑动窗口的左边界
        int right = 0; // 滑动窗口的右边界
        int maxLen = 0; // 记录最大长度
        int zeroCount = 0; // 记录窗口内0的数量
        List<int[]> resultArrays = new ArrayList<>(); // 存储符合条件的数组

        // 开始移动右边界
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++; // 如果遇到0，计数+1
            }

            // 当窗口内的0超过K时，移动左边界缩小窗口
            while (zeroCount > K) {
                if (nums[left] == 0) {
                    zeroCount--; // 缩小窗口时如果移除的是0，计数-1
                }
                left++; // 移动左边界
            }

            // 如果当前窗口长度等于当前最大长度，则保存该数组
            if (right - left + 1 == maxLen) {
                resultArrays.add(copyAndModify(nums, left, right));
            }

            // 如果找到更大的长度，清空旧结果并保存新的数组
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                resultArrays.clear(); // 清空旧结果
                resultArrays.add(copyAndModify(nums, left, right));
            }

            right++; // 移动右边界
        }

        // 打印所有符合条件的数组
        System.out.println("最大连续1的长度: " + maxLen);
        for (int[] array : resultArrays) {
            printArray(array);
        }

        return resultArrays;
    }

    // 工具方法：复制原数组并将[left, right]区间内的0变为1
    private static int[] copyAndModify(int[] nums, int left, int right) {
        int[] modifiedArray = nums.clone(); // 复制原数组
        for (int i = left; i <= right; i++) {
            if (modifiedArray[i] == 0) {
                modifiedArray[i] = 1; // 将0变为1
            }
        }
        return modifiedArray;
    }

    // 工具方法：打印数组
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 1, 0, 1, 0, 1};
        int K = 1;

        // 计算符合条件的数组并输出
        longestOnesWithArrays(nums, K);
    }
}
