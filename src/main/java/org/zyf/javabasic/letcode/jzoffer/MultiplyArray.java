package org.zyf.javabasic.letcode.jzoffer;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个数组 A[0,1,...,n-1]，
 * 请构建一个数组 B[0,1,...,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的所有元素的乘积。
 * 不能使用除法。
 * @date 2023/6/4  22:34
 */
public class MultiplyArray {
    /**
     * 可以将数组 B 看作两部分的乘积，即左边部分和右边部分的乘积。
     * 首先计算数组 B 中的左边部分乘积，然后再计算右边部分乘积。具体步骤如下：
     * 1.	初始化结果数组 B，长度为 n，并初始化为全 1。
     * 2.	计算左边部分的乘积。从左往右遍历数组 A，累乘每个元素并更新 B 中对应的位置，即 B[i] *= A[i-1]。
     * 3.	计算右边部分的乘积。从右往左遍历数组 A，累乘每个元素并更新 B 中对应的位置，即 B[i] *= A[i+1]。
     * 4.	返回最终的结果数组 B。
     * 这样，数组 B 中的每个元素就是数组 A 中除了对应位置元素之外的所有元素的乘积。
     */
    public static int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];

        // 计算左边部分的乘积
        B[0] = 1;
        for (int i = 1; i < length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }

        // 计算右边部分的乘积并与左边部分相乘
        int temp = 1;
        for (int i = length - 2; i >= 0; i--) {
            temp *= A[i + 1];
            B[i] *= temp;
        }

        return B;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = multiply(A);
        System.out.println(Arrays.toString(B));
    }

}
