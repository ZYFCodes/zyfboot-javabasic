package org.zyf.javabasic.letcode.jzoffer;

/**
 * @program: zyfboot-javabasic
 * @description: 判断一个数是否为丑数
 * @author: zhangyanfeng
 * @create: 2024-08-17 23:32
 **/
public class UglyNumberChecker {
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        // 逐个除以 2、3 和 5
        for (int factor : new int[]{2, 3, 5}) {
            while (n % factor == 0) {
                n /= factor;
            }
        }

        // 检查最终 n 是否为 1
        return n == 1;
    }

    public static void main(String[] args) {
        // 示例测试
        System.out.println(isUgly(6));   // 输出：true
        System.out.println(isUgly(1));   // 输出：true
        System.out.println(isUgly(14));  // 输出：false
    }
}
