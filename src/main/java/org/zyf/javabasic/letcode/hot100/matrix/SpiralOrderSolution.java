package org.zyf.javabasic.letcode.hot100.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 螺旋矩阵​
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:53
 **/
public class SpiralOrderSolution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 定义四个边界
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // 只要还有未处理的区域
        while (top <= bottom && left <= right) {
            // 从左到右遍历当前上边界
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++; // 上边界向下移动

            // 从上到下遍历当前右边界
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // 右边界向左移动

            // 确保当前行还有未处理的部分
            if (top <= bottom) {
                // 从右到左遍历当前下边界
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--; // 下边界向上移动
            }

            // 确保当前列还有未处理的部分
            if (left <= right) {
                // 从下到上遍历当前左边界
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // 左边界向右移动
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralOrderSolution solution = new SpiralOrderSolution();

        // 测试用例 1
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(solution.spiralOrder(matrix1)); // 输出: [1,2,3,6,9,8,7,4,5]

        // 测试用例 2
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(solution.spiralOrder(matrix2)); // 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
    }
}
