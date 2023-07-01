package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 我们可以用 2x1 的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用 n 个 2x1 的小矩形无重叠地覆盖一个 2xn 的大矩形，总共有多少种方法？
 * @date 2023/6/8  23:44
 */
public class CoverJZ {
    /**
     * 设覆盖 2xn 大矩形的方法数为 f(n)。我们考虑最后一步，可以使用一个 2x1 的小矩形横着放在最后一列，也可以使用两个 2x1 的小矩形竖着放在最后一列。
     * •	如果最后一列使用一个横着放置的小矩形，那么前面的方法数为 f(n-1)。
     * •	如果最后一列使用两个竖着放置的小矩形，那么前面的方法数为 f(n-2)。
     * 因此，覆盖 2xn 大矩形的方法数为 f(n) = f(n-1) + f(n-2)。初始条件为 f(1) = 1，f(2) = 2。
     * <p>
     * 具体步骤如下：
     * 1.	定义一个数组 dp，dp[i] 表示覆盖 2xi 大矩形的方法数。
     * 2.	初始化 dp[1] = 1，dp[2] = 2。
     * 3.	从 i = 3 开始遍历到 n，根据状态转移方程 dp[i] = dp[i-1] + dp[i-2] 计算 dp[i] 的值。
     * 4.	返回 dp[n]，即覆盖 2xn 大矩形的方法数。
     */
    public int rectCover(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        CoverJZ solution = new CoverJZ();
        int n = 5;
        // 输出结果为 8
        System.out.println(solution.rectCover(n));
    }
}
