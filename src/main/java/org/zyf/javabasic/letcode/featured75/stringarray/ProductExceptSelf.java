package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 除自身以外数组的乘积
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:56
 **/
public class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // 第一次遍历：计算前缀乘积
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // 第二次遍历：计算后缀乘积并与前缀乘积相乘
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct *= nums[i];
        }

        return answer;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = productExceptSelf(nums1);
        for (int num : result1) {
            System.out.print(num + " ");
        }
        // 输出: [24, 12, 8, 6]
        System.out.println();

        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = productExceptSelf(nums2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        // 输出: [0, 0, 9, 0, 0]
    }
}
