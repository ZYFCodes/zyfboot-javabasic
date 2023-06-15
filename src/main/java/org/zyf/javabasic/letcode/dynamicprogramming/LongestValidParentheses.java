package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:输入: "(()"        输出: 2       解释: 最长有效括号子串为 "()"
 * 示例 2:输入: ")()())"    输出: 4      解释: 最长有效括号子串为 "()()"
 * @date 2023/5/22  23:18
 */
public class LongestValidParentheses {
    /**
     * 要找到最长的包含有效括号的子串的长度，我们可以使用栈的思想来解决。
     * 首先，我们定义一个栈和一个变量 maxLen，栈用于记录括号的索引，初始时将 -1 放入栈中，用于处理边界情况。
     * 然后，我们遍历字符串，对于每个字符：
     * * 如果是左括号 '('，将其索引入栈。
     * * 如果是右括号 ')'，弹出栈顶元素表示当前右括号的匹配。
     * * 如果栈为空，表示当前右括号没有匹配的左括号，将当前右括号的索引入栈，用于处理下一个可能的有效括号子串的起始位置。
     * * 如果栈不为空，计算当前有效括号子串的长度，即当前右括号的索引减去栈顶元素的索引，更新 maxLen。
     * 最后，返回 maxLen 即为最长有效括号子串的长度。
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "(()";
        int maxLen1 = new LongestValidParentheses().longestValidParentheses(s1);
        System.out.println("最长有效括号子串的长度为：" + maxLen1);

        String s2 = ")()())";
        int maxLen2 = new LongestValidParentheses().longestValidParentheses(s2);
        System.out.println("最长有效括号子串的长度为：" + maxLen2);
    }
}
