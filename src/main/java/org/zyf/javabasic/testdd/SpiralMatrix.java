package org.zyf.javabasic.testdd;

/**
 * @author yanfengzhang
 * @description 给定一个二维数组m*n，把1到m*n按照顺时针方向往里写入，最后打印这个二维数组。
 * 1 2 3
 * 8 9 4
 * 7 6 5
 * @date 2023/8/1  20:44
 */
public class SpiralMatrix {

    public static int[][] generateMatrix(int n) {
        //创建数组
        int[][] matrix = new int[n][n];
        //用于填充数字
        int num = 1;
        //初始化边界
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (num <= n * n) {
            //从左到右
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            //更新上边界
            top++;

            //从上到下
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            //更新右边界
            right--;

            //从右到左
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            //更新下边界
            bottom--;

            //从下到上
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            //跟新左边界
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] res = generateMatrix(n);

        //dayin
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(res[i][j]);
            }
        }

    }
}
