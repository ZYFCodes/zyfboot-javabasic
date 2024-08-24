package org.zyf.javabasic.letcode.featured75.bitwise;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 比特位计数
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:36
 **/
public class CountBits {
    public int[] countBits(int num) {
        // 创建一个数组 result，用来存储从 0 到 num 每个数的二进制中 1 的个数
        int[] result = new int[num + 1];

        // 遍历 1 到 num 的每个数字
        for (int i = 1; i <= num; i++) {
            // 如果 i 是奇数，则其 1 的个数是 i-1 的 1 的个数加 1
            if (i % 2 == 1) {
                result[i] = result[i - 1] + 1;
            } else {
                // 如果 i 是偶数，则其 1 的个数等于 i/2 的 1 的个数
                result[i] = result[i / 2];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        CountBits solution = new CountBits();

        // 测试用例 1
        int num1 = 2;
        int[] result1 = solution.countBits(num1);
        System.out.println(Arrays.toString(result1)); // 预期输出: [0, 1, 1]

        // 测试用例 2
        int num2 = 5;
        int[] result2 = solution.countBits(num2);
        System.out.println(Arrays.toString(result2)); // 预期输出: [0, 1, 1, 2, 1, 2]

        // 测试用例 3: 边界情况
        int num3 = 0;
        int[] result3 = solution.countBits(num3);
        System.out.println(Arrays.toString(result3)); // 预期输出: [0]

        // 测试用例 4: 较大的输入
        int num4 = 10;
        int[] result4 = solution.countBits(num4);
        System.out.println(Arrays.toString(result4)); // 预期输出: [0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2]
    }
}
