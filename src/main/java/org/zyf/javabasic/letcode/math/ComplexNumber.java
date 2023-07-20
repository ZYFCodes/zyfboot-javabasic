package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 要求实现两个复数的四则运算，包括加法、减法、乘法和除法。
 * 复数由实部和虚部组成，一般表示为a + bi，其中a为实部，b为虚部，i为虚数单位，满足i^2 = -1。
 * 两个复数的四则运算规则如下：
 * 1.	复数加法：(a + bi) + (c + di) = (a + c) + (b + d)i
 * 2.	复数减法：(a + bi) - (c + di) = (a - c) + (b - d)i
 * 3.	复数乘法：(a + bi) * (c + di) = (ac - bd) + (ad + bc)i
 * 4.	复数除法：(a + bi) / (c + di) = (ac + bd) / (c^2 + d^2) + (bc - ad)i / (c^2 + d^2)
 * 其中，a、b、c和d均为实数。
 * 为了实现这些运算，你可以定义一个复数类，其中包含实部和虚部作为成员变量，并编写方法来进行四则运算。
 * @date 2023/7/19  23:41
 */
public class ComplexNumber {
    // 实部
    private double real;
    // 虚部
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // 复数加法
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    // 复数减法
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    // 复数乘法
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }

    // 复数除法
    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexNumber(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    public static void main(String[] args) {
        ComplexNumber num1 = new ComplexNumber(3, 2);
        ComplexNumber num2 = new ComplexNumber(1, -1);

        // 四则运算示例并验证
        System.out.println("Number 1: " + num1);
        System.out.println("Number 2: " + num2);
        // 复数加法
        System.out.println("Addition: " + num1.add(num2));
        // 复数减法
        System.out.println("Subtraction: " + num1.subtract(num2));
        // 复数乘法
        System.out.println("Multiplication: " + num1.multiply(num2));
        // 复数除法
        System.out.println("Division: " + num1.divide(num2));
    }

}
