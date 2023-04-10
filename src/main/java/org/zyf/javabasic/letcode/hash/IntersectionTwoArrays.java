package org.zyf.javabasic.letcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定两个数组，编写一个函数来计算它们的交集。
 * @date 2023/4/10  23:45
 */
public class IntersectionTwoArrays {

    /**
     * 该问题可以通过哈希表来解决。具体步骤如下：
     * <p>
     * 首先创建了一个 set，用于存储 nums1 中的元素。
     * 然后，遍历 nums2 中的元素，如果其在 set 中存在，则将其添加到另一个 set 中。
     * 最后，将新 set 转换为数组并返回。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        /*创建一个 set，用于存储 nums1 中的元素*/
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        /*创建一个 set，用于存储 nums2 和 nums1 中的交集*/
        Set<Integer> intersect = new HashSet<>();
        for (int num : nums2) {
            /*如果 num 存在于 set 中，则将其添加到 intersect 中*/
            if (set.contains(num)) {
                intersect.add(num);
            }
        }

        /*将 intersect 转换为数组*/
        int[] res = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            res[i++] = num;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = new IntersectionTwoArrays().intersection(nums1, nums2);
        System.out.println("Intersection of nums1 and nums2: " + Arrays.toString(result));
    }
}
