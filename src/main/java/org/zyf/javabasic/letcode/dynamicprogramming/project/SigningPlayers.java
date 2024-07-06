package org.zyf.javabasic.letcode.dynamicprogramming.project;

import org.zyf.javabasic.letcode.dynamicprogramming.project.base.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 实现的动态规划解决方案，用于解决签约棒球自由球员问题
 * @author: zhangyanfeng
 * @create: 2024-07-06 22:05
 **/
public class SigningPlayers {
    public static void main(String[] args) {
        // 示例数据
        int X = 5; // 总预算（以10万美元为单位）
        int N = 3; // 不同位置的自由球员数量
        int[] P = {2, 3, 2}; // 每个位置上的自由球员数量
        List<Player>[] players = new List[N];

        // 为每个位置添加示例数据
        players[0] = Arrays.asList(
                new Player("投手", 1, 3),
                new Player("投手", 2, 4)
        );
        players[1] = Arrays.asList(
                new Player("内野手", 1, 2),
                new Player("内野手", 3, 5),
                new Player("内野手", 1, 4)
        );
        players[2] = Arrays.asList(
                new Player("外野手", 2, 6),
                new Player("外野手", 1, 3)
        );

        // 动态规划解决方案
        int[][] dp = new int[N + 1][X + 1]; // dp数组，dp[i][j]表示前i个位置，预算不超过j时的最大总VORP值
        boolean[][] chosen = new boolean[N + 1][X + 1]; // 记录是否选择了该位置的球员

        // 填充dp数组
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= X; j++) {
                dp[i][j] = dp[i - 1][j]; // 不选择当前位置的任何球员

                // 尝试选择当前位置的每个球员
                for (Player player : players[i - 1]) {
                    if (player.getCost() <= j) {
                        int currentVORP = dp[i - 1][j - player.getCost()] + player.getVorp();
                        if (currentVORP > dp[i][j]) {
                            dp[i][j] = currentVORP;
                            chosen[i][j] = true; // 标记选择了该球员
                        }
                    }
                }
            }
        }

        // 找到选择的球员列表
        List<Player> selectedPlayers = new ArrayList<>();
        int remainingBudget = X;

        for (int i = N; i > 0; i--) {
            if (chosen[i][remainingBudget]) {
                for (Player player : players[i - 1]) {
                    if (player.getCost() <= remainingBudget
                            && dp[i][remainingBudget] == dp[i - 1][remainingBudget - player.getCost()] + player.getVorp()) {
                        selectedPlayers.add(player);
                        remainingBudget -= player.getCost();
                        break;
                    }
                }
            }
        }

        // 输出结果
        System.out.println("最大总VORP值: " + dp[N][X]);
        System.out.println("总签约费用: " + (X * 100000)); // 换算为美元
        System.out.println("签约球员名单:");
        for (Player player : selectedPlayers) {
            System.out.println("位置: " + player.getPosition() +
                    ", 签约费用: " + (player.getCost() * 100000) + "美元, VORP值: " + player.getVorp());
        }
    }
}
