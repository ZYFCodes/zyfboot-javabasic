package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 请你来实现一个函数 atoi，使其能将字符串转换成整数。
 * 函数 atoi 的算法需要满足以下条件：
 * * 		读取字符串并丢弃无用的前导空格。
 * * 		检查第一个字符（假设该字符为 c）是否为正号 '+' 或负号 '-'，或者是否为数字（即 '0' - '9'）。
 * * 		解析字符串中的整数，直到不能继续读取为止。
 * * 解析得到的整数需要满足下列条件：
 * * 如果整数数值大于 INT_MAX，返回 INT_MAX。
 * * 如果整数数值小于 INT_MIN，返回 INT_MIN。
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 * 示例 2：
 * 输入：s = " -42"
 * 输出：-42
 * 解释：第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3：
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：解析出数字 4193 。
 * 示例 4：
 * 输入：s = "words and 987"
 * 输出：0
 * 解释：第一个非空字符是 'w'，但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5：
 * 输入：s = "-91283472332"
 * 输出：-2147483648
 * 解释：解析出数字 -2147483648 。
 * 注意：
 * * 本题中的空白字符只包括空格字符 ' ' 。
 * * 除前导空格或数字后的其余字符串外，请勿忽略任何其他字符。
 * @date 2021/1/27  23:53
 */
public class StringToInteger {

    /**
     * 最优解法是使用有限状态机来解决字符串转换整数的问题。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 有限状态机的思想是通过定义不同的状态来处理不同的字符。在字符串转换整数的问题中，我们可以将有限状态机定义为以下几个状态：
     * * 起始空格状态（START）
     * * 符号状态（SIGN）
     * * 数字状态（NUMBER）
     * * 结束状态（END）
     * 具体的最优解法步骤如下：
     * * 定义有限状态机，并初始化当前状态为起始空格状态（START）。
     * * 遍历字符串的每个字符，根据当前状态和当前字符进行状态转移，处理不同的情况。
     * * 如果遍历完成后仍处于符号状态（SIGN）或起始空格状态（START），说明没有有效数字，返回 0。
     * * 如果得到的结果大于 INT_MAX，返回 INT_MAX；如果得到的结果小于 INT_MIN，返回 INT_MIN。
     * 这种解法只需遍历一次字符串，通过有限状态机来解析整数并进行状态转移，从而得到转换后的整数。
     * 这种解法的空间复杂度是 O(1)，因为只使用了常数级别的额外空间。
     * 综上所述，使用有限状态机解决字符串转换整数问题是最优解法，具有高效和节省空间的特点。
     */
    public static int myAtoi(String str) {
        // 定义有限状态机
        // 起始空格状态
        final int START = 0;
        // 符号状态
        final int SIGN = 1;
        // 数字状态
        final int NUMBER = 2;
        // 结束状态
        final int END = 3;

        // 初始化当前状态为起始空格状态
        int state = START;
        // 初始化符号，默认为正号
        int sign = 1;
        // 初始化结果
        int result = 0;

        for (char c : str.toCharArray()) {
            if (state == START) {
                // 处理起始空格状态
                if (c == ' ') {
                    continue;
                } else if (c == '+' || c == '-') {
                    // 如果是符号字符，则切换到符号状态
                    state = SIGN;
                    sign = (c == '+') ? 1 : -1;
                } else if (Character.isDigit(c)) {
                    // 如果是数字字符，则切换到数字状态
                    state = NUMBER;
                    result = c - '0';
                } else {
                    // 其他字符，直接返回 0
                    return 0;
                }
            } else if (state == SIGN) {
                // 处理符号状态
                if (Character.isDigit(c)) {
                    // 如果是数字字符，则切换到数字状态
                    state = NUMBER;
                    result = c - '0';
                } else {
                    // 其他字符，直接返回 0
                    return 0;
                }
            } else if (state == NUMBER) {
                // 处理数字状态
                if (Character.isDigit(c)) {
                    // 如果是数字字符，则继续更新结果
                    int digit = c - '0';
                    // 判断是否溢出
                    if (result > (Integer.MAX_VALUE - digit) / 10) {
                        return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                    result = result * 10 + digit;
                } else {
                    // 遇到非数字字符，退出循环
                    break;
                }
            }
        }

        // 返回最终结果
        return sign * result;
    }

    public static void main(String[] args) {
        String str1 = "42";
        String str2 = "   -42";
        String str3 = "4193 with words";
        String str4 = "words and 987";
        String str5 = "-91283472332";

        // 输出结果：String to Integer for "42": 42
        System.out.println("String to Integer for \"" + str1 + "\": " + myAtoi(str1));
        // 输出结果：String to Integer for "   -42": -42
        System.out.println("String to Integer for \"" + str2 + "\": " + myAtoi(str2));
        // 输出结果：String to Integer for "4193 with words": 4193
        System.out.println("String to Integer for \"" + str3 + "\": " + myAtoi(str3));
        // 输出结果：String to Integer for "words and 987": 0
        System.out.println("String to Integer for \"" + str4 + "\": " + myAtoi(str4));
        // 输出结果：String to Integer for "-91283472332": -2147483648
        System.out.println("String to Integer for \"" + str5 + "\": " + myAtoi(str5));
    }
}
