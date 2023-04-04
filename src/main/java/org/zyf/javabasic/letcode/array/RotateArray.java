package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 旋转数组 (Rotate Array)的原题目是：将一个含有 n 个元素的数组向右旋转 k 步。
 * 例如，输入: [1,2,3,4,5,6,7] 和 k = 3，输出: [5,6,7,1,2,3,4]
 * 要求使用空间复杂度为 O(1) 的原地算法进行解决。
 * @date 2023/3/30  23:24
 */
public class RotateArray {

    /**
     * 旋转数组的最优解法是“三次翻转算法”，时间复杂度为 O(n)，空间复杂度为 O(1)。
     * <p>
     * 具体步骤如下：
     * 1. 将整个数组翻转，即将数组中的元素首尾颠倒，这样原数组的最后 k 个元素就被移到了数组的前面。
     * 比如上面的例子，对 [1,2,3,4,5,6,7] 进行翻转，得到 [7,6,5,4,3,2,1]。
     * 2. 然后翻转数组的前 k 个元素，这样就能将原数组的最后 k 个元素移到数组的前面。
     * 对于上面的例子，翻转前 3 个元素，即 [7,6,5,4,3,2,1] 中的 [7,6,5]，得到 [5,6,7,4,3,2,1]。
     * 3. 最后翻转数组中从第 k+1 个元素到末尾的元素，这样就能得到旋转后的数组。
     * 对于上面的例子，翻转从第 4 个元素到末尾的元素，即 [5,6,7,4,3,2,1] 中的 [4,3,2,1]，得到 [5,6,7,1,2,3,4]。
     * <p>
     * 通过这种算法，我们只需要对数组进行三次翻转，就能够得到旋转后的数组，而不需要使用额外的数组来存储中间结果，
     * 因此空间复杂度为 O(1)。而由于每个元素都只被访问了一次，时间复杂度为 O(n)。
     *
     * @param nums 输入数组
     * @param k    旋转 k 步
     */
    public void rotate(int[] nums, int k) {
        /*如果数组为null或长度为0，则直接返回*/
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        /*计算旋转次数k，若k%n=0，则不需要旋转，直接返回*/
        k %= n;
        if (k == 0) {
            return;
        }
        /*三次翻转数组:第一次翻转整个数组*/
        reverse(nums, 0, n - 1);
        /*三次翻转数组:第二次翻转前k个元素*/
        reverse(nums, 0, k - 1);
        /*三次翻转数组:第三次翻转后n-k个元素*/
        reverse(nums, k, n - 1);
    }

    /**
     * 翻转数组，使用双指针
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new RotateArray().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
