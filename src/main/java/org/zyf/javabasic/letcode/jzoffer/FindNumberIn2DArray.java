package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @date 2023/6/1  22:53
 */
public class FindNumberIn2DArray {
    /**
     * 可以从二维数组的右上角（或左下角）开始查找，根据目标值与当前元素的大小关系，逐行缩小查找范围。
     * 以右上角为例，假设目标值为target，初始时，将指针定位在右上角的元素arr[row][col]：
     * •	若arr[row][col]等于target，说明找到目标值，返回true；
     * •	若arr[row][col]大于target，由于当前元素所在列的下方元素都大于当前元素，可以排除当前列，将指针左移一列，即col减1；
     * •	若arr[row][col]小于target，由于当前元素所在行的左侧元素都小于当前元素，可以排除当前行，将指针下移一行，即row加1。
     * 重复上述步骤，直到找到目标值或超出二维数组的范围。
     * 该解法的时间复杂度为O(m+n)，其中m为二维数组的行数，n为二维数组的列数。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            // 矩阵为空，返回false
            return false;
        }

        // 矩阵的行数
        int rows = matrix.length;
        // 矩阵的列数
        int cols = matrix[0].length;

        int row = 0;
        // 指针初始位置为矩阵的右上角元素
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                // 找到目标值，返回true
                return true;
            } else if (matrix[row][col] > target) {
                // 当前元素大于目标值，指针左移一列
                col--;
            } else {
                // 当前元素小于目标值，指针下移一行
                row++;
            }
        }

        // 遍历完矩阵仍未找到目标值，返回false
        return false;
    }

    public static void main(String[] args) {
        FindNumberIn2DArray solution = new FindNumberIn2DArray();
        // 测试输入二维数组
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        // 测试目标值
        int target = 9;
        // 调用方法查找目标值
        boolean result = solution.findNumberIn2DArray(matrix, target);
        // 输出结果
        System.out.println("是否找到目标值: " + result);
    }

}
