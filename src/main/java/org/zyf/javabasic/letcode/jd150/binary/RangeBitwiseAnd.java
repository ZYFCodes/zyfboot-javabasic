package org.zyf.javabasic.letcode.jd150.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 数字范围按位与
 * @author: zhangyanfeng
 * @create: 2024-08-25 19:11
 **/
public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        // 不断右移left和right，直到它们相等
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        // 将相等的left左移回原位置
        return left << shift;
    }
}
