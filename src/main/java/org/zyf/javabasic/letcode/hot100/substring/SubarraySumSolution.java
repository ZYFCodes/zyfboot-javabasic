package org.zyf.javabasic.letcode.hot100.substring;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 和为 K 的子数组
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:36
 **/
public class SubarraySumSolution {
    public int subarraySum(int[] nums, int k) {
        // 创建一个哈希表记录前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 初始化前缀和为0的情况
        prefixSumCount.put(0, 1);

        int currentSum = 0; // 当前前缀和
        int count = 0; // 和为k的子数组的数量

        // 遍历数组
        for (int num : nums) {
            // 计算当前前缀和
            currentSum += num;

            // 检查是否存在一个前缀和，使得currentSum - k存在于哈希表中
            if (prefixSumCount.containsKey(currentSum - k)) {
                count += prefixSumCount.get(currentSum - k);
            }

            // 更新哈希表中当前前缀和的出现次数
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count; // 返回和为k的子数组的个数
    }

    public static void main(String[] args) {
        SubarraySumSolution solution = new SubarraySumSolution();

        // 测试用例 1
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println(solution.subarraySum(nums1, k1)); // 输出: 2

        // 测试用例 2
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println(solution.subarraySum(nums2, k2)); // 输出: 2
    }
}
