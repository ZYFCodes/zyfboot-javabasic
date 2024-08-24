package org.zyf.javabasic.letcode.hot100.skills;

/**
 * @program: zyfboot-javabasic
 * @description: 下一个排列（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 21:20
 **/
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 步骤 1: 找到第一个递减的元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // 步骤 2: 找到第一个比 nums[i] 大的元素
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }

            // 步骤 3: 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 步骤 4: 反转 i 位置之后的部分
        reverse(nums, i + 1, n - 1);
    }

    // 交换数组中两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 反转数组的部分
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();

        // 测试用例1
        int[] nums1 = {1, 2, 3};
        np.nextPermutation(nums1);
        System.out.println("测试用例1结果: " + java.util.Arrays.toString(nums1)); // 输出：[1, 3, 2]

        // 测试用例2
        int[] nums2 = {3, 2, 1};
        np.nextPermutation(nums2);
        System.out.println("测试用例2结果: " + java.util.Arrays.toString(nums2)); // 输出：[1, 2, 3]

        // 测试用例3
        int[] nums3 = {1, 1, 5};
        np.nextPermutation(nums3);
        System.out.println("测试用例3结果: " + java.util.Arrays.toString(nums3)); // 输出：[1, 5, 1]
    }
}
