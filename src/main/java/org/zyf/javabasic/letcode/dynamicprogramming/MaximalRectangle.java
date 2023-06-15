package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1:
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 * @date 2023/5/21  23:37
 */
public class MaximalRectangle {
    /**
     * 最优解法采用动态规划和柱状图的思想来解决最大矩形问题。
     * 将问题转化为柱状图中的最大矩形问题，可以通过遍历每一行，将每一行作为底部，统计每一行上方连续的1的个数，然后计算以当前行为底部的最大矩形面积。
     * 假设我们有一个一维数组 heights，其中的每个元素表示该列上方连续的1的个数。我们需要计算以当前行为底部的最大矩形面积。
     * 首先，初始化一个同样大小的一维数组 left，其中的元素表示当前位置左边界的索引。
     * 对于某个位置 (i, j)，如果 matrix[i][j] 是1，则 left[j] 的值为 max(left[j], curLeft)，
     * 其中 curLeft 是当前行中从左边界开始连续的1的起始位置。
     * 然后，初始化一个同样大小的一维数组 right，其中的元素表示当前位置右边界的索引。
     * 对于某个位置 (i, j)，如果 matrix[i][j] 是1，则 right[j] 的值为 min(right[j], curRight)，
     * 其中 curRight 是当前行中从右边界开始连续的1的结束位置。
     * 接下来，遍历每一行，对于每个位置 (i, j)，
     * 计算以当前位置为底部的矩形的面积为 (right[j] - left[j]) * heights[j]，并更新最大矩形面积。
     * 最后，返回最大矩形面积即可。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[] heights = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        // 初始化 right 数组为 n
        Arrays.fill(right, n);

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;

            // 更新 heights 数组和 left 数组
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    heights[j] = 0;
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            // 更新 right 数组和计算最大矩形面积
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                    maxArea = Math.max(maxArea, (right[j] - left[j]) * heights[j]);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int maxArea1 = new MaximalRectangle().maximalRectangle(matrix1);
        // 输出：最大矩形面积为：6
        System.out.println("最大矩形面积为：" + maxArea1);

        char[][] matrix2 = {
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'}
        };
        int maxArea2 = new MaximalRectangle().maximalRectangle(matrix2);
        // 输出：最大矩形面积为：12
        System.out.println("最大矩形面积为：" + maxArea2);

        char[][] matrix3 = {
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'}
        };
        int maxArea3 = new MaximalRectangle().maximalRectangle(matrix3);
        // 输出：最大矩形面积为：0
        System.out.println("最大矩形面积为：" + maxArea3);
    }

}
