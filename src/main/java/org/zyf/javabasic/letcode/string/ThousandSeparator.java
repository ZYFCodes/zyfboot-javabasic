package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 给定一个整数，将其转换为字符串形式并添加千位分隔符。
 * @date 2021/3/26  22:53
 */
public class ThousandSeparator {

    /**
     * 最优解法是通过将整数转换为字符串形式，并从字符串末尾开始每隔三位添加一个千位分隔符（逗号）来实现。这种解法的时间复杂度是 O(log10(n))，其中 n 是给定的整数。
     * 具体的最优解法步骤如下：
     * * 将给定的整数转换为字符串形式。
     * * 创建一个 StringBuilder 来保存结果字符串。
     * * 从字符串的末尾开始遍历，每隔三位添加一个逗号，并将字符拼接到 StringBuilder 中。
     * * 如果整数长度不是 3 的倍数，需要额外处理剩余的位数。
     * 这种解法的核心思想是将整数转换为字符串形式，然后从字符串末尾开始遍历并添加千位分隔符。
     * 由于这个解法是通过字符串遍历来实现的，并且每次处理只需要常数时间，因此时间复杂度是 O(log10(n))，其中 n 是给定的整数。
     * 另外，由于使用了 StringBuilder 来保存结果字符串，所以空间复杂度是 O(log10(n))，同样取决于整数的位数。
     * 综上所述，通过将整数转换为字符串并从末尾开始添加千位分隔符是最优解法，具有高效的时间复杂度和固定的空间复杂度。
     */
    public static String addThousandSeparator(int num) {
        if (num == 0) {
            return "0";
        }

        // 将整数转换为字符串形式
        String numStr = String.valueOf(num);
        StringBuilder result = new StringBuilder();

        int count = 0;
        // 从字符串的末尾开始遍历
        for (int i = numStr.length() - 1; i >= 0; i--) {
            // 将字符插入到 result 的开头
            result.insert(0, numStr.charAt(i));

            count++;
            // 每隔三位添加一个千位分隔符
            if (count % 3 == 0 && i > 0) {
                result.insert(0, ',');
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int num1 = 1234567;
        int num2 = 1234567890;

        String formattedNum1 = addThousandSeparator(num1);
        String formattedNum2 = addThousandSeparator(num2);

        // 输出结果：1234567 -> 1,234,567
        System.out.println(num1 + " -> " + formattedNum1);
        // 输出结果：1234567890 -> 1,234,567,890
        System.out.println(num2 + " -> " + formattedNum2);
    }

}
