package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums[i] = nums[j]，并且 i 和 j 的差的绝对值最大为 k。
 * @date 2023/4/9  20:33
 */
public class ContainsDuplicateII {

    /**
     * 存在重复元素 II 可以使用哈希表来解决。具体步骤如下：
     * <p>
     * 创建一个哈希表用于存储元素及其下标的映射。
     * 遍历数组 nums 中的每个元素，如果元素已经在哈希表中存在，则计算其下标与哈希表中存储的下标之差是否小于等于 k。
     * 如果满足条件，说明存在重复元素，直接返回 true。
     * 如果元素不在哈希表中，则将其添加到哈希表中。
     * 遍历完整个数组 nums 后，仍然没有发现符合条件的重复元素，则返回 false。
     * 时间复杂度分析：遍历一遍数组 nums 需要 O(n) 的时间，其中 n 是数组的长度。同时，由于只用了一个哈希表，空间复杂度为 O(n)。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        /*创建一个哈希表用于存储元素及其下标的映射*/
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            /*如果元素已经在哈希表中存在，则计算其下标与哈希表中存储的下标之差是否小于等于 k*/
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                /*如果满足条件，说明存在重复元素，直接返回 true*/
                return true;
            }
            /*如果元素不在哈希表中，则将其添加到哈希表中*/
            map.put(nums[i], i);
        }
        /*遍历完整个数组 nums 后，仍然没有发现符合条件的重复元素，则返回 false*/
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean result = new ContainsDuplicateII().containsNearbyDuplicate(nums, k);
        /*输入的数组 nums 为 {1, 2, 3, 1}，k 的值为 3。
        函数的返回值为 true，因为数组中有两个值为 1 的元素，
        它们的下标之差为 3，满足题目要求。所以输出为 true。*/
        System.out.println(result);
    }


}
