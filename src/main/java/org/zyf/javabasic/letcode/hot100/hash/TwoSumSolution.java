package org.zyf.javabasic.letcode.hot100.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 两数之和
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:16
 **/
public class TwoSumSolution {
    public int[] twoSum(int[] nums, int target) {
        // 创建一个哈希表来存储已经访问过的元素及其下标
        Map<Integer, Integer> numMap = new HashMap<>();

        // 遍历数组中的每一个元素
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // 检查补数是否在哈希表中
            if (numMap.containsKey(complement)) {
                // 如果补数在哈希表中，返回补数的下标和当前元素的下标
                return new int[] { numMap.get(complement), i };
            }

            // 如果补数不在哈希表中，将当前元素和下标加入哈希表
            numMap.put(nums[i], i);
        }

        // 按题意，只会存在一个有效答案，所以不需要额外的返回值
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        TwoSumSolution solution = new TwoSumSolution();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Result 1: [" + result1[0] + ", " + result1[1] + "]"); // 输出: [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Result 2: [" + result2[0] + ", " + result2[1] + "]"); // 输出: [1, 2]

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Result 3: [" + result3[0] + ", " + result3[1] + "]"); // 输出: [0, 1]
    }
}
