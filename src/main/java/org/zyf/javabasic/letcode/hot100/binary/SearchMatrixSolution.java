package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 搜索二维矩阵（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:59
 **/
public class SearchMatrixSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SearchMatrixSolution solution = new SearchMatrixSolution();

        // 示例 1
        int[][] matrix1 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target1 = 3;
        System.out.println(solution.searchMatrix(matrix1, target1)); // 输出: true

        // 示例 2
        int[][] matrix2 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target2 = 13;
        System.out.println(solution.searchMatrix(matrix2, target2)); // 输出: false
    }
}
