package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 n/2 的元素。
 * 你可以假设数组是非空的，并且数组中的众数永远存在。
 * <p>
 * 示例 1：输入：[3,2,3]     输出：3
 * 示例 2：输入：[2,2,1,1,1,2,2]     输出：2
 * @date 2023/7/11  23:08
 */
public class MajorityElement {

    /**
     * 最优解法可以通过摩尔投票法（Boyer-Moore Voting Algorithm）来实现求众数。
     * 摩尔投票法是一种用于求众数的高效算法，基本思想是通过遍历数组，利用投票的方式来找到众数。
     * 具体步骤如下：
     * 1. 初始化两个变量 `candidate` 和 `count`，`candidate` 用于保存当前的候选众数，
     * `count` 用于保存当前候选众数出现的次数，初始值分别为数组的第一个元素和 1。
     * 2. 遍历数组中的每个元素，如果当前元素和 `candidate` 相同，则 `count` 加1，否则 `count` 减1。
     * 3. 在遍历过程中，如果 `count` 减为0，说明当前的 `candidate` 不再是候选众数，需要将 `candidate` 更新为当前元素，并将 `count` 重新设置为1。
     * 4. 遍历完成后，`candidate` 中保存的即为众数。
     * 由于众数的出现次数大于 n/2，所以在整个遍历过程中，众数出现的次数一定大于其他元素。因此，在最后得到的 `candidate` 就是众数。
     * 该算法的时间复杂度为 O(n)，其中 n 是数组的大小。因为需要遍历整个数组进行投票计算。
     * 而空间复杂度为 O(1)，只需要常数级的额外空间来保存候选众数和计数器。
     */
    public static int majorityElement(int[] nums) {
        // 初始化候选众数
        int candidate = nums[0];
        // 初始化候选众数出现的次数
        int count = 1;

        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                // 当前元素和候选众数相同，计数加1
                count++;
            } else {
                // 当前元素和候选众数不同，计数减1
                count--;
                if (count == 0) {
                    // 当计数减为0，更新候选众数为当前元素，并将计数重置为1
                    candidate = nums[i];
                    count = 1;
                }
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};

        System.out.println("Input: [3, 2, 3] Output: " + majorityElement(nums1));
        System.out.println("Input: [2, 2, 1, 1, 1, 2, 2] Output: " + majorityElement(nums2));
    }
}
