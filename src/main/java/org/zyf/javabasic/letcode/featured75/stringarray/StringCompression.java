package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 压缩字符串
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:04
 **/
public class StringCompression {
    public static int compress(char[] chars) {
        // write 指针用于记录写入位置
        int write = 0;
        int i = 0;

        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;

            // 计算连续字符的数量
            while (i < chars.length && chars[i] == currentChar) {
                i++;
                count++;
            }

            // 写入当前字符到压缩后的位置
            chars[write++] = currentChar;

            // 如果字符数量大于1，写入数量
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }

        // 返回新的长度
        return write;
    }

    // 测试方法
    public static void main(String[] args) {
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(compress(chars1)); // 输出: 6

        char[] chars2 = {'a'};
        System.out.println(compress(chars2)); // 输出: 1

        char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(compress(chars3)); // 输出: 4
    }
}
