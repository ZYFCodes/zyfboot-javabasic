package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个正整数n，要求构造一个n x n的矩阵，并按照从1开始顺时针螺旋填充矩阵。
 * 例如，当n=3时，构造的螺旋矩阵如下：
 * 1 2 3
 * 8 9 4
 * 7 6 5
 * @date 2023/7/18  23:12
 */
public class SpiralMatrix {

    /**
     * 为了解决这个问题，可以使用模拟方法。
     * 从矩阵的左上角开始，按照顺时针方向依次填充数字，同时注意矩阵边界的处理。
     * 通常可以使用四个变量来表示矩阵的上下左右边界，然后按照螺旋的顺序不断填充数字。
     * <p>
     * 螺旋填充矩阵问题可以使用模拟的方法来解决。最优解法的时间复杂度为O(n^2)，其中n是矩阵的边长。
     * 具体的最优解法步骤如下：
     * 1.	初始化一个n x n的矩阵，并用一个变量num来表示当前要填充的数字，初始值为1。
     * 2.	设置四个变量top、bottom、left和right来表示当前填充的边界范围。初始时，top=0，bottom=n-1，left=0，right=n-1。
     * 3.	进行循环，从左到右、从上到下、从右到左、从下到上四个方向依次填充数字，直到所有的位置都填充完毕。在每个方向上，每填充一个数字，num增加1。
     * 4.	每填充完一个方向后，需要更新边界范围：对于从左到右和从右到左的方向，更新top和bottom；对于从上到下和从下到上的方向，更新left和right。
     * 5.	继续下一个方向的填充，直到所有位置都填充完毕。
     * 最优解法的关键点在于通过四个边界变量来控制填充的方向和范围，这样可以在一个循环内完成所有位置的填充。
     * 因为每个位置只被填充一次，所以时间复杂度为O(n^2)。
     */
    public static int[][] generateMatrix(int n) {
        // 创建一个 n x n 的矩阵
        int[][] matrix = new int[n][n];
        // 用于填充的数字，初始值为 1
        int num = 1;
        // 初始化边界
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (num <= n * n) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            // 更新上边界
            top++;

            // 从上到下
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            // 更新右边界
            right--;

            // 从右到左
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            // 更新下边界
            bottom--;

            // 从下到上
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            // 更新左边界
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        // 验证的矩阵边长为 4
        int n = 4;
        int[][] result = generateMatrix(n);

        // 打印生成的螺旋填充矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
