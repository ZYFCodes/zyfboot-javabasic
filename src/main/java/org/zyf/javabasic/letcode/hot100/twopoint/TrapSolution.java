package org.zyf.javabasic.letcode.hot100.twopoint;

/**
 * @program: zyfboot-javabasic
 * @description: 接雨水  ​
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:09
 **/
public class TrapSolution {
    public int trap(int[] height) {
        // 初始化双指针，分别指向数组的两端
        int left = 0, right = height.length - 1;

        // 初始化左边和右边的最大高度
        int leftMax = 0, rightMax = 0;

        // 初始化接住的雨水量
        int water = 0;

        // 当左指针小于右指针时，继续遍历
        while (left < right) {
            // 如果左边柱子低于右边柱子，处理左边
            if (height[left] < height[right]) {
                // 如果当前左边的高度大于等于 leftMax，更新 leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // 否则，leftMax 大于当前高度，计算能接住的水量
                    water += leftMax - height[left];
                }
                // 将左指针右移
                left++;
            } else {
                // 如果右边柱子低于或等于左边柱子，处理右边
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // 否则，rightMax 大于当前高度，计算能接住的水量
                    water += rightMax - height[right];
                }
                // 将右指针左移
                right--;
            }
        }

        // 返回总的接住的雨水量
        return water;
    }

    public static void main(String[] args) {
        TrapSolution solution = new TrapSolution();

        // 测试用例 1
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(height1)); // 输出: 6

        // 测试用例 2
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println(solution.trap(height2)); // 输出: 9
    }
}
