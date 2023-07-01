package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串”+100”、“5e2”、”-123”、“3.1416”、”-1E-16”都表示数值，
 * 但”12e”、“1a3.14”、“1.2.3”、”+-5”以及”12e+5.4”都不是。
 * @date 2023/6/4  23:47
 */
public class StriIsNumeric {
    /**
     * 根据题目要求，我们需要判断给定的字符串是否能够表示数值。
     * 为了实现这个功能，我们可以使用有限状态自动机（Finite State Machine）的思想。
     * 我们需要定义多个状态，并根据当前字符和状态转移条件来更新状态，最终判断是否能够到达接受状态。
     *
     * 具体的状态定义和转移条件如下：
     * 	1.	定义以下9种状态：起始空格、符号位、整数部分、小数点、小数部分、字符e、指数符号、指数整数部分、末尾空格。
     * 	2.	定义以下4种字符类型：数字、正负号、小数点、字符e。
     * 	3.	根据字符类型和当前状态，定义状态转移规则，更新当前状态。
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }

        // 当前字符索引
        int index = 0;
        // 字符串长度
        int len = str.length;

        // 跳过起始的空格
        while (index < len && str[index] == ' ') {
            index++;
        }

        // 判断符号位
        if (index < len && (str[index] == '+' || str[index] == '-')) {
            index++;
        }

        // 判断整数部分
        boolean isNumeric = scanDigits(str, index, len);

        // 判断小数部分
        if (index < len && str[index] == '.')
        {
            index++;
            // 小数部分可以没有整数部分，因此使用 || 进行判断
            isNumeric = scanDigits(str, index, len) || isNumeric;
        }

        // 判断指数部分
        if (index < len && (str[index] == 'e' || str[index] == 'E'))
        {
            index++;
            // 指数部分必须有整数部分，因此使用 && 进行判断
            isNumeric = isNumeric && scanExponent(str, index, len);
        }

        // 跳过末尾的空格
        while (index < len && str[index] == ' ') {
            index++;
        }

        // 如果所有字符都被处理并且符合数值的规则，则返回true
        return isNumeric && index == len;
    }

    // 判断是否是数字
    private boolean scanDigits(char[] str, int index, int len) {
        while (index < len && str[index] >= '0' && str[index] <= '9') {
            index++;
        }

        return index > 0 && index <= len;
    }

    // 判断指数部分
    private boolean scanExponent(char[] str, int index, int len) {
        // 判断指数部分的符号位
        if (index < len && (str[index] == '+' || str[index] == '-')) {
            index++;
        }

        // 判断指数部分的整数部分
        boolean hasDigits = scanDigits(str, index, len);

        // 如果指数部分存在整数部分，则返回true
        return hasDigits && index == len;
    }

    public static void main(String[] args) {
        StriIsNumeric solution = new StriIsNumeric();

        // 验证示例测试用例
        char[] str1 = {'+', '1', '0', '0'};
        // 应输出true
        System.out.println(solution.isNumeric(str1));

        char[] str2 = {'5', 'e', '2'};
        // 应输出true
        System.out.println(solution.isNumeric(str2));

        char[] str3 = {'-', '1', '2', '3'};
        // 应输出true
        System.out.println(solution.isNumeric(str3));

        char[] str4 = {'3', '.', '1', '4', '1', '6'};
        // 应输出true
        System.out.println(solution.isNumeric(str4));

        char[] str5 = {'-', '1', 'E', '-', '1', '6'};
        // 应输出true
        System.out.println(solution.isNumeric(str5));
    }
}
