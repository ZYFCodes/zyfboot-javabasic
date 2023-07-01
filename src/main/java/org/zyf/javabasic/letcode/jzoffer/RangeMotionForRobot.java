package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 地上有一个m行n列的方格，一个机器人从坐标(0, 0)的格子开始移动，
 * 它每次可以向上、下、左、右移动一格，但不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格(35, 37)，因为3+5+3+7=18。
 * 但它不能进入方格(35, 38)，因为3+5+3+8=19。请问机器人能够到达多少个格子？
 * @date 2023/6/6  22:16
 */
public class RangeMotionForRobot {
    /**
     * 该问题可以使用递归或深度优先搜索来解决。具体步骤如下：
     * 1.	定义一个辅助函数movingCountCore，用于计算机器人从坐标(i, j)开始能够到达的格子数量。
     * 2.	在movingCountCore函数中，首先判断当前坐标是否越界或已经访问过，若是，则返回0。
     * 3.	计算当前坐标(i, j)的数位之和，若大于给定的阈值k，则返回0。
     * 4.	将当前坐标标记为已访问。
     * 5.	递归计算机器人能够到达的上、下、左、右四个方向的格子数量，并将其累加起来。
     * 6.	返回累加结果加上当前格子本身，表示从当前格子出发能够到达的格子总数。
     * 7.	在主函数中调用movingCountCore函数，并返回最终结果。
     * <p>
     * 这样就能够得到机器人能够到达的格子数量。
     */
    public int movingCount(int threshold, int rows, int cols) {
        // 创建一个二维数组用于记录格子的访问状态，初始值为false表示未访问过
        boolean[][] visited = new boolean[rows][cols];
        // 调用辅助函数进行递归计算
        return movingCountCore(threshold, 0, 0, rows, cols, visited);
    }

    private int movingCountCore(int threshold, int i, int j, int rows, int cols, boolean[][] visited) {
        // 判断坐标是否越界或已经访问过
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j]) {
            return 0;
        }

        // 计算当前坐标的数位之和
        int sum = getDigitSum(i) + getDigitSum(j);

        // 判断数位之和是否大于阈值
        if (sum > threshold) {
            return 0;
        }

        // 标记当前坐标为已访问
        visited[i][j] = true;

        // 递归计算上、下、左、右四个方向的格子数量，并累加结果
        int count = 1 + movingCountCore(threshold, i - 1, j, rows, cols, visited)
                + movingCountCore(threshold, i + 1, j, rows, cols, visited)
                + movingCountCore(threshold, i, j - 1, rows, cols, visited)
                + movingCountCore(threshold, i, j + 1, rows, cols, visited);

        return count;
    }

    // 计算数位之和的辅助函数
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        RangeMotionForRobot solution = new RangeMotionForRobot();

        // 测试样例
        int threshold = 5;
        int rows = 10;
        int cols = 10;
        int count = solution.movingCount(threshold, rows, cols);
        System.out.println("能够到达的格子数量: " + count);
    }
}
