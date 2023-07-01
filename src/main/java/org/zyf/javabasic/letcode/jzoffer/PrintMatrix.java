package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;

/**
 * @author yanfengzhang
 * @description 顺时针打印矩阵。
 * 给定一个二维矩阵，按照从外向里以顺时针的顺序依次打印出每一个元素。
 * @date 2023/6/2  23:57
 */
public class PrintMatrix {
    /**
     * 最优的解题思路是模拟顺时针打印的过程。具体步骤如下：
     * 	1.	定义四个变量top、bottom、left、right分别表示当前打印范围的上边界、下边界、左边界和右边界。
     * 	2.	进行循环，每次循环打印一圈，即从左到右、从上到下、从右到左、从下到上四个方向。
     * 	3.	在每个方向上，按照当前边界的范围依次打印元素。
     * 	4.	每次打印完一个方向后，更新边界，继续下一次循环打印下一圈。
     * 	5.	循环终止条件是打印的元素数量达到矩阵的总元素数量。
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        // 矩阵的行数
        int rows = matrix.length;
        // 矩阵的列数
        int cols = matrix[0].length;
        // 上边界
        int top = 0;
        // 下边界
        int bottom = rows - 1;
        // 左边界
        int left = 0;
        // 右边界
        int right = cols - 1;

        while (top <= bottom && left <= right) {
            // 从左到右打印一行
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // 上边界下移
            top++;

            // 从上到下打印一列
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            // 右边界左移
            right--;

            // 从右到左打印一行
            if (top <= bottom) {
                // 避免重复打印
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                // 下边界上移
                bottom--;
            }

            // 从下到上打印一列
            if (left <= right) {
                // 避免重复打印
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                // 左边界右移
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PrintMatrix solution = new PrintMatrix();

        // 测试输入矩阵
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // 调用方法打印矩阵
        ArrayList<Integer> result = solution.printMatrix(matrix);

        // 输出打印结果 1 2 3 6 9 8 7 4 5
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
