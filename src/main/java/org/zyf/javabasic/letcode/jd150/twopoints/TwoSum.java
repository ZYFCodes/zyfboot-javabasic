package org.zyf.javabasic.letcode.jd150.twopoints;

/**
 * @program: zyfboot-javabasic
 * @description: 两数之和 II - 输入有序数组
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:43
 **/
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0; // 左指针
        int right = numbers.length - 1; // 右指针

        // 遍历直到两个指针重合
        while (left < right) {
            int sum = numbers[left] + numbers[right]; // 计算当前指针指向的两个数的和

            if (sum == target) {
                // 如果和等于目标值，返回下标（加 1，因为题目要求从 1 开始）
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                // 如果和小于目标值，左指针向右移动
                left++;
            } else {
                // 如果和大于目标值，右指针向左移动
                right--;
            }
        }

        // 如果没有找到符合条件的两个数（题目保证有唯一解，这里只是为了编译器完整性）
        return new int[] {-1, -1};
    }
}
