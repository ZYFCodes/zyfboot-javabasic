package org.zyf.javabasic.letcode.hash;

/**
 * @author yanfengzhang
 * @description 判断一个 9x9 的数独是否有效。
 * @date 2023/4/9  18:11
 */
public class ValidSudoku {

    /**
     * 具体实现思路如下：
     * <p>
     * 分别使用三个二维布尔数组记录每个数字在每行、每列、每个3x3子数独中是否出现过。数组初始化为false。
     * 遍历数独，如果当前位置是数字，则在对应的行、列、子数独的布尔数组中标记该数字已出现。
     * 每次标记前先检查当前数字是否已经在对应的行、列、子数独中出现过，如果出现过则说明不是有效的数独。
     * 遍历结束后，如果没有出现过重复数字，则说明是有效的数独。
     * <p>
     * 该算法时间复杂度为O(81)，空间复杂度为O(27)，具有较高的时间和空间效率。
     */
    public boolean isValidSudoku(char[][] board) {
        /*行*/
        boolean[][] rows = new boolean[9][9];
        /*列*/
        boolean[][] cols = new boolean[9][9];
        /*子数独*/
        boolean[][] boxes = new boolean[9][9];

        /*遍历数独中的每个数字*/
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    /*当前数字对应的索引*/
                    int num = board[i][j] - '1';
                    /*当前数字所在的子数独编号*/
                    int boxIndex = (i / 3) * 3 + j / 3;

                    /*若该数字在对应的行、列或子数独中已经出现过，则说明不是有效的数独*/
                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false;
                    }

                    /*标记该数字在对应的行、列和子数独中已经出现过*/
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }

        /*遍历结束没有发现重复数字，则说明是有效的数独，返回true*/
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValid = new ValidSudoku().isValidSudoku(board);
        System.out.println(isValid);
    }

}
