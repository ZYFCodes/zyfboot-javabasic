package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 输入一个整数 n，求从 1 到 n 这 n 个整数的十进制表示中 1 出现的次数。
 * 例如，输入 12，从 1 到 12 这些整数中，1 出现的次数是 5（1、10、11、12）。
 * @date 2023/6/6  23:44
 */
public class DigitOnes {
    /**
     * 要求从 1 到 n 整数中 1 出现的次数，可以使用数学的方法来解决。
     * 具体步骤如下：
     * 	1.	将 n 拆分为若干位数，从个位开始，设当前位为 weight。
     * 	2.	计算当前位上 1 出现的次数，分为以下三种情况：
     * 	•	weight = 0：当前位为 0，1 出现次数为 high * digit。
     * 	•	weight = 1：当前位为 1，1 出现次数为 high * digit + low + 1。
     * 	•	weight > 1：当前位大于 1，1 出现次数为 (high + 1) * digit。
     * 	3.	遍历每一位，累加每个位上 1 出现的次数。
     * 	4.	返回累加的结果即为从 1 到 n 整数中 1 出现的总次数。
     */
    public int countDigitOne(int n) {
        int count = 0;
        int digit = 1;
        int high = n / 10;
        int cur = n % 10;
        int low = 0;

        while (high != 0 || cur != 0) {
            if (cur == 0) {
                count += high * digit;
            } else if (cur == 1) {
                count += high * digit + low + 1;
            } else {
                count += (high + 1) * digit;
            }

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }

        return count;
    }

    public static void main(String[] args) {
        DigitOnes solution = new DigitOnes();
        // 测试样例
        int n = 12;
        int result = solution.countDigitOne(n);
        System.out.println("Count of digit one: " + result);
    }
}
