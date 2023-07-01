package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 求 1+2+3+…+n 的和，
 * 要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句（A?B:C）。
 * @date 2023/6/6  23:35
 */
public class NumsSum {
    /**
     * 要求不能使用循环语句和条件判断语句来求解数字之和，可以考虑使用递归来实现。
     * 具体步骤如下：
     * 	1.	使用递归函数 sumNums，传入一个参数 n 表示要求和的范围，初始值为 n。
     * 	2.	使用逻辑运算符 && 来判断递归的终止条件，当 n=0 时终止递归。
     * 	3.	在递归过程中，使用 n-1 来缩小范围，并通过递归调用 sumNums(n-1) 来求解前 n-1 个数字的和。
     * 	4.	将递归得到的结果与 n 相加，即可得到 1+2+3+…+n 的和。
     */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        NumsSum solution = new NumsSum();
        // 测试样例
        int n = 5;
        int result = solution.sumNums(n);
        System.out.println("Sum of numbers: " + result);
    }
}
