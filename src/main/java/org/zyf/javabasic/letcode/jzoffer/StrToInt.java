package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请你写一个函数 StrToInt，实现将字符串转换成整数的功能。
 * 字符串可以包含空格字符，转换结果需要去掉前导空格，并且符号位只能是正负号（’+’ 或 ‘-’）。
 * 如果字符串中包含非数字字符或者空字符串，则返回 0。
 * @date 2023/6/5  23:03
 */
public class StrToInt {
    /**
     * 要将字符串转换成整数，可以按照以下步骤进行：
     * 	1.	去掉字符串的前导空格。
     * 	2.	判断字符串的符号位，记录正负号。
     * 	3.	从字符串的第一个非空字符开始，遍历每个字符：
     * 	•	如果当前字符是数字字符，则将其转换成数字，并累加到结果中。
     * 	•	如果当前字符不是数字字符，则停止遍历。
     * 	4.	根据符号位，给结果加上正负号。
     * 	5.	如果结果超出整数的范围（即超出 Integer.MIN_VALUE 和 Integer.MAX_VALUE），则返回边界值。
     * 	6.	如果字符串中没有有效的数字字符，则返回 0。
     */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        // 去掉前导空格
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        int index = 0;
        int sign = 1;
        int result = 0;

        // 判断符号位
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            if (str.charAt(index) == '-') {
                sign = -1;
            }
            index++;
        }

        // 遍历字符数组
        while (index < str.length()) {
            char c = str.charAt(index);
            if (c < '0' || c > '9') {
                break;
            }

            // 计算结果
            int digit = c - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;

            index++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        StrToInt solution = new StrToInt();

        String str = "   -12345";
        int result = solution.strToInt(str);

        System.out.println("转换后的整数：" + result);
    }

}
