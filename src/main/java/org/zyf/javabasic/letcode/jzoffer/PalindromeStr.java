package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给定一个字符串，请编写一个函数判断它是否是一个回文串。
 * 回文串是指正着读和倒着读都一样的字符串，忽略字母的大小写。
 * @date 2023/6/15  23:37
 */
public class PalindromeStr {
    /**
     * 判断一个字符串是否是回文串的一种常见方法是使用双指针。
     * 定义两个指针，一个指向字符串的开头，另一个指向字符串的末尾。
     * 然后，分别向中间移动指针，并比较两个指针所指向的字符是否相同。具体步骤如下：
     * 1.	初始化两个指针，一个指向字符串的开头，即索引为 0，另一个指向字符串的末尾，即索引为字符串长度减一。
     * 2.	循环遍历字符串，直到两个指针相遇为止。
     * 3.	在循环中，比较两个指针所指向的字符是否相同。如果相同，则继续向中间移动指针；如果不同，则说明字符串不是回文串，返回 false。
     * 4.	如果循环结束时没有发现不同字符，则说明字符串是回文串，返回 true。
     * <p>
     * 需要注意的是，在比较字符时应忽略字母的大小写。可以使用字符转换函数将字符统一转换为小写或大写，然后进行比较。
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char c1 = Character.toLowerCase(s.charAt(left));
            char c2 = Character.toLowerCase(s.charAt(right));

            if (!Character.isLetterOrDigit(c1)) {
                left++;
            } else if (!Character.isLetterOrDigit(c2)) {
                right--;
            } else if (c1 != c2) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeStr solution = new PalindromeStr();
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";

        boolean isPalindrome1 = solution.isPalindrome(s1);
        boolean isPalindrome2 = solution.isPalindrome(s2);

        // 输出: true
        System.out.println("字符串 \"" + s1 + "\" 是否是回文串: " + isPalindrome1);
        // 输出: false
        System.out.println("字符串 \"" + s2 + "\" 是否是回文串: " + isPalindrome2);
    }
}
