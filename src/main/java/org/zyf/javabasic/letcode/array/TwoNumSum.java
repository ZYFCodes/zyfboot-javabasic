package org.zyf.javabasic.letcode.array;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 两数之和 (Two Sum)
 * 题目描述：在一个数组中找到两个数，使得它们的和等于一个给定的目标值。
 * @date 2023/3/30  23:19
 */
public class TwoNumSum {

    /**
     * 两数之和（Two Sum）的高效求解方法是使用哈希表（Hash Table）。
     * 具体思路是：遍历整个数组，对于每个数字，用目标值减去该数字得到一个差值，然后在哈希表中查找这个差值是否存在。
     * 如果存在，说明找到了符合条件的两个数字，返回它们的下标；
     * 如果不存在，就将当前数字加入哈希表中，继续遍历后面的数字。
     * <p>
     * 这种方法的时间复杂度是O(n)，空间复杂度是O(n)，其中n是数组中元素的个数。
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 返回对应两个数的下表
     */
    public int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            throw new IllegalArgumentException("nums array is illegal!");
        }

        /*创建一个哈希表，用于存储数字及其下标*/
        Map<Integer, Integer> twoSumMap = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            /*计算目标值与当前数字的差值*/
            int complement = target - nums[i];
            /*如果差值在哈希表中已经存在，则找到符合条件的两个数字，返回它们的下标*/
            if (twoSumMap.containsKey(complement)) {
                return new int[]{twoSumMap.get(complement), i};
            }
            /*将当前数字及其下标存入哈希表中*/
            twoSumMap.put(nums[i], i);
        }
        /*如果没有找到符合条件的两个数字，则抛出异常*/
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 使用哈希表是两数之和问题的最优解，时间复杂度为O(n)，其中n是数组中元素的个数。
     * 因为在哈希表中查找一个元素的时间复杂度是O(1)，所以对于每个数字，我们只需要一次哈希表查找就能找到它对应的另一个数字。
     * <p>
     * 除了哈希表之外，还有一种时间复杂度为O(nlogn)的解法，即先将数组排序，然后使用双指针进行查找。
     * 具体步骤如下：
     * 1 将数组进行排序。
     * 2 使用双指针i和j分别指向数组的开头和结尾。
     * 3 如果nums[i] + nums[j]等于目标值，就找到了符合条件的两个数字，返回它们的下标。
     * 4 如果nums[i] + nums[j]小于目标值，就将i指针向右移动一位。
     * 5 如果nums[i] + nums[j]大于目标值，就将j指针向左移动一位。
     * 6 重复执行步骤3到5，直到找到符合条件的两个数字或者i和j指针相遇为止。
     * 虽然这种方法的时间复杂度比哈希表略高，但是它并不需要额外的空间，因此如果空间限制很严格，可以考虑使用这种方法。
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 返回对应两个数的下表
     */
    public int[] twoSumIndex(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            throw new IllegalArgumentException("nums array is illegal!");
        }

        /*复制数组，排序后的数组将用于双指针查找*/
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        /*对数组进行排序*/
        Arrays.sort(sortedNums);
        /*初始化指针i和j，分别指向数组的开头和结尾*/
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (sortedNums[i] + sortedNums[j] == target) {
                /*如果找到符合条件的两个数字，就返回它们的下标*/
                break;
            } else if (sortedNums[i] + sortedNums[j] < target) {
                /*如果两个数字之和小于目标值，就将i指针向右移动一位*/
                i++;
            } else {
                /*如果两个数字之和大于目标值，就将j指针向左移动一位*/
                j--;
            }
        }

        if (nums[i] + nums[j] != target) {
            /*如果没有找到符合条件的两个数字，则抛出异常*/
            throw new IllegalArgumentException("No two sum solution");
        }

        /*初始化两个数字的下标*/
        int index1 = -1, index2 = -1;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == sortedNums[i] && index1 == -1) {
                /*如果找到第一个数字的下标，就将其存储在index1中*/
                index1 = k;
            } else if (nums[k] == sortedNums[j] && index2 == -1) {
                /*如果找到第二个数字的下标，就将其存储在index2中*/
                index2 = k;
            }
        }

        /*返回符合条件的两个数字的下标*/
        return new int[]{index1, index2};
    }

    /**
     * 使用双指针技巧对排序数组进行求解。
     * 因为数组是已经排序好的，所以可以将左右两个指针分别指向数组的第一个元素和最后一个元素，计算它们对应的值的和，
     * 然后根据和与目标值的大小关系移动指针，不断缩小搜索范围。
     * 具体而言，每次比较左右指针所指元素的和与目标值的大小关系：
     * 1 如果和等于目标值，说明找到了满足条件的两个数，直接返回它们的下标；
     * 2 如果和小于目标值，说明左指针所指元素过小，需要将左指针右移一位；
     * 3 如果和大于目标值，说明右指针所指元素过大，需要将右指针左移一位。
     * 这样不断地移动指针，直到找到满足条件的两个数，或者搜索范围为空，这时候说明没有满足条件的两个数，抛出异常即可。
     * 这种方法避免了使用哈希表或者暴力枚举的额外空间开销，时间复杂度为 O(n)，空间复杂度为 O(1)，是一种高效的解法。
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 返回对应两个数的下表
     */
    public int[] twoSumForSortArray(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            throw new IllegalArgumentException("nums array is illegal!");
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};

        System.out.println(Arrays.equals(new TwoNumSum().twoSum(nums, target), expected));
        System.out.println(Arrays.equals(new TwoNumSum().twoSumIndex(nums, target), expected));
        System.out.println(Arrays.equals(new TwoNumSum().twoSumForSortArray(nums, target), expected));

        target = 93;
        System.out.println(Arrays.equals(new TwoNumSum().twoSumIndex(nums, target), expected));
        System.out.println(Arrays.equals(new TwoNumSum().twoSumForSortArray(nums, target), expected));
    }
}
