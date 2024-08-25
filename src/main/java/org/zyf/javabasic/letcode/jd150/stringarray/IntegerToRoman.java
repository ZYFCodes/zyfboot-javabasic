package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 整数转罗马数字
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:05
 **/
public class IntegerToRoman {
    public String intToRoman(int num) {
        // 罗马数字符号和对应的值
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        // 遍历所有符号值
        for (int i = 0; i < values.length; i++) {
            // 每次找到最大值，减少num
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]); // 添加符号到结果
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman converter = new IntegerToRoman();

        // 测试用例
        System.out.println(converter.intToRoman(3749)); // 输出: MMMDCCXLIX
        System.out.println(converter.intToRoman(58));   // 输出: LVIII
        System.out.println(converter.intToRoman(1994)); // 输出: MCMXCIV
    }
}
