package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * <p>
 * 示例 1：输入：columnNumber = 1.       输出："A"
 * 示例 2：输入：columnNumber = 28     输出："AB"
 * 示例 3：输入：columnNumber = 701.   输出："ZY"
 * 示例 4：输入：columnNumber = 2147483647     输出："FXSHRXW"
 * <p>
 * 提示：
 * - 1 <= columnNumber <= 2^31 - 1
 * @date 2023/7/9  23:07
 */
public class ExcelSheetColumnTitle {

    /**
     * 最优解法可以通过进制转换来实现Excel表列名称的计算。
     * 1. 从给定的正整数开始，不断地将其除以 26，并将余数转换为对应的字符。
     * 2. 将余数对应的字符拼接到结果字符串的最前面。
     * 3. 更新原始的正整数为商，然后继续进行上述步骤，直到正整数变为0为止。
     * 例如，对于 28，首先 28 / 26 = 1 余 2，对应的字符为 B，所以将 B 拼接到结果字符串的最前面。
     * 然后更新正整数为 1，继续进行计算，此时 1 / 26 = 0 余 1，对应的字符为 A，将 A 拼接到结果字符串的最前面。最终得到结果 "AB"。
     * 该算法的时间复杂度为 O(log(columnNumber))，其中 columnNumber 是给定的正整数。
     * 因为在每次计算时，正整数都会减少一半，最多需要进行 log(columnNumber) 次计算。
     * 而空间复杂度为 O(log(columnNumber))，需要额外的字符串来保存结果。
     */
    public static String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            // 余数，减1是因为字符从 A 开始计数
            int remainder = (columnNumber - 1) % 26;
            // 将余数转换为对应的字符
            char ch = (char) ('A' + remainder);
            // 将字符拼接到结果字符串的最前面
            result.insert(0, ch);
            // 更新原始的正整数为商
            columnNumber = (columnNumber - 1) / 26;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int columnNumber1 = 1;
        int columnNumber2 = 28;
        int columnNumber3 = 701;
        int columnNumber4 = 2147483647;

        System.out.println("Input: " + columnNumber1 + " Output: " + convertToTitle(columnNumber1));
        System.out.println("Input: " + columnNumber2 + " Output: " + convertToTitle(columnNumber2));
        System.out.println("Input: " + columnNumber3 + " Output: " + convertToTitle(columnNumber3));
        System.out.println("Input: " + columnNumber4 + " Output: " + convertToTitle(columnNumber4));
    }
}
