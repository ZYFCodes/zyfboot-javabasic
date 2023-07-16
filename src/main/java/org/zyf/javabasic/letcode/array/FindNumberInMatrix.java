package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个行递增、列递增的二维数组（矩阵），判断是否可以找到某个数。
 * @date 2023/7/11  23:28
 */
public class FindNumberInMatrix {
    /**
     * 判断在行递增、列递增的二维数组中是否可以找到某个数
     *
     * @param matrix 二维数组
     * @param target 目标数
     * @return 是否找到目标数
     */
    public static boolean findNumber(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // 从第一行开始
        int row = 0;
        // 从最后一列开始
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                // 找到目标数，返回true
                return true;
            } else if (matrix[row][col] > target) {
                // 当前元素大于目标数，向左移动一列
                col--;
            } else {
                // 当前元素小于目标数，向下移动一行
                row++;
            }
        }

        // 遍历完整个矩阵仍未找到目标数，返回false
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int target = 5;

        boolean found = findNumber(matrix, target);
        System.out.println("Number " + target + " found: " + found);
    }

}
