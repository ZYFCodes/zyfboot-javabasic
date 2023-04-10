package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组 nums 和一个整数 k ，
 * 请找到该数组中和为 k 的连续子数组的个数。
 * @date 2023/4/9  22:14
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        /*创建哈希表，用于存储前缀和的计数器*/
        Map<Integer, Integer> map = new HashMap<>();
        /*初始化前缀和为 0，并将其计数器设为 1*/
        map.put(0, 1);
        /*初始化计数器 count 和当前前缀和为 0*/
        int count = 0, sum = 0;
        /*遍历整个数组 nums*/
        for (int num : nums) {
            /*更新当前前缀和*/
            sum += num;
            /*计算前缀和之差*/
            int diff = sum - k;
            /*如果发现当前前缀和已经在哈希表中出现过了*/
            if (map.containsKey(diff)) {
                /*将这个位置出现的次数累加至结果中*/
                count += map.get(diff);
            }
            /*将当前前缀和的计数器加 1，并将其存入哈希表中*/
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        /*返回最终结果*/
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        /*Expected output: 2*/
        System.out.println(new SubarraySumEqualsK().subarraySum(nums1, k1));

        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        /*Expected output: 2*/
        System.out.println(new SubarraySumEqualsK().subarraySum(nums2, k2));

        int[] nums3 = {3, 2, 1};
        int k3 = 3;
        /*Expected output: 3*/
        System.out.println(new SubarraySumEqualsK().subarraySum(nums3, k3));

        int[] nums4 = {1};
        int k4 = 1;
        /*Expected output: 1*/
        System.out.println(new SubarraySumEqualsK().subarraySum(nums4, k4));

        int[] nums5 = {1, -1, 0};
        int k5 = 0;
        /*Expected output: 3*/
        System.out.println(new SubarraySumEqualsK().subarraySum(nums5, k5));
    }
}
