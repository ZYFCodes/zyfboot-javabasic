package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],…，k[m]。
 * 请问k[0]xk[1]x…xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，剪成2、3、3三段，得到的最大乘积是18。
 * @date 2023/6/6  22:20
 */
public class CutRope {
    /**
     * 解题步骤：
     * 	1.	创建一个长度为n+1的数组dp，并初始化dp[0]和dp[1]为0，dp[2]为1。
     * 	2.	使用循环从3到n，计算每个dp[i]的最大乘积：
     * 	•	内部使用循环从1到i/2，计算剪一刀后的两段的最大乘积，并选择最大值。
     * 	•	将该最大值与剪一刀后的两段乘积进行比较，取较大值作为dp[i]的最大乘积。
     * 	3.	返回dp[n]作为结果。
     *
     * 这样，我们就可以得到长度为n的绳子剪成若干段后的最大乘积。
     */
    public int cutRope(int target) {
        if (target <= 1) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }

        // 创建长度为target+1的数组，用于存储最大乘积
        int[] dp = new int[target + 1];
        // 长度为0的绳子无法剪断，最大乘积为0
        dp[0] = 0;
        // 长度为1的绳子无法剪断，最大乘积为0
        dp[1] = 1;
        // 长度为2的绳子只能剪成1*1，最大乘积为1
        dp[2] = 2;
        // 长度为3的绳子只能剪成1*2，最大乘积为2
        dp[3] = 3;

        // 循环计算每个长度的最大乘积
        for (int i = 4; i <= target; i++) {
            // 内部循环计算剪一刀后的两段的最大乘积，并选择最大值
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        CutRope solution = new CutRope();
        // 验证案例：长度为8的绳子可以剪成1*7、2*6、3*5、4*4，最大乘积为18
        int target = 8;
        int result = solution.cutRope(target);
        System.out.println("Max product for rope of length " + target + ": " + result);
    }

}
