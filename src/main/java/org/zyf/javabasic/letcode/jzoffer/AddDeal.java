package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 写一个函数，求两个整数之和，
 * 要求在函数体内不得使用四则运算符号（+、-、*、/）。
 * @date 2023/6/6  23:37
 */
public class AddDeal {
    /**
     * 要实现两个整数的加法，不能使用四则运算符号，可以考虑使用位运算来进行操作。
     * 具体步骤如下：
     * 	1.	使用位运算的异或操作（^）计算两个数的无进位和。异或操作可以将两个数的二进制位进行相加，不考虑进位。
     * 	2.	使用位运算的与操作（&）和左移操作（<<）计算进位值。与操作可以得到两个数相加时的进位，左移操作将进位值向左移动一位。
     * 	3.	将无进位和与进位值相加，得到最终的和。
     * 	4.	重复上述步骤，直到没有进位为止。
     */
    public int add(int a, int b) {
        while (b != 0) {
            // 无进位和
            int sum = a ^ b;
            // 进位值
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        AddDeal solution = new AddDeal();
        // 测试样例
        int a = 5;
        int b = 7;
        int result = solution.add(a, b);
        System.out.println("Sum: " + result);
    }
}
