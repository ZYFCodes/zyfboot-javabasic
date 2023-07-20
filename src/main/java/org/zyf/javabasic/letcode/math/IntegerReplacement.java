package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个正整数 n，你可以做如下操作：
 * 1. 如果 n 是偶数，则用 n / 2 替换 n。
 * 2. 如果 n 是奇数，则可以用 n + 1 或 n - 1 替换 n。
 * 将 n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1：输入：8     输出：3      解释：8 -> 4 -> 2 -> 1
 * 示例 2：输入：7      输出：4     解释：7 -> 8 -> 4 -> 2 -> 1 或 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 提示：
 * - 1 <= n <= 2^31 - 1
 * @date 2023/7/3  23:04
 */
public class IntegerReplacement {

    /**
     * 递归方法：
     * - 对于偶数 n，直接将 n 除以 2，并对 n/2 继续递归调用。
     * - 对于奇数 n，可以选择将 n + 1 或 n - 1，然后对 (n+1)/2 或 (n-1)/2 继续递归调用。
     * - 递归的结束条件是 n = 1，此时不再调用递归，返回步骤数。
     * - 在递归过程中，通过比较两种替换方式得到的步骤数，选择步数较小的方案。
     */
    public static int integerReplacement(int n) {
        return integerReplacementHelper(n, 0);
    }

    /**
     * 辅助函数，递归实现整数替换的计算
     */
    private static int integerReplacementHelper(int n, int count) {
        if (n == 1) {
            return count;
        }

        if (n % 2 == 0) {
            return integerReplacementHelper(n / 2, count + 1);
        } else {
            int countPlusOne = integerReplacementHelper(n + 1, count + 1);
            int countMinusOne = integerReplacementHelper(n - 1, count + 1);
            return Math.min(countPlusOne, countMinusOne);
        }
    }

    /**
     * 迭代方法：
     * - 使用一个 while 循环，当 n 不等于 1 时进行迭代。
     * - 对于偶数 n，直接将 n 除以 2。
     * - 对于奇数 n，比较 n + 1 和 n - 1 两种替换方式得到的步骤数，选择步数较小的方案，并更新 n 的值。
     * - 循环结束时，返回步骤数。
     */
    public static int integerReplacement2(int n) {
        int count = 0;

        while (n != 1) {
            if (n % 2 == 0) {
                // 偶数，直接除以2
                n /= 2;
            } else {
                if ((n + 1) % 4 == 0 && n != 3) {
                    // 如果 n+1 后可以被4整除，且 n 不等于3，优先选择 n+1
                    n++;
                } else {
                    // 否则选择 n-1
                    n--;
                }
            }

            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int n1 = 8;
        int n2 = 7;

        System.out.println("Input: " + n1 + " Output: " + integerReplacement(n1));
        System.out.println("Input: " + n2 + " Output: " + integerReplacement(n2));
    }

}
