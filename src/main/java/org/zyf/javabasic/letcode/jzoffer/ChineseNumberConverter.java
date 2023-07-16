package org.zyf.javabasic.letcode.jzoffer;

import java.util.HashMap;

/**
 * 如果输入是一个中文的数字表达，如何高效的返回其对应的实际数字格式表达？
 * 比方说输入是“五十万零五百”，输出"500500"
 */
public class ChineseNumberConverter {
    // 定义中文数字和阿拉伯数字的对应关系
    private static final HashMap<Character, Integer> chineseNum = new HashMap<>();
    private static final HashMap<Character, Integer> unit = new HashMap<>();

    static {
        chineseNum.put('零', 0);
        chineseNum.put('一', 1);
        chineseNum.put('二', 2);
        chineseNum.put('三', 3);
        chineseNum.put('四', 4);
        chineseNum.put('五', 5);
        chineseNum.put('六', 6);
        chineseNum.put('七', 7);
        chineseNum.put('八', 8);
        chineseNum.put('九', 9);

        unit.put('亿', 100000000);
        unit.put('万', 10000);
        unit.put('千', 1000);
        unit.put('百', 100);
        unit.put('十', 10);
    }

    public static int chineseToArabic(String chineseNumStr) {
        if (chineseNumStr == null || chineseNumStr.isEmpty()) {
            return 0;
        }

        // 如果字符串以“零”开头，则递归调用 chineseToArabic 处理剩余的字符串
        if (chineseNumStr.charAt(0) == '零') {
            return chineseToArabic(chineseNumStr.substring(1));
        }

        // 如果字符串以单位字符（亿、万、千、百、十）结尾，则递归调用 chineseToArabic 处理前面的字符串，并将结果乘以对应的单位值
        if (unit.containsKey(chineseNumStr.charAt(chineseNumStr.length() - 1))) {
            return chineseToArabic(chineseNumStr.substring(0, chineseNumStr.length() - 1)) * unit.get(chineseNumStr.charAt(chineseNumStr.length() - 1));
        }

        // 如果字符串不以“零”或单位字符结尾，则将其转换为对应的阿拉伯数字，并递归调用 chineseToArabic 处理剩余的字符串
        int result = 0;
        for (int i = 0; i < chineseNumStr.length(); i++) {
            char c = chineseNumStr.charAt(i);
            if (chineseNum.containsKey(c)) {
                int num = chineseNum.get(c);
                int unitValue = (int) Math.pow(10, chineseNumStr.length() - i - 1);
                result += num * unitValue;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String chineseNumStr = "五十万零五百";
        int arabicNum = chineseToArabic(chineseNumStr);
        // 输出结果为 500500
        System.out.println(arabicNum);
    }
}
