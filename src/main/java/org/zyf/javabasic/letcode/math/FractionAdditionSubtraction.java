package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个表示分数加减表达式的字符串，你需要返回一个字符串，表示该表达式的结果。
 * 整数部分的分数格式为整数或分数（例如 2，-2，1/2，-1/2），
 * 对于这样的表达式中的每一个操作符（+、-、*、/），你都需要给出结果并将结果用分数格式作为最简分数。
 * 整数的除法只保留整数部分。你需要返回一个字符串，其中包含整数部分和分数部分，如果两个部分存在的话。
 * <p>
 * 示例 1：输入: "-1/2+1/2"     输出: "0/1"
 * 示例 2：输入: "-1/2+1/2+1/3"     输出: "1/3"
 * 示例 3：输入: "1/3-1/2"      输出: "-1/6"
 * 示例 4：输入: "5/3+1/3"     输出: "2/1"
 * <p>
 * 提示：
 * - 输入和输出都是字符串形式的，每个输入表示一个分数表达式，而输出包含整数部分和分数部分。
 * 输入的范围是 [-10,000, 10,000]。
 * @date 2023/7/17  23:51
 */
public class FractionAdditionSubtraction {

    /**
     * 最优解法可以通过求最小公倍数和通分的方式来实现分数加减运算。
     * 具体步骤如下：
     * 1. 定义一个函数来求两个整数的最大公约数（Greatest Common Divisor，简称GCD）。
     * 2. 定义一个函数来求两个整数的最小公倍数（Least Common Multiple，简称LCM），根据 GCD 和公式 LCM(a, b) = a * b / GCD(a, b) 计算。
     * 3. 定义一个函数来对分数进行通分，使其分母相同。通过求得的最小公倍数将分数通分。
     * 4. 定义一个函数来将两个通分后的分数进行加减运算，并将结果化简成最简分数。
     * 该算法的时间复杂度取决于通分和化简过程中的计算。在本题中，分数通分和化简的时间复杂度可以认为是常数级别。
     * 因此，整体的时间复杂度为 O(1)。而空间复杂度为 O(1)，只需要常数级的额外空间来保存结果。
     */
    public static String fractionAddition(String expression) {
        // 使用正则表达式拆分分数表达式
        String[] fractions = expression.split("(?=[-+])");
        // 初始化分子为0
        int numerator = 0;
        // 初始化分母为1
        int denominator = 1;

        for (String fraction : fractions) {
            // 获取分数的分子和分母
            int[] parts = getParts(fraction);
            int num = parts[0];
            int den = parts[1];

            // 通分，更新分子和分母
            numerator = numerator * den + num * denominator;
            denominator *= den;

            // 化简分子和分母，保证最简分数
            int gcd = getGCD(Math.abs(numerator), denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        return numerator + "/" + denominator;
    }

    /**
     * 辅助函数，获取分数的分子和分母
     */
    private static int[] getParts(String fraction) {
        String[] parts = fraction.split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        return new int[]{numerator, denominator};
    }

    /**
     * 辅助函数，求两个整数的最大公约数
     */
    private static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        String expression1 = "-1/2+1/2";
        String expression2 = "-1/2+1/2+1/3";
        String expression3 = "1/3-1/2";
        String expression4 = "5/3+1/3";

        System.out.println("Input: \"" + expression1 + "\" Output: " + fractionAddition(expression1));
        System.out.println("Input: \"" + expression2 + "\" Output: " + fractionAddition(expression2));
        System.out.println("Input: \"" + expression3 + "\" Output: " + fractionAddition(expression3));
        System.out.println("Input: \"" + expression4 + "\" Output: " + fractionAddition(expression4));
    }
}
