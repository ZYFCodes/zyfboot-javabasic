package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/2  19:32
 */
public class XXArrayExceptSelf {

    /**
     * 最优解法是使用前缀积和后缀积的方法，时间复杂度为 O(n)，空间复杂度为 O(1)。
     * <p>
     * 具体步骤如下：
     * 1 遍历一次数组，计算出每个元素左侧所有元素的乘积，存入结果数组中。
     * 2 遍历一次数组，计算出每个元素右侧所有元素的乘积，和步骤 1 结果数组相乘，得到最终结果数组。
     * 这里使用两个变量 left 和 right，分别表示每个元素左侧和右侧所有元素的乘积。
     * 初始值都为 1。在遍历时，先更新结果数组中的左侧乘积，
     * 然后再从右向左遍历更新右侧乘积，并将结果数组对应位置的值乘以右侧乘积即可。
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        /*计算前缀积*/
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        /*计算后缀积并乘到前缀积中*/
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = new XXArrayExceptSelf().productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

}
