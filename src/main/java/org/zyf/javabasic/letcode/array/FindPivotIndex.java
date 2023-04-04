package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * 示例1：输入：nums = [1, 7, 3, 6, 5, 6]       输出：3
 * 解释：索引3（nums[3] = 6）左侧数之和（1 + 7 + 3 = 11），右侧数之和（5 + 6 = 11），二者相等。
 * 示例2：输入：nums = [1, 2, 3]      输出：-1
 * 解释：数组中不存在满足此条件的中心索引。
 * <p>
 * 注意：
 * 1.nums 的长度范围为 [0, 10000]。
 * 2.任何一个 nums[i] 将会是一个范围在 [-1000, 1000]之间的整数。
 * @date 2023/4/2  00:29
 */
public class FindPivotIndex {

    /**
     * 寻找数组的中心索引可以使用前缀和的思想解决，
     * 假设数组的总和为 sum，那么只要从左往右遍历数组，依次累加左侧的元素和 leftSum，右侧的元素和 rightSum 就可以找到中心索引。
     * <p>
     * 具体地，假设当前遍历到的索引为 i，如果 i 是中心索引，则满足以下条件：
     * 1 leftSum = rightSum - nums[i]，即左侧元素和等于右侧元素和减去当前元素值。
     * 2 sum - nums[i] - leftSum = leftSum，即数组总和减去当前元素值减去左侧元素和等于左侧元素和。
     * 根据以上条件，只需要从左往右遍历一次数组，依次计算左侧元素和，再判断是否满足上述条件即可找到中心索引。
     * 如果遍历完整个数组都没有找到中心索引，则说明数组没有中心索引，返回 -1。
     * 该算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     */
    public int pivotIndex(int[] nums) {
        /*首先计算整个数组的总和*/
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            /*判断是否是中心索引，如果是则直接返回*/
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        /*如果找不到中心索引，则返回 -1*/
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println(new FindPivotIndex().pivotIndex(nums1));

        int[] nums2 = {1, 2, 3};
        System.out.println(new FindPivotIndex().pivotIndex(nums2));

        int[] nums3 = {2, 1, -1};
        System.out.println(new FindPivotIndex().pivotIndex(nums3));
    }
}
