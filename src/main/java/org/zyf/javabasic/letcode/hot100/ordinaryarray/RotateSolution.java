package org.zyf.javabasic.letcode.hot100.ordinaryarray;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 轮转数组​
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:22
 **/
public class RotateSolution {
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // 处理 k 大于数组长度的情况
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }

        // 复制结果数组到原数组
        System.arraycopy(result, 0, nums, 0, n);
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // 处理 k 大于数组长度的情况
        reverse(nums, 0, n - 1);  // 反转整个数组
        reverse(nums, 0, k - 1);  // 反转前 k 个元素
        reverse(nums, k, n - 1);  // 反转后 n - k 个元素
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // 处理 k 大于数组长度的情况
        int count = 0;  // 记录移动的元素数量

        for (int start = 0; count < n; start++) {
            int current = start;
            int prevValue = nums[start];

            do {
                int nextIndex = (current + k) % n;
                int temp = nums[nextIndex];
                nums[nextIndex] = prevValue;
                prevValue = temp;
                current = nextIndex;
                count++;
            } while (start != current);
        }
    }

    public static void main(String[] args) {
        RotateSolution solution = new RotateSolution();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate3(nums1, 3);
        System.out.println(Arrays.toString(nums1));  // 输出: [5, 6, 7, 1, 2, 3, 4]

        // 测试用例 2
        int[] nums2 = {-1, -100, 3, 99};
        solution.rotate3(nums2, 2);
        System.out.println(Arrays.toString(nums2));  // 输出: [3, 99, -1, -100]
    }
}
