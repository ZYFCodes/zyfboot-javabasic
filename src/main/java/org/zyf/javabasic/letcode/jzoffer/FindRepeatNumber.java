package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 找出数组中重复的数字。在一个长度为n的数组nums里的所有数字都在0~n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * @date 2023/6/1 23:49
 */
public class FindRepeatNumber {
    /**
     * 可以利用哈希表或者原地置换的方式来解决这个问题。下面是原地置换的解题思路：
     * 遍历数组nums，对于每个元素nums[i]，将其与索引i对应的元素nums[nums[i]]进行比较：
     * •	若nums[i]等于nums[nums[i]]，即找到了重复的数字，返回nums[i]；
     * •	否则，将nums[i]与nums[nums[i]]交换，将其放到正确的位置上，继续比较下一个元素。
     * 重复上述步骤，直到找到重复的数字或遍历完整个数组。若遍历完数组仍未找到重复的数字，则返回-1表示没有重复数字。
     * 该解法的时间复杂度为O(n)，空间复杂度为O(1)。
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            // 数组为空或长度为0，返回-1表示无重复数字
            return -1;
        }

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 当当前元素不等于索引时，需要进行交换
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    // 当前元素与它应该放置的位置上的元素相等，即找到了重复数字
                    return nums[i];
                }
                // 交换当前元素与它应该放置的位置上的元素
                swap(nums, i, nums[i]);
            }
        }

        // 遍历完整个数组仍未找到重复数字，返回-1表示无重复数字
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        // 交换数组中索引为i和索引为j的两个元素
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FindRepeatNumber solution = new FindRepeatNumber();
        // 测试输入数组
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        // 调用方法查找重复数字
        int result = solution.findRepeatNumber(nums);
        // 输出结果
        System.out.println("重复的数字是: " + result);
    }
}
