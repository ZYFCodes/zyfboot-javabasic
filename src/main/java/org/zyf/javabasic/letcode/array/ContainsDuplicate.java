package org.zyf.javabasic.letcode.array;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。
 * 如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * 示例 1:输入: [1,2,3,1]  输出: true
 * 示例 2:输入: [1,2,3,4]  输出: false
 * 示例 3:输入: [1,1,1,3,3,4,3,2,4,2] 输出: true
 * <p>
 * 这个题目的最优解是使用哈希表来存储元素出现次数，然后遍历数组，
 * 对于每个元素，在哈希表中查找是否存在对应的元素，
 * 如果存在，则说明有重复元素，返回true，否则将元素加入哈希表中，继续遍历。
 * 时间复杂度为O(n)，空间复杂度为O(n)。
 * @date 2023/3/31  23:13
 */
public class ContainsDuplicate {

    /**
     * 解法一：哈希表
     * 思路：使用哈希表记录每个数字出现的次数，如果出现次数大于等于 2 次，则说明存在重复元素。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums 输入数组
     * @return 如果数组中每个元素都不相同，则返回 false
     */
    public boolean containsDuplByMap(int[] nums) {
        /*边界条件检查*/
        if (nums == null || nums.length == 0) {
            return false;
        }

        Map<Integer, Integer> map = Maps.newHashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return true;
            } else {
                map.put(num, 1);
            }
        }
        return false;
    }

    /**
     * 解法二：排序
     * 思路：先将数组排序，再从前往后遍历，如果相邻元素相等，则说明存在重复元素。
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     *
     * @param nums 输入数组
     * @return 如果数组中每个元素都不相同，则返回 false
     */
    public boolean containsDuplBySort(int[] nums) {
        /*边界条件检查*/
        if (nums == null || nums.length == 0) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {1, 2, 2, 3};

        System.out.println("Test Case 1:   Input:" + Arrays.toString(nums1)
                + ",Output:" + new ContainsDuplicate().containsDuplByMap(nums1));
        System.out.println("Test Case 2:   Input:" + Arrays.toString(nums2)
                + ",Output:" + new ContainsDuplicate().containsDuplByMap(nums2));
    }

}
