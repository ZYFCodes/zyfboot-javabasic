package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 验证给定的字符串是否可以解释为一个有效的数字。
 * <p>
 * 有效数字可以分成以下几个部分：
 * 1. 一个 小数 或者 整数
 * 2. (可选) 一个 'e' 或 'E' ，后面跟着一个 整数
 * <p>
 * 小数（Decimal）可以分成以下几个部分：
 * 1. (可选) 一个符号字符（'+' 或 '-'）
 * 2. 至少一个数字，包括在一个点 '.' 下面的
 * 3. (可选) 跟着一个点 '.'，后面再跟着至少一个数字
 * <p>
 * 整数（Integer）可以分成以下几个部分：
 * 1. (可选) 一个符号字符（'+' 或 '-'）
 * 2. 至少一个数字
 * <p>
 * 部分有效数字列举如下：
 * - "+100"
 * - "5e2"
 * - "-123"
 * - "3.1416"
 * - "-1E-16"
 * <p>
 * 部分无效数字列举如下：
 * - "12e"
 * - "1a3.14"
 * - "1.2.3"
 * - "+-5"
 * - "12e+5.4"
 * <p>
 * 说明：
 * - 在本题中，没有什么前置或后置的空格。
 * - 一个有效的数字（满足上述条件）可以有前导空格或后导空格。
 * - 但是，不能有中间空格。
 * @date 2023/7/15  23:34
 */
public class ValidNumber {

    /**
     * 最优解法可以通过使用有限状态自动机（Finite State Machine）来实现有效数字的验证。
     * 有限状态自动机是一种表示有限个状态及其状态之间的转移的数学模型。在本题中，可以将有效数字的验证过程抽象为一系列状态和状态之间的转移。
     * 具体步骤如下：
     * 1. 定义一个状态机，包含以下状态：
     * - 空格状态：表示前导空格。
     * - 符号状态：表示符号字符。
     * - 整数状态：表示整数部分。
     * - 小数点状态：表示小数点。
     * - 小数状态：表示小数部分。
     * - 幂符号状态：表示幂符号 'e' 或 'E'。
     * - 幂整数状态：表示幂的整数部分。
     * - 后导空格状态：表示后导空格。
     * 2. 定义状态之间的转移规则，根据输入字符决定状态之间的转移。根据题目要求，可以列举以下情况：
     * - 如果当前状态是空格状态，遇到空格继续保持空格状态，遇到符号字符转移到符号状态，遇到数字转移到整数状态，遇到小数点转移到小数点状态。
     * - 如果当前状态是符号状态，遇到数字转移到整数状态，遇到小数点转移到小数点状态。
     * - 如果当前状态是整数状态，遇到数字继续保持整数状态，遇到小数点转移到小数状态，遇到幂符号转移到幂符号状态，遇到空格转移到后导空格状态。
     * - 如果当前状态是小数点状态，遇到数字转移到小数状态，遇到幂符号转移到幂符号状态，遇到空格转移到后导空格状态。
     * - 如果当前状态是小数状态，遇到数字继续保持小数状态，遇到幂符号转移到幂符号状态，遇到空格转移到后导空格状态。
     * - 如果当前状态是幂符号状态，遇到符号字符转移到符号状态，遇到数字转移到幂整数状态。
     * - 如果当前状态是幂整数状态，遇到数字继续保持幂整数状态，遇到空格转移到后导空格状态。
     * - 如果当前状态是后导空格状态，遇到空格继续保持后导空格状态。
     * 3. 定义一个布尔数组，用于表示每个状态是否是接受状态。在本题中，接受状态包括整数状态、小数状态、幂整数状态和后导空格状态。
     * 4. 依次处理输入字符，并根据转移规则更新状态，如果最终的状态是接受状态，则表示输入字符串是有效的数字。
     * 该算法的时间复杂度为 O(n)，其中 n 是输入字符串的长度。因为需要遍历整个输入字符串来进行状态转移。
     * 而空间复杂度为 O(1)，只需要常数级的额外空间来保存状态和转移规则。
     */
    public static boolean isNumber(String s) {
        // 初始化状态为 0，表示空格状态
        int state = 0;
        // 定义状态之间的转移规则
        int[][] transfer = {
                {0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}
        };
        // 定义接受状态
        int[] accept = {0, 0, 0, 1, 0, 1, 1, 0, 1};

        // 依次处理输入字符
        for (char c : s.toCharArray()) {
            // 获取字符的类型
            int type = getType(c);
            if (type == -1) {
                // 非法字符，直接返回 false
                return false;
            }
            // 更新状态
            state = transfer[state][type];
            if (state == -1) {
                // 遇到不可转移的状态，表示输入字符串不是有效数字
                return false;
            }
        }

        // 判断最终状态是否是接受状态
        return accept[state] == 1;
    }

    public static int getType(char c) {
        if (c == ' ') {
            // 空格字符
            return 0;
        }
        if (c == '+' || c == '-') {
            // 符号字符
            return 1;
        }
        if (c >= '0' && c <= '9') {
            // 数字字符
            return 2;
        }
        if (c == '.') {
            // 小数点字符
            return 3;
        }
        if (c == 'e' || c == 'E') {
            // 幂符号字符
            return 4;
        }
        // 非法字符
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "0";
        String s2 = " 0.1 ";
        String s3 = "abc";
        String s4 = "1 a";
        String s5 = "2e10";
        String s6 = " -90e3   ";
        String s7 = " 1e";
        String s8 = "e3";
        String s9 = " 6e-1";
        String s10 = " 99e2.5 ";
        String s11 = "53.5e93";
        String s12 = " --6 ";
        String s13 = "-+3";
        String s14 = "95a54e53";

        System.out.println("Input: \"" + s1 + "\" Output: " + isNumber(s1));
        System.out.println("Input: \"" + s2 + "\" Output: " + isNumber(s2));
        System.out.println("Input: \"" + s3 + "\" Output: " + isNumber(s3));
        System.out.println("Input: \"" + s4 + "\" Output: " + isNumber(s4));
        System.out.println("Input: \"" + s5 + "\" Output: " + isNumber(s5));
        System.out.println("Input: \"" + s6 + "\" Output: " + isNumber(s6));
        System.out.println("Input: \"" + s7 + "\" Output: " + isNumber(s7));
        System.out.println("Input: \"" + s8 + "\" Output: " + isNumber(s8));
        System.out.println("Input: \"" + s9 + "\" Output: " + isNumber(s9));
        System.out.println("Input: \"" + s10 + "\" Output: " + isNumber(s10));
        System.out.println("Input: \"" + s11 + "\" Output: " + isNumber(s11));
        System.out.println("Input: \"" + s12 + "\" Output: " + isNumber(s12));
        System.out.println("Input: \"" + s13 + "\" Output: " + isNumber(s13));
        System.out.println("Input: \"" + s14 + "\" Output: " + isNumber(s14));
    }
}
