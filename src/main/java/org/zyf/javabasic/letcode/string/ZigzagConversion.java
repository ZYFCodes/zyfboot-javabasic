package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 将一个字符串按照指定的行数进行 Z 字形排列后，逐行读取得到新的字符串。
 * 例如，对于字符串 "PAYPALISHIRING" 和行数 3，按照 Z 字形排列得到：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 逐行读取得到新的字符串为 "PAHNAPLSIIGYIR"。
 * @date 2021/3/9  23:28
 */
public class ZigzagConversion {

    /**
     * 最优解法是使用模拟 Z 字形排列的方法来将一个字符串按照指定的行数进行 Z 字形变换。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 创建一个长度为行数的字符串数组，用于保存每一行的字符。
     * * 定义一个变量 curRow，表示当前行数，初始值为 0，表示从第一行开始。
     * * 定义一个变量 goingDown，表示当前方向，初始值为 false，表示当前方向是向上。
     * * 遍历原始字符串的每个字符，按照 Z 字形排列的顺序将字符放入对应的行，并根据当前方向的变化更新 curRow 和 goingDown。
     * * 最后，将每一行的字符按顺序拼接成新的字符串。
     * 这种解法的核心思想是模拟 Z 字形排列的过程，按顺序将字符放入对应的行，并根据当前方向的变化更新行数 curRow 和方向 goingDown。
     * 由于这个解法只需要遍历一次原始字符串，并且在遍历过程中只进行简单的判断和字符放入操作，因此时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 另外，除了存储原始字符串和结果字符串外，这个解法只需要使用常数大小的额外空间，因此空间复杂度是 O(1)。
     * 综上所述，使用模拟 Z 字形排列的方法来将一个字符串按照指定的行数进行 Z 字形变换是最优解法，具有高效的时间复杂度和节省空间的特点。
     */
    public static String convert(String s, int numRows) {
        // 特殊情况处理
        if (numRows == 1) {
            return s;
        }

        // 创建长度为 numRows 的字符串数组，用于保存每一行的字符
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // 当前行数
        int curRow = 0;
        // 当前方向
        boolean goingDown = false;

        // 遍历原始字符串的每个字符
        for (char c : s.toCharArray()) {
            // 将当前字符放入对应的行
            rows[curRow].append(c);

            // 更新当前行数和方向
            if (curRow == 0 || curRow == numRows - 1) {
                // 到达第一行或最后一行时改变方向
                goingDown = !goingDown;
            }
            // 根据当前方向更新当前行数
            curRow += goingDown ? 1 : -1;
        }

        // 将每一行的字符按顺序拼接成新的字符串
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String converted = convert(s, numRows);
        // 输出结果：Zigzag Conversion: PAHNAPLSIIGYIR
        System.out.println("Zigzag Conversion: " + converted);
    }
}
