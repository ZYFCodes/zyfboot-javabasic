package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 打印从1到最大的n位数。
 * 题目要求按照从小到大的顺序打印出从1到最大的n位十进制数。
 * @date 2023/6/1  00:59
 */
public class PrintNumbers {
    /**
     * 最优的解题思路是使用数组模拟大数加法。具体步骤如下：
     * 	1.	初始化一个长度为n的数组number，用于存储当前的大数。
     * 	2.	通过递归或循环实现对数组number的增加，模拟大数加法。
     * 	3.	每次增加时，从数组的最低位开始进行加1操作，然后判断是否产生进位。如果有进位，则将进位传递到更高位。
     * 	4.	每次增加完成后，将数组number打印出来。
     * 	5.	当数组number的最高位不为9时，继续增加；当最高位为9时，说明已经达到了最大的n位数，停止增加。
     */
    public void printNumbers(int n) {
        if (n <= 0) {
            return;
        }
        // 初始化长度为n的数组
        int[] number = new int[n];

        // 模拟大数加法，直到最大的n位数
        while (!increment(number)) {
            // 打印当前的大数
            printNumber(number);
        }
    }

    /**
     * 模拟大数加法
     */
    private boolean increment(int[] number) {
        // 进位标志
        int carry = 0;

        for (int i = number.length - 1; i >= 0; i--) {
            // 当前位数与进位的和
            int sum = number[i] + carry;

            if (i == number.length - 1) {
                // 最低位数加1
                sum++;
            }

            if (sum >= 10) {
                if (i == 0) {
                    // 已经达到最大的n位数，返回true
                    return true;
                }
                // 产生进位，当前位数减去10
                sum -= 10;
                // 设置进位标志
                carry = 1;
            } else {
                // 无进位，进位标志为0
                carry = 0;
            }
            // 更新当前位的值
            number[i] = sum;
        }

        return false;
    }

    /**
     * 打印当前的大数
     */
    private void printNumber(int[] number) {
        // 是否是前导0
        boolean isBeginning0 = true;

        for (int i = 0; i < number.length; i++) {
            if (isBeginning0 && number[i] != 0) {
                // 遇到第一个非0数字，取消前导0标志
                isBeginning0 = false;
            }

            if (!isBeginning0) {
                // 打印非0数字
                System.out.print(number[i]);
            }
        }

        // 换行
        System.out.println();
    }

    public static void main(String[] args) {
        PrintNumbers solution = new PrintNumbers();

        // 测试输入n的值
        int n = 3;

        // 调用方法打印从1到最大的n位数
        solution.printNumbers(n);
    }
}
