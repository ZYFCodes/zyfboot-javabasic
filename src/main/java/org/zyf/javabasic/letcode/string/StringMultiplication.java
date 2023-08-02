package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 实现两个大整数字符串的相乘操作，要求高效且正确处理溢出。
 * @date 2021/3/23  23:47
 */
public class StringMultiplication {
    /**
     * 最优解法是使用竖式乘法的思想来实现字符串相乘，这种解法的时间复杂度是 O(n*m)，其中 n 和 m 分别是两个大整数字符串的长度。
     * 具体的最优解法步骤如下：
     * * 创建一个数组 result 来保存相乘的结果，数组的长度为两个大整数字符串的长度之和。
     * * 从两个大整数字符串的最低位（个位）开始遍历，分别与另一个大整数字符串中的每一位相乘，并将结果存储在 result 数组中的对应位置。
     * * 处理进位，将 result 数组中的每一位都保持在 0-9 的范围内，并考虑进位的情况。
     * * 最后将 result 数组转换为字符串，注意去除前导零。
     * 这种解法的核心思想是模拟手工乘法的过程，将两个大整数字符串的每一位相乘，然后累加得到最终结果。由于乘法的结果可能是多位数，因此需要考虑进位的情况。
     * 由于这个解法只需要遍历两个大整数字符串一次，并进行一次进位处理，因此时间复杂度是 O(n*m)，其中 n 和 m 分别是两个大整数字符串的长度。
     * 另外，由于 result 数组的长度是两个大整数字符串长度之和，所以空间复杂度也是 O(n+m)。
     * 综上所述，使用竖式乘法的思想来实现字符串相乘是最优解法，具有高效的时间复杂度和固定的空间复杂度。
     */
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];

        // 从个位开始遍历 num1 和 num2，实现竖式乘法的思想
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 乘积的高位
                int p1 = i + j;
                // 乘积的低位
                int p2 = i + j + 1;

                // 把乘积累加到 result 中的对应位置
                int sum = product + result[p2];
                // 处理进位
                result[p1] += sum / 10;
                result[p2] = sum % 10;
            }
        }

        // 转换 result 数组为字符串
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (sb.length() > 0 || digit != 0) {
                sb.append(digit);
            }
        }

        // 处理结果为 "0" 的特殊情况
        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        String product = multiply(num1, num2);
        // 输出结果：123 * 456 = 56088
        System.out.println(num1 + " * " + num2 + " = " + product);
    }

}
