package org.zyf.javabasic.letcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 摩尔投票算法的扩展
 * @author: zhangyanfeng
 * @create: 2023-08-17 22:26
 **/
public class MajorityElementFinder {
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // Step 1: Find potential candidates
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Verify the candidates
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        int n = nums.length;
        if (count1 > n / 3) {
            result.add(candidate1);
        }
        if (count2 > n / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        // 示例测试
        int[] nums1 = {3, 2, 3};
        System.out.println(majorityElement(nums1)); // 输出：[3]

        int[] nums2 = {1};
        System.out.println(majorityElement(nums2)); // 输出：[1]

        int[] nums3 = {1, 2};
        System.out.println(majorityElement(nums3)); // 输出：[1, 2]
    }
}
