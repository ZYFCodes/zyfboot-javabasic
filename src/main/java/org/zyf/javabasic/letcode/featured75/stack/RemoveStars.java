package org.zyf.javabasic.letcode.featured75.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 从字符串中移除星号
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:17
 **/
public class RemoveStars {
    public String removeStars(String s) {
        // 初始化一个栈，用于存放非星号字符
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            if (c != '*') {
                // 如果字符不是星号，将其压入栈中
                stack.push(c);
            } else {
                // 如果字符是星号，弹出栈顶字符
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        // 将栈中的字符拼接成结果字符串
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // 由于栈的性质，最后的字符顺序是相反的，因此我们需要翻转字符串
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveStars solution = new RemoveStars();

        // 测试用例 1
        String s1 = "leet**cod*e";
        System.out.println(solution.removeStars(s1)); // 输出: "lecoe"

        // 测试用例 2
        String s2 = "erase*****";
        System.out.println(solution.removeStars(s2)); // 输出: ""
    }
}
