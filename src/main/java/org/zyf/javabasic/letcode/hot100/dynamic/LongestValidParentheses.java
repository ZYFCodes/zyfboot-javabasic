package org.zyf.javabasic.letcode.hot100.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 最长有效括号（困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:34
 **/
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLength = 0;

        // 从左到右遍历
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                left = right = 0; // 重置计数器
            }
        }

        // 从右到左遍历
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0; // 重置计数器
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestValidParentheses lvp = new LongestValidParentheses();

        // 测试用例1
        String s1 = "(()";
        System.out.println("测试用例1结果: " + lvp.longestValidParentheses(s1)); // 输出：2

        // 测试用例2
        String s2 = ")()())";
        System.out.println("测试用例2结果: " + lvp.longestValidParentheses(s2)); // 输出：4

        // 测试用例3
        String s3 = "";
        System.out.println("测试用例3结果: " + lvp.longestValidParentheses(s3)); // 输出：0
    }
}
