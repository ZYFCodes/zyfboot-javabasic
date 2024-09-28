package org.zyf.javabasic.letcode.math;

/**
 * @program: zyfboot-javabasic
 * @description: 自除数（Self-Dividing Number）是指一个数能够被它的每一位数字整除，且每一位数字都不为 0。
 * 例如，128 是一个自除数，因为 128 能被 1、2 和 8 整除。
 * @author: zhangyanfeng
 * @create: 2024-09-11 18:19
 **/
public class SelfDividingNumberChecker {
    // 判断一个数是否为自除数
    public static boolean isSelfDividing(int num) {
        int originalNum = num;  // 保存原始数字
        while (num > 0) {
            int digit = num % 10;  // 取当前的最低位数字
            // 如果某位为 0 或者原数字不能被该位整除，则不是自除数
            if (digit == 0 || originalNum % digit != 0) {
                return false;
            }
            num /= 10;  // 去掉最低位
        }
        return true;  // 如果所有位都能整除，则是自除数
    }

    public static void main(String[] args) {
        // 测试
        int number = 128;
        if (isSelfDividing(number)) {
            System.out.println(number + " 是自除数");
        } else {
            System.out.println(number + " 不是自除数");
        }
    }
}
