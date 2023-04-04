package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个由非负整数组成的数组，表示一个整数，将这个整数加上一。
 * 你可以假设这个整数不包含任何前导零，除了数字0本身。
 * 最高位数字存放在列表的首位， 数组中每个元素只存储单个数字。
 * 你可以将你的解决方案与标准的库函数进行比较。
 * 你可以假设两个数都不包含前导零，除了数字0本身。
 * @date 2023/4/2  00:16
 */
public class PlusOne {
    /**
     * 最优解法是直接模拟加法运算。
     * 从数组的最后一位开始，加上1后取余，如果余数不为0，则不会进位，直接返回数组。
     * 如果余数为0，则需要进位，进位后继续向前遍历，直到不需要进位为止。
     * <p>
     * 具体步骤如下：
     * 从数组的最后一位开始，加上1，并计算余数remainder = (digits[i] + 1) % 10，进位carry = (digits[i] + 1) / 10。
     * 将余数remainder存入当前位digits[i]，如果没有进位，直接返回digits数组。
     * 如果有进位，将carry加入下一位digits[i-1]中，继续向前遍历。
     * 如果遍历完整个数组digits后，还有进位，就需要在digits数组最前面添加一个1。
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] res1 = new PlusOne().plusOne(nums1);
        System.out.println(Arrays.toString(res1));

        int[] nums2 = {9, 9, 9};
        int[] res2 = new PlusOne().plusOne(nums2);
        System.out.println(Arrays.toString(res2));

        int[] nums3 = {0};
        int[] res3 = new PlusOne().plusOne(nums3);
        System.out.println(Arrays.toString(res3));
    }


}
