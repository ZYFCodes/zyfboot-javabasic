package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给定两个非负整数的大字符串表示，将它们相加并返回结果的大字符串表示。
 * 假设输入的大字符串都是有效的，且不包含前导零，除非本身就是数字 0。
 * @date 2023/6/15  23:05
 */
public class AddStrings {
    /**
     * 为了处理大整数的加法，我们可以使用类似于手工加法的方法，逐位相加，并将进位传递到下一位。我们从字符串的末尾开始逐位相加，模拟手工计算的过程。
     * <p>
     * 具体的解法如下：
     * <p>
     * 1.	创建一个空的结果字符串，用于存储相加的结果。
     * 2.	定义两个指针分别指向两个输入字符串的末尾。
     * 3.	初始化一个进位变量为 0。
     * 4.	循环从末尾向前遍历两个输入字符串，直到两个字符串都遍历完毕。
     * •	在每一位上，将当前位的数字相加，加上进位，并将结果转化为字符。
     * •	如果相加结果超过 9，更新进位为 1，否则更新进位为 0。
     * •	将相加结果的个位数添加到结果字符串的开头。
     * 5.	如果还有一个字符串没有遍历完毕，则继续遍历该字符串的剩余位，并加上进位。
     * 6.	如果最后进位为 1，则将字符 ‘1’ 添加到结果字符串的开头。
     * 7.	将结果字符串返回。
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        // num1字符串的指针
        int i = num1.length() - 1;
        // num2字符串的指针
        int j = num2.length() - 1;
        // 进位
        int carry = 0;
        // 结果字符串
        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0) {
            // 将当前位的数字相加，并加上进位
            if (i >= 0) {
                carry += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += num2.charAt(j) - '0';
                j--;
            }

            // 将相加结果的个位数添加到结果字符串的开头
            result.insert(0, carry % 10);
            // 更新进位
            carry /= 10;
        }

        // 如果还有进位，则在结果字符串开头添加 '1'
        if (carry == 1) {
            result.insert(0, '1');
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        String sum = addStrings(num1, num2);
        // 输出相加结果
        System.out.println("Sum: " + sum);
    }
}
