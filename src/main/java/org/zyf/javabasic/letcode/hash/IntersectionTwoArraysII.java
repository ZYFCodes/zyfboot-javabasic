package org.zyf.javabasic.letcode.hash;

import java.util.*;

/**
 * @author yanfengzhang
 * @description 给定两个数组，编写一个函数来计算它们的交集。
 * @date 2023/4/10  23:32
 */
public class IntersectionTwoArraysII {

    /**
     * 该问题可以通过哈希表来解决。具体步骤如下：
     * <p>
     * 遍历 nums1 数组，将其中的每个元素加入哈希表中，并记录其出现次数。
     * 遍历 nums2 数组，对于其中的每个元素，如果在哈希表中出现过，则将该元素加入答案数组，并在哈希表中将其出现次数减 1。
     * 遍历结束后，返回答案数组。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        /*创建一个哈希表，用于存储 nums1 中的所有数及其出现次数*/
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /*创建一个列表，用于存储 nums1 和 nums2 的交集*/
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                /*如果当前数在哈希表中出现过，则将其添加到列表中，并将哈希表中该数的出现次数减一*/
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        /*将列表转换成数组，并返回*/
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] intersection = new IntersectionTwoArraysII().intersect(nums1, nums2);
        /*[2, 2]*/
        System.out.println(Arrays.toString(intersection));

        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        intersection = new IntersectionTwoArraysII().intersect(nums3, nums4);
        /*[4, 9]*/
        System.out.println(Arrays.toString(intersection));

    }

}
