package org.zyf.javabasic.letcode.hot100.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 最长序列（不要求序列元素在原数组中连续）的长度
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:34
 **/
public class LongestConsecutiveSolution {
    public int longestConsecutive(int[] nums) {
        // 将所有数字放入哈希集
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 遍历数组中的每一个数字
        for (int num : nums) {
            // 只有当 num-1 不在哈希集中时，才认为 num 是一个序列的起点
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 从起点开始寻找连续的序列
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 更新最长序列的长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        LongestConsecutiveSolution solution = new LongestConsecutiveSolution();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums1)); // 输出: 4

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(solution.longestConsecutive(nums2)); // 输出: 9
    }
}
