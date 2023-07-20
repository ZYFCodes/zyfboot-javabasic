package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 要求将26个字母按照顺时针螺旋填充到一个5x5的矩阵中。请见下方的矩阵：
 * A  B  C  D  E
 * V  W  X  Y  F
 * U  T  S  R  G
 * T  R  Q  P  H
 * S  Q  P  O  N
 * 其中，字母按照字母表的顺序从A开始，依次填充到矩阵中，直到Z。
 * @date 2023/7/18  23:38
 */
public class SpiralFillAlphabet {

    /**
     * 对于将26个字母按照顺时针螺旋填充到一个5x5的矩阵中，最优解法是按照以下步骤进行：
     * 	1.	初始化一个5x5的空矩阵，并设定一个指针，初始位置在矩阵的左上角(0,0)。
     * 	2.	遍历字母表中的每个字母，从A到Z。
     * 	3.	将当前字母填充到指针所指向的位置，并将指针向前移动一步。
     * 	4.	检查指针移动后的位置是否已经填充过字母或者是否越界。如果是，则按照顺时针方向旋转指针，并重新移动。
     * 	5.	重复步骤3和步骤4，直到所有字母都被填充到矩阵中。
     * 这样，按照最优解法，可以得到一个符合题目要求的5x5矩阵，其中字母按照字母表的顺序从A开始，依次填充到Z。
     * 由于5x5的矩阵有限，只有25个位置，所以最后一个字母Z会填充在矩阵的最后一个可用位置。
     * 需要注意的是，最优解法需要仔细处理边界条件和指针的移动，确保每个字母都被正确地填充到矩阵中，而且不会出现死循环或者遗漏字母的情况。
     */
    public static char[][] fillSpiralMatrix() {
        char[][] matrix = new char[5][5];
        char currentLetter = 'A';
        int row = 0;
        int col = 0;
        int rowDirection = 0;
        int colDirection = 1;

        for (int i = 0; i < 26; i++) {
            // 步骤1：在当前位置填充当前字母到矩阵中。
            matrix[row][col] = currentLetter;
            // 步骤2：移动到下一个字母。
            currentLetter++;

            // 步骤3：检查下一个位置是否有效（未填充且在边界内）。
            int nextRow = row + rowDirection;
            int nextCol = col + colDirection;

            if (nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5 || matrix[nextRow][nextCol] != 0) {
                // 如果下一个位置无效，则顺时针改变方向。
                if (rowDirection == 0 && colDirection == 1) {
                    rowDirection = 1;
                    colDirection = 0;
                } else if (rowDirection == 1 && colDirection == 0) {
                    rowDirection = 0;
                    colDirection = -1;
                } else if (rowDirection == 0 && colDirection == -1) {
                    rowDirection = -1;
                    colDirection = 0;
                } else if (rowDirection == -1 && colDirection == 0) {
                    rowDirection = 0;
                    colDirection = 1;
                }
            }

            // 步骤4：移动到下一个有效位置。
            row += rowDirection;
            col += colDirection;
        }

        return matrix;
    }

    public static void main(String[] args) {
        char[][] matrix = fillSpiralMatrix();
        printMatrix(matrix);
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}
