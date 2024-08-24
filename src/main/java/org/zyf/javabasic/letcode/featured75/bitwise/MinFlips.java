package org.zyf.javabasic.letcode.featured75.bitwise;

/**
 * @program: zyfboot-javabasic
 * @description: 或运算的最小翻转次数
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:44
 **/
public class MinFlips {
    public int minFlips(int a, int b, int c) {
        int ans = 0; // 记录总的翻转次数

        // 遍历每一位（最多 31 位，因为 10^9 的二进制表示最多 30 位）
        for (int i = 0; i < 31; ++i) {
            // 提取当前位的值
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            int bitC = (c >> i) & 1;

            if (bitC == 0) {
                // 如果 c 的当前位为 0，a 和 b 的当前位都需要为 0
                ans += bitA + bitB;
                // 当 a[i] 和 b[i] 都是 1 时，需要翻转两个 1 为 0
            } else {
                // 如果 c 的当前位为 1，a 和 b 的当前位需要至少有一个为 1
                ans += (bitA + bitB == 0) ? 1 : 0;
                // 当 a[i] 和 b[i] 都是 0 时，需要翻转至少一个位为 1
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        MinFlips solution = new MinFlips();

        // 测试用例 1
        int a1 = 2, b1 = 6, c1 = 5;
        System.out.println(solution.minFlips(a1, b1, c1)); // 预期输出: 3

        // 测试用例 2
        int a2 = 4, b2 = 2, c2 = 7;
        System.out.println(solution.minFlips(a2, b2, c2)); // 预期输出: 1

        // 测试用例 3
        int a3 = 1, b3 = 2, c3 = 3;
        System.out.println(solution.minFlips(a3, b3, c3)); // 预期输出: 0

        // 边界测试用例
        int a4 = 1, b4 = 1, c4 = 0;
        System.out.println(solution.minFlips(a4, b4, c4)); // 预期输出: 2
    }
}
