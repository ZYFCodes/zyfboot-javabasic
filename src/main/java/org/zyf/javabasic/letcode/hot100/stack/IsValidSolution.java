package org.zyf.javabasic.letcode.hot100.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 有效的括号（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:25
 **/
public class IsValidSolution {
    public boolean isValid(String s) {
        // 哈希表存储括号对
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        // 使用栈来存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char ch : s.toCharArray()) {
            // 如果是右括号
            if (bracketMap.containsKey(ch)) {
                // 弹出栈顶元素（左括号），若栈为空则设为'#'
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                // 检查栈顶元素是否与当前右括号匹配
                if (topElement != bracketMap.get(ch)) {
                    return false;
                }
            } else {
                // 如果是左括号，压入栈中
                stack.push(ch);
            }
        }

        // 如果栈为空，则括号匹配有效；否则无效
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsValidSolution solution = new IsValidSolution();

        // 示例 1
        String s1 = "()";
        System.out.println("Output: " + solution.isValid(s1)); // 输出: true

        // 示例 2
        String s2 = "()[]{}";
        System.out.println("Output: " + solution.isValid(s2)); // 输出: true

        // 示例 3
        String s3 = "(]";
        System.out.println("Output: " + solution.isValid(s3)); // 输出: false
    }
}
