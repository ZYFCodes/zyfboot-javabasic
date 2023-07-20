package org.zyf.javabasic.letcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 罗马数字包含以下七种字符：I、V、X、L、C、D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如，罗马数字 2 写做 II，即为两个并列的 1。12 写做 XII，即为 X + II。 27 写做 XXVII，即为 XX + V + II。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * - I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * - X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * - C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1：输入：s = "III"                   输出：3
 * 示例 2：输入：s = "IV"                  输出：4
 * 示例 3：输入：s = "IX"                  输出：9
 * 示例 4：输入：s = "LVIII"              输出：58
 * 解释：L = 50，V = 5，III = 3。
 * 示例 5：输入：s = "MCMXCIV"    输出：1994
 * 解释：M = 1000，CM = 900，XC = 90，IV = 4。
 * <p>
 * 提示：
 * - 1 <= s.length <= 15
 * - s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * - 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内。
 * @date 2023/7/5  22:35
 */
public class RomanAndInteger {
    /**
     * 最优解法可以通过以下步骤来实现罗马数字转整数：
     * 1. 初始化一个变量 `result` 用于保存最终的整数结果，初始值为0。
     * 2. 遍历输入的罗马数字字符串，从左到右依次处理每个字符。
     * 3. 对于每个字符，根据其对应的数值加或减到 `result` 中，同时考虑特殊情况下的组合数值。
     * 4. 特殊情况下的组合数值是指在罗马数字中，某个较小的字符出现在较大的字符的左侧，此时需要减去较小字符对应的数值，并加上较大字符对应的数值。
     * 5. 完成整个字符串的遍历后，`result` 即为转换后的整数结果。
     * 该算法的时间复杂度为 O(n)，其中 n 是罗马数字字符串的长度，因为需要遍历整个字符串。
     * 而空间复杂度为 O(1)，只用了常数级的额外空间。
     */
    public static int romanToInt(String s) {
        // 创建罗马数字字符与对应数值的映射表
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        // 用于记录前一个字符的数值
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = romanMap.get(currentChar);

            // 根据当前字符的数值和前一个字符的数值，判断是否需要减去较小字符对应的数值
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            // 更新prevValue为当前字符的数值
            prevValue = currentValue;
        }

        return result;
    }

    public static void main(String[] args) {
        String s1 = "III";
        String s2 = "IV";
        String s3 = "IX";
        String s4 = "LVIII";
        String s5 = "MCMXCIV";

        System.out.println("Input: " + s1 + " Output: " + romanToInt(s1));
        System.out.println("Input: " + s2 + " Output: " + romanToInt(s2));
        System.out.println("Input: " + s3 + " Output: " + romanToInt(s3));
        System.out.println("Input: " + s4 + " Output: " + romanToInt(s4));
        System.out.println("Input: " + s5 + " Output: " + romanToInt(s5));
    }
}
