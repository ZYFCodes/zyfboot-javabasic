package org.zyf.javabasic.letcode.dynamicprogramming.project;

/**
 * @program: zyfboot-javabasic
 * @description: 动态规划算法来解决最优投资策略问题
 * @author: zhangyanfeng
 * @create: 2021-09-25 23:00
 **/
public class InvestmentStrategy {
    // 计算最大回报的方法
    public static double maxReturn(int years, int products, double initialInvestment, double[][] r, double f1, double f2) {
        double[][] dp = new double[years + 1][products];

        // 初始化：第一年各产品的投资回报
        for (int j = 0; j < products; j++) {
            dp[0][j] = initialInvestment * r[j][0];
        }

        // 状态转移
        for (int i = 1; i <= years; i++) {
            for (int j = 0; j < products; j++) {
                // 假设继续投资当前产品
                dp[i][j] = dp[i-1][j] * r[j][i] - f1;
                // 考虑转移到其他产品
                for (int k = 0; k < products; k++) {
                    if (k != j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k] * r[j][i] - f2);
                    }
                }
            }
        }

        // 获取最终最大值
        double maxReturn = 0;
        for (int j = 0; j < products; j++) {
            maxReturn = Math.max(maxReturn, dp[years][j]);
        }

        return maxReturn;
    }

    public static void main(String[] args) {
        int years = 10;
        int products = 3;
        double initialInvestment = 10000;
        double[][] r = {
                {1.1, 1.2, 1.3, 1.1, 1.3, 1.2, 1.1, 1.3, 1.2, 1.1, 1.3},
                {1.3, 1.1, 1.2, 1.3, 1.1, 1.2, 1.3, 1.1, 1.2, 1.3, 1.1},
                {1.2, 1.3, 1.1, 1.2, 1.3, 1.1, 1.2, 1.3, 1.1, 1.2, 1.3}
        };
        double f1 = 50;
        double f2 = 100;

        System.out.println("最大回报: " + maxReturn(years, products, initialInvestment, r, f1, f2));
    }
}
