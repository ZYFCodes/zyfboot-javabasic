package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：输入：n = 10.     输出：4
 * 解释：小于 10 的质数一共有 2, 3, 5, 7 四个。
 * 示例 2：输入：n = 0       输出：0
 * 示例 3：输入：n = 1        输出：0
 * <p>
 * 提示：
 * - 0 <= n <= 5 * 10^6
 * @date 2023/7/10  23:05
 */
public class CountPrimes {

    /**
     * 最优解法可以通过埃拉托斯特尼筛选法（Sieve of Eratosthenes）来实现计数质数的数量。
     * 埃拉托斯特尼筛选法是一种用于查找质数的经典算法，其基本思想是通过标记法，将不是质数的数排除，最终剩下的即为质数。
     * <p>
     * 具体步骤如下：
     * 1. 创建一个大小为 n 的布尔数组 primes，用于标记每个数字是否是质数。
     * 2. 初始化数组中的所有元素为 true，表示所有数字都是质数。
     * 3. 从 2 开始遍历数组，对于每个质数 p，将所有 p 的倍数标记为 false，表示它们不是质数。
     * 4. 继续遍历数组，直到所有小于 n 的质数和其倍数都被标记为 false。
     * 5. 统计数组中所有标记为 true 的元素的个数，即为小于 n 的质数的数量。
     * 该算法的时间复杂度为 O(n * log(log(n)))，其中 n 是给定的非负整数。
     * 因为在每次遍历时，将当前质数的所有倍数标记为 false，而每个合数最多只会被标记一次。
     * 而空间复杂度为 O(n)，需要额外的布尔数组来标记每个数字是否是质数。
     */
    public static int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] primes = new boolean[n];
        // 初始化数组，假设所有数字都是质数
        for (int i = 2; i < n; i++) {
            primes[i] = true;
        }

        // 埃拉托斯特尼筛选法，从 2 开始遍历
        for (int i = 2; i * i < n; i++) {
            if (primes[i]) {
                // 将当前质数 i 的倍数标记为 false，因为它们不是质数
                for (int j = i * i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        // 统计数组中标记为 true 的元素的个数，即为质数的数量
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int n1 = 10;
        int n2 = 0;
        int n3 = 1;

        System.out.println("Input: " + n1 + " Output: " + countPrimes(n1));
        System.out.println("Input: " + n2 + " Output: " + countPrimes(n2));
        System.out.println("Input: " + n3 + " Output: " + countPrimes(n3));
    }
}
