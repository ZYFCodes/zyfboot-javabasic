package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * @date 2023/6/8  22:22
 */
public class DigitAtIndex {
    /**
     * 我们可以观察到数字序列的规律，可以分为以下几个步骤：
     * 1.	确定目标数字所在的数字位数（位数从1开始），例如第9位是两位数。
     * 2.	确定目标数字所在的具体数字。例如，对于两位数，我们需要确定是10、11、12还是其他数字。
     * 3.	确定目标数字在所在数字中的位置。例如，对于两位数，我们需要确定是第一位还是第二位。
     * 根据以上步骤，我们可以通过模拟来解题。具体步骤如下：
     * 1.	初始化变量digits为1，表示当前数字的位数。
     * 2.	初始化变量count为9，表示当前位数的数字总个数。
     * 3.	初始化变量start为1，表示当前位数的起始数字。
     * 4.	当n小于digits * count时，说明目标数字一定在当前位数的数字中，进行下一步操作。
     * 5.	计算目标数字在当前位数的数字中的位置，即index = (n - 1) / digits。
     * 6.	计算目标数字具体是哪个数字，即num = start + index。
     * 7.	计算目标数字在具体数字中的位置，即position = (n - 1) % digits。
     * 8.	将目标数字转化为字符串，并返回第position位上的数字。
     */
    public int digitAtIndex(int n) {
        if (n < 0) {
            return -1;
        }

        // 当前数字的位数
        int digits = 1;
        // 当前位数的数字总个数
        long count = 9;
        // 当前位数的起始数字
        int start = 1;

        while (n > digits * count) {
            n -= digits * count;
            digits++;
            count *= 10;
            start *= 10;
        }

        // 目标数字
        int num = start + (n - 1) / digits;
        // 目标数字在具体数字中的位置
        int position = (n - 1) % digits;

        String numStr = String.valueOf(num);
        return numStr.charAt(position) - '0';
    }

    public static void main(String[] args) {
        DigitAtIndex solution = new DigitAtIndex();

        int n = 5;
        int digit = solution.digitAtIndex(n);
        System.out.println("The digit at index " + n + " is: " + digit);
    }

}
