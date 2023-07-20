package org.zyf.javabasic.letcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定两个整数，分别表示分数的分子 `numerator` 和分母 `denominator`，以字符串形式返回小数。
 * 如果小数部分是循环的，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回任意一个。
 * <p>
 * 示例 1：输入：numerator = 1, denominator = 2.         输出："0.5"
 * 示例 2：输入：numerator = 2, denominator = 1          输出："2"
 * 示例 3：输入：numerator = 2, denominator = 3         输出："0.(6)"
 * 示例 4：输入：numerator = 4, denominator = 333     输出："0.(012)"
 * 示例 5：输入：numerator = 1, denominator = 5          输出："0.2"
 * <p>
 * 提示：
 * - -2^31 <= numerator, denominator <= 2^31 - 1
 * - denominator != 0
 * @date 2023/7/8  23:52
 */
public class FractionToRecurringDecimal {

    /**
     * 最优解法可以通过模拟长除法来实现分数转循环小数。
     * 1. 首先判断结果的正负号，如果分子和分母的符号不一致，则结果为负数。
     * 2. 计算结果的整数部分，通过 `numerator / denominator` 可以得到整数部分 `integerPart`。
     * 3. 计算结果的小数部分，通过 `numerator % denominator` 可以得到小数部分的分子 `remainder`。
     * 4. 创建一个哈希表用于记录小数部分的余数以及对应的位置，然后开始进行循环小数的计算。
     * 5. 将 `remainder` 乘以 10，得到下一位小数的分子 `nextNumerator`，同时更新 `remainder` 为 `nextNumerator % denominator`。
     * 6. 在每次计算下一位小数时，检查当前的 `remainder` 是否在哈希表中，如果是，说明出现了循环，将循环部分括在括号内即可。
     * 7. 重复步骤 5 和 6 直到 `remainder` 为 0 或者出现循环为止。
     * 该算法的时间复杂度为 O(denominator)，其中 denominator 为分母的值。
     * 因为每次计算下一位小数时，分子 `remainder` 的值会在 0 到 `denominator-1` 之间，最多需要进行 `denominator` 次计算。
     * 而空间复杂度为 O(denominator)，需要额外的哈希表来记录小数部分的余数。
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        // 处理结果的符号
        StringBuilder result = new StringBuilder();
        if ((numerator < 0 && denominator > 0)
                || (numerator > 0 && denominator < 0)) {
            result.append("-");
        }

        // 计算整数部分
        long integerPart = Math.abs((long) numerator / denominator);
        result.append(integerPart);

        // 计算小数部分
        long remainder = Math.abs((long) numerator % denominator);
        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");
        // 记录小数部分的余数和对应位置
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            // 检查当前余数是否在哈希表中，如果是，说明出现循环
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                // 非循环部分
                String nonRecurringPart = result.substring(0, index);
                // 循环部分
                String recurringPart = result.substring(index);
                return nonRecurringPart + "(" + recurringPart + ")";
            }

            // 记录余数的位置
            map.put(remainder, result.length());
            // 余数乘以10得到下一位小数的分子
            remainder *= 10;
            // 计算下一位小数
            result.append(remainder / denominator);
            // 更新余数
            remainder %= denominator;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int numerator1 = 1;
        int denominator1 = 2;

        int numerator2 = 2;
        int denominator2 = 1;

        int numerator3 = 2;
        int denominator3 = 3;

        int numerator4 = 4;
        int denominator4 = 333;

        int numerator5 = 1;
        int denominator5 = 5;

        System.out.println("Input: " + numerator1 + ", " + denominator1 + " Output: " + fractionToDecimal(numerator1, denominator1));
        System.out.println("Input: " + numerator2 + ", " + denominator2 + " Output: " + fractionToDecimal(numerator2, denominator2));
        System.out.println("Input: " + numerator3 + ", " + denominator3 + " Output: " + fractionToDecimal(numerator3, denominator3));
        System.out.println("Input: " + numerator4 + ", " + denominator4 + " Output: " + fractionToDecimal(numerator4, denominator4));
        System.out.println("Input: " + numerator5 + ", " + denominator5 + " Output: " + fractionToDecimal(numerator5, denominator5));
    }
}
