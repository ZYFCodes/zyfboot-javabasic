package org.zyf.javabasic.letcode.jd150.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 二进制求和
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:54
 **/
public class AddBinary {
    public String addBinary(String a, String b) {
        // 使用 StringBuilder 来存储结果
        StringBuilder result = new StringBuilder();
        // 初始化指针 i 和 j 分别指向 a 和 b 的末尾
        int i = a.length() - 1;
        int j = b.length() - 1;
        // 初始化进位 carry
        int carry = 0;

        // 遍历 a 和 b
        while (i >= 0 || j >= 0) {
            // 获取当前位的值，如果指针已经超出字符串范围，则认为当前位为 0
            int bitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int bitB = (j >= 0) ? b.charAt(j) - '0' : 0;
            // 计算当前位的和（包括进位）
            int sum = bitA + bitB + carry;
            // 计算新的进位（如果 sum >= 2 则有进位）
            carry = sum / 2;
            // 将 sum 的余数添加到结果中
            result.append(sum % 2);
            // 指针向前移动
            i--;
            j--;
        }

        // 如果遍历结束后仍有进位，则需要添加到结果中
        if (carry != 0) {
            result.append(carry);
        }

        // 最终需要将结果反转并返回
        return result.reverse().toString();
    }
}
