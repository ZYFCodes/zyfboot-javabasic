package org.zyf.javabasic.letcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/9  19:51
 */
public class ContainsDuplicate {
    /**
     * 最优解法使用哈希表（HashMap）来解决，思路如下：
     * <p>
     * 创建一个哈希表，用于存储数组中的元素及其出现的次数。
     * 遍历数组中的每个元素，判断该元素是否已经在哈希表中存在。
     * 如果该元素在哈希表中已经存在，则说明数组中存在重复元素，返回 true。
     * 否则，在哈希表中将该元素加入，并将对应的值设为 1。
     * 重复步骤 2、3、4，直到遍历完整个数组。
     */
    public boolean containsDuplicate(int[] nums) {
        /*创建哈希表，用于存储已经遍历过的元素*/
        Set<Integer> set = new HashSet<>();

        /*遍历整个数组*/
        for (int num : nums) {
            /*如果当前元素已经在哈希表中存在，说明数组中存在重复元素，返回 true*/
            if (set.contains(num)) {
                return true;
            } else {
                /*否则，将当前元素加入哈希表中*/
                set.add(num);
            }
        }
        /*如果遍历完整个数组，都没有发现重复元素，则返回 false*/
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        /*输出 true*/
        System.out.println(new ContainsDuplicate().containsDuplicate(nums1));
        /*输出 false*/
        System.out.println(new ContainsDuplicate().containsDuplicate(nums2));
        /*输出 true*/
        System.out.println(new ContainsDuplicate().containsDuplicate(nums3));
    }


}
