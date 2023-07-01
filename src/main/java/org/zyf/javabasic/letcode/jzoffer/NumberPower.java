package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 实现函数double Power(double base, int exponent)，求base的exponent次方。
 * 不得使用库函数，同时不需要考虑大数问题。
 * @date 2023/6/6  22:37
 */
public class NumberPower {
    /**
     * 求一个数的整数次方，可以使用快速幂的方法来降低计算复杂度。
     * 快速幂的核心思想是将指数按照二进制展开，通过不断平方和乘积的方式快速计算幂。
     * 具体步骤如下：
     * 	1.	判断指数exponent的正负情况，如果为负，则将base取倒数，同时将指数变为正数。
     * 	2.	初始化结果result为1。
     * 	3.	循环计算，每次将base平方，如果当前指数的二进制位为1，则将result乘以当前base的值。
     * 	4.	最终返回结果result。
     * 需要注意的是，由于计算机对浮点数的表示有精度限制，因此在判断浮点数是否等于0时，不能直接使用==运算符，而是要考虑它们的差值是否在一个很小的范围内。
     */
    public double power(double base, int exponent) {
        if (base == 0 && exponent < 0) {
            throw new IllegalArgumentException("Invalid input: base cannot be 0 when exponent is negative.");
        }

        int absExponent = Math.abs(exponent);
        double result = powerWithUnsignedExponent(base, absExponent);

        if (exponent < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    private double powerWithUnsignedExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result *= result;

        if ((exponent & 0x1) == 1) {
            result *= base;
        }

        return result;
    }

    public static void main(String[] args) {
        NumberPower solution = new NumberPower();
        // 测试样例
        double base = 2.0;
        int exponent = 5;
        double result = solution.power(base, exponent);
        System.out.println(base + " ^ " + exponent + " = " + result);
    }

}
