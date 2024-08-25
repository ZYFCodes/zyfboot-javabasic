package org.zyf.javabasic.letcode.jd150.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 颠倒二进制位
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:57
 **/
public class ReverseBits {
    // 颠倒32位无符号整数的二进制位
    public int reverseBits(int n) {
        // 初始化结果为0
        int result = 0;

        // 遍历32位
        for (int i = 0; i < 32; i++) {
            // 将result左移1位，为下一个反转位腾出空间
            result <<= 1;
            // 将n的最低位加到result的最低位
            result |= (n & 1);
            // 将n右移1位，处理下一个位
            n >>= 1;
        }

        // 返回反转后的结果
        return result;
    }
}
