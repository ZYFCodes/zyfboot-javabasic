package org.zyf.javabasic.letcode.hot100.ordinaryarray;

/**
 * @program: zyfboot-javabasic
 * @description: 缺失的第一个正数
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:38
 **/
public class FirstMissingPositiveSolution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 步骤 1：处理不合法的元素
        // 将不在范围 [1, n] 内的元素标记为 n+1（超出范围的数）
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        // 步骤 2：使用原地哈希方法标记出现的正整数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                // 标记 num 位置的值为负数，表示 num 出现过
                if (nums[num - 1] > 0) {
                    nums[num - 1] = -nums[num - 1];
                }
            }
        }

        // 步骤 3：查找第一个未出现的正整数
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // 如果所有位置都被标记，则返回 n+1
        return n + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositiveSolution solution = new FirstMissingPositiveSolution();

        // 测试用例 1
        int[] nums1 = {1, 2, 0};
        System.out.println(solution.firstMissingPositive(nums1));  // 输出: 3

        // 测试用例 2
        int[] nums2 = {3, 4, -1, 1};
        System.out.println(solution.firstMissingPositive(nums2));  // 输出: 2

        // 测试用例 3
        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println(solution.firstMissingPositive(nums3));  // 输出: 1
    }
}
