package org.zyf.javabasic.letcode.hot100.ordinaryarray;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 除自身以外数组的乘积
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:31
 **/
public class ProductExceptSelfSolution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // 步骤 1：计算前缀乘积
        // 初始化答案数组的第一个元素
        answer[0] = 1;

        // 计算前缀乘积
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // 步骤 2：计算后缀乘积并最终更新答案数组
        int suffix = 1; // 从 1 开始，表示当前元素右侧的乘积
        for (int i = n - 1; i >= 0; i--) {
            // 更新答案数组的当前元素，乘以前缀乘积和后缀乘积
            answer[i] = answer[i] * suffix;
            // 更新后缀乘积为当前元素
            suffix *= nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        ProductExceptSelfSolution solution = new ProductExceptSelfSolution();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println(Arrays.toString(result1));  // 输出: [24, 12, 8, 6]

        // 测试用例 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.println(Arrays.toString(result2));  // 输出: [0, 0, 9, 0, 0]
    }
}
