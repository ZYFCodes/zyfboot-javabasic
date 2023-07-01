package org.zyf.javabasic.letcode.jzoffer;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组nums，其中除两个数字之外，其他数字都出现了两次。
 * 请找出这两个只出现一次的数字。
 * 要求时间复杂度为O(n)，空间复杂度为O(1)。
 * @date 2023/6/4  23:35
 */
public class FindNumsAppearOnce {
    /**
     * 最优解题思路：
     * 	1.	首先，我们知道如果只有一个数字只出现一次，而其他数字都出现了两次，那么可以使用异或运算来找到该数字。
     * 	    因为相同的数字异或运算结果为0，而任何数字与0异或运算结果为其本身。
     * 	2.	对于这个问题，我们要找到两个只出现一次的数字，我们可以将所有数字进行异或运算，得到的结果为这两个数字的异或结果。假设为result。
     * 	3.	因为result不为0，所以至少存在一位上为1。我们可以找到result中第一个为1的位的位置，记为第n位。
     * 	4.	根据第n位是否为1，将数组中的数字分成两组：一组第n位为1，一组第n位为0。这样两个只出现一次的数字就被分配到了不同的组中。
     * 	5.	对于每个组内的数字，再次使用异或运算，最终得到的两个结果就是我们要找的两个只出现一次的数字。
     * 通过上述步骤，我们可以找到数组中只出现一次的两个数字。
     * 请注意，题目没有要求按照特定的顺序返回这两个数字，所以返回顺序可以任意。
     */
    public void findNumsAppearOnce(int[] nums, int[] num1, int[] num2) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        // 找到结果中第一个为1的位
        int firstBit = findFirstBitIs1(xorResult);

        // 将数组分成两组进行异或运算
        for (int num : nums) {
            if (isBit1(num, firstBit)) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    /**
     * 找到结果中第一个为1的位
     */
    private int findFirstBitIs1(int num) {
        int indexBit = 0;
        while ((num & 1) == 0) {
            num >>= 1;
            indexBit++;
        }
        return indexBit;
    }

    /**
     * 判断数字num的第indexBit位是否为1
     */
    private boolean isBit1(int num, int indexBit) {
        num >>= indexBit;
        return (num & 1) == 1;
    }

    public static void main(String[] args) {
        FindNumsAppearOnce solution = new FindNumsAppearOnce();
        int[] nums = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        solution.findNumsAppearOnce(nums, num1, num2);
        // 输出 [4]
        System.out.println(Arrays.toString(num1));
        // 输出 [6]
        System.out.println(Arrays.toString(num2));
    }

}
