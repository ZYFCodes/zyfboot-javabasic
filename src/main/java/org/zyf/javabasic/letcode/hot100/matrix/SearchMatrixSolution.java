package org.zyf.javabasic.letcode.hot100.matrix;

/**
 * @program: zyfboot-javabasic
 * @description: 搜索二维矩阵 II
 * @author: zhangyanfeng
 * @create: 2024-08-21 23:31
 **/
public class SearchMatrixSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 从矩阵的右上角开始
        int row = 0;
        int col = n - 1;

        // 搜索矩阵
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true; // 找到目标值
            } else if (matrix[row][col] > target) {
                col--; // 向左移动
            } else {
                row++; // 向下移动
            }
        }

        // 目标值不在矩阵中
        return false;
    }

    public static void main(String[] args) {
        SearchMatrixSolution solution = new SearchMatrixSolution();

        // Test Case 1
        int[][] matrix1 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        System.out.println(solution.searchMatrix(matrix1, target1)); // Expected: true

        // Test Case 2
        int[][] matrix2 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target2 = 20;
        System.out.println(solution.searchMatrix(matrix2, target2)); // Expected: false

        // Test Case 3
        int[][] matrix3 = {
                {1, 2},
                {3, 4}
        };
        int target3 = 3;
        System.out.println(solution.searchMatrix(matrix3, target3)); // Expected: true

        // Test Case 4
        int[][] matrix4 = {
                {1}
        };
        int target4 = 0;
        System.out.println(solution.searchMatrix(matrix4, target4)); // Expected: false
    }
}
