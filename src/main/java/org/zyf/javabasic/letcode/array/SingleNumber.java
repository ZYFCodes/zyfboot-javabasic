package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 只出现一次的数字是一个经典的问题，给定一个非空整数数组，其中每个元素都出现了两次，只有一个元素出现了一次。找出这个只出现一次的元素。
 * 例如，给定数组 nums = [2,2,1]，返回 1。
 * 要求算法时间复杂度为 O(n)，并且不使用额外空间。
 * 提示：位运算符可能有用。
 * @date 2023/3/31  23:36
 */
public class SingleNumber {

    /**
     * 常见的最优解法是使用异或运算。具体思路如下：
     * 1.将所有数字进行异或运算，即将所有数字的二进制位逐位进行异或运算。
     * 由于异或运算满足交换律和结合律，所以异或运算可以随意交换操作数的位置，因此可以将所有相同的数字异或得到0。
     * 2.最终得到的结果即为只出现一次的数字，因为只有这个数字没有与其他数字配对进行异或运算，所以保留了原值。
     * 这种方法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     * <p>
     * 该算法利用了 XOR 的两个性质：
     * 任何数和 0 做异或运算，结果仍然是原来的数。
     * 任何数和其自身做异或运算，结果是 0。
     * 因此，如果我们对所有数字进行异或运算，得到的结果即为那个只出现一次的数字。
     *
     * @param nums 输入数组
     * @return 只出现一次的元素
     */
    public int singleNumber(int[] nums) {
        /*如果数组为null或长度为0，则直接返回*/
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums array is illegal!");
        }
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 12};
        System.out.println(new SingleNumber().singleNumber(nums));
    }
}
