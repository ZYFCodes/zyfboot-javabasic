package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定两个表示复数的字符串 num1 和 num2，返回两个复数的乘积。字符串表示的复数以 "a+bi" 的形式，其中 a 和 b 是实部和虚部，i 表示虚数单位。
 * 注意：
 * 1. 输入字符串中的虚数单位 i 以及两个复数的虚部 b 均不包含字符 i。
 * 2. 输入字符串中的虚数单位 i 仅用于表示虚部。
 * <p>
 * 示例 1：输入：num1 = "1+1i", num2 = "1+1i"     输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i + i + i^2 = 1 + 2i
 * 示例 2：输入：num1 = "1+-1i", num2 = "1+-1i"     输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 - i - i + i^2 = 1 - 2i
 * <p>
 * 提示：
 * - 输入字符串 num1 和 num2 长度均不超过 100。
 * - 输入字符串中的实部和虚部都是整数，取值范围是 [-100, 100]。
 * - 输出结果的实部和虚部也都将表示为整数。
 * @date 2023/6/28  23:10
 */
public class ComplexNumberMultiplication {

    /**
     * 最优解法可以通过将复数分割为实部和虚部，然后进行计算得到乘积。
     * 假设两个复数分别为 "a+bi" 和 "c+di"，其中 a、b、c 和 d 都是整数。
     * 它们的乘积可以展开为：(a+bi) * (c+di) = ac + adi + bci + bdi^2。
     * 因为 i^2 = -1，所以结果可以简化为：(a+bi) * (c+di) = (ac - bd) + (ad + bc)i。
     * 可以看到，实部的计算是 a * c - b * d，虚部的计算是 a * d + b * c。
     * 通过这两个计算得到最终的实部和虚部，然后将其拼接为结果字符串。
     * 具体步骤如下：
     * 1. 将 num1 和 num2 按照 "+" 进行拆分，得到两个数组：[a, bi] 和 [c, di]。
     * 2. 将 a、b、c 和 d 解析为整数。
     * 3. 计算实部和虚部的结果：实部为 ac - bd，虚部为 ad + bc。
     * 4. 将实部和虚部拼接为结果字符串，并返回。
     * 该算法的时间复杂度为 O(1)，因为只进行了常数次计算。而空间复杂度为 O(1)，因为只需要常数的额外空间。
     */
    public static String complexNumberMultiply(String num1, String num2) {
        // 将 num1 和 num2 按照 "+" 进行拆分，得到两个数组：[a, bi] 和 [c, di]。
        String[] num1Arr = num1.split("\\+");
        String[] num2Arr = num2.split("\\+");

        // 将 a、b、c 和 d 解析为整数。
        int a = Integer.parseInt(num1Arr[0]);
        int b = Integer.parseInt(num1Arr[1].substring(0, num1Arr[1].length() - 1));
        int c = Integer.parseInt(num2Arr[0]);
        int d = Integer.parseInt(num2Arr[1].substring(0, num2Arr[1].length() - 1));

        // 计算实部和虚部的结果：实部为 ac - bd，虚部为 ad + bc。
        int real = a * c - b * d;
        int imag = a * d + b * c;

        // 将实部和虚部拼接为结果字符串，并返回。
        return real + "+" + imag + "i";
    }

    public static void main(String[] args) {
        String num1 = "1+1i";
        String num2 = "1+1i";

        System.out.println("Input: num1 = " + num1 + ", num2 = " + num2 + " Output: " + complexNumberMultiply(num1, num2));
    }

}
