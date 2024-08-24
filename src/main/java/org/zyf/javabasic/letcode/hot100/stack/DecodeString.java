package org.zyf.javabasic.letcode.hot100.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 字符串解码（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:34
 **/
public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); // 计算数字（可能是多位数）
            } else if (ch == '[') {
                countStack.push(k); // 保存当前的重复次数
                stringStack.push(currentString); // 保存当前字符串
                currentString = new StringBuilder(); // 重置 currentString 开始处理新字符
                k = 0; // 重置 k
            } else if (ch == ']') {
                int count = countStack.pop(); // 弹出重复次数
                StringBuilder decodedString = stringStack.pop(); // 弹出栈顶字符串
                for (int i = 0; i < count; i++) {
                    decodedString.append(currentString); // 重复并拼接字符串
                }
                currentString = decodedString; // 将结果存入 currentString
            } else {
                currentString.append(ch); // 普通字符直接添加
            }
        }

        return currentString.toString(); // 返回最终解码后的字符串
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString("3[a]2[bc]")); // 输出 "aaabcbc"
        System.out.println(ds.decodeString("3[a2[c]]")); // 输出 "accaccacc"
        System.out.println(ds.decodeString("2[abc]3[cd]ef")); // 输出 "abcabccdcdcdef"
        System.out.println(ds.decodeString("abc3[cd]xyz")); // 输出 "abccdcdcdxyz"
    }
}
