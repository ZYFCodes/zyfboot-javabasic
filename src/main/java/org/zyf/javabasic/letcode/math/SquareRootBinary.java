package org.zyf.javabasic.letcode.math;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个计算平方根的函数 sqrt(double value, double error)，并且满足以下条件：
 * 输入的 value 为正数，且大于 0；
 * 输入的 error 为容差范围，且满足 0 < error <= 0.001；
 * 返回的平方根值的误差范围必须在给定的 error 之内，即：|返回值 - 实际平方根| <= error。
 * @author: zhangyanfeng
 * @create: 2024-09-14 13:27
 **/
public class SquareRootBinary {

    // 实现平方根逼近函数
    public static double sqrt(double value, double error) {
        // 检查输入合法性
        if (value <= 0 || error <= 0 || error > 0.001) {
            throw new IllegalArgumentException("输入值不合法");
        }

        // 初始猜测值
        double guess = value / 2.0;

        // 牛顿迭代法
        while (Math.abs(guess * guess - value) > error) {
            guess = (guess + value / guess) / 2.0;
        }

        return guess;
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(sqrt(9, 0.001));    // 输出接近 3 的结果
        System.out.println(sqrt(16, 0.001));   // 输出接近 4 的结果
        System.out.println(sqrt(0.01, 0.001)); // 输出接近 0.1 的结果
    }
}
