package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定一个只包括括号的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 示例 1：输入：s = "()"            输出：true
 * 示例 2：输入：s = "()[]{}"        输出：true
 * 示例 3：输入：s = "(]"            输出：false
 * 示例 4：输入：s = "([)]"          输出：false
 * 示例 5：输入：s = "{[]}"          输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * @date 2023/4/8  22:17
 */
public class ValidStr {

    /**
     * 最优解是使用栈来解决。
     * <p>
     * 我们可以遍历给定字符串，
     * 对于每个左括号，将其对应的右括号压入栈中，
     * 当遇到右括号时，弹出栈顶元素并判断是否与该右括号相同，
     * 若相同则继续遍历，若不相同则该字符串不合法。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();
                if ((top == '(' && c == ')')
                        || (top == '[' && c == ']')
                        || (top == '{' && c == '}')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        /*验证有效的括号代码*/
        String s = "()[]{}";
        boolean result = new ValidStr().isValid(s);
        System.out.println(result);

        s = "(]";
        result = new ValidStr().isValid(s);
        System.out.println(result);

        s = "([)]";
        result = new ValidStr().isValid(s);
        System.out.println(result);

        s = "{[]}";
        result = new ValidStr().isValid(s);
        System.out.println(result);
    }
}
