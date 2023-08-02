package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 找到一个字符串的最长回文前缀。
 * 回文前缀是指从字符串的开头开始，能够形成回文字符串的最长子串。
 * 例如，对于字符串 "abacdc"，它的最长回文前缀是 "aba"。
 * @date 2021/3/18  23:43
 */
public class LongestPalindromePrefix {

    /**
     * 最优解法是使用中心扩展法来找到一个字符串的最长回文前缀。这种解法的时间复杂度是 O(n^2)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 定义一个变量 maxPrefix，用于记录最长的回文前缀。
     * * 遍历字符串的每个字符，以当前字符为中心，向两边扩展检查是否是回文子串。具体扩展方式包括回文子串长度是奇数或偶数的两种情况。
     * * 更新 maxPrefix 为找到的最长回文前缀。
     * 这种解法的核心思想是从字符串的每个字符开始，以该字符为中心，向两边扩展检查是否形成回文子串。由于回文子串的长度可能是奇数或偶数，因此需要分别考虑两种情况。
     * 由于这个解法需要遍历字符串的每个字符，并在每个字符处检查回文子串的扩展，因此时间复杂度是 O(n^2)，其中 n 是字符串的长度。
     * 另外，除了存储原始字符串和记录最长回文前缀外，这个解法只需要使用常数大小的额外空间，因此空间复杂度是 O(1)。
     * 综上所述，使用中心扩展法来找到一个字符串的最长回文前缀是最优解法，具有高效的时间复杂度和节省空间的特点。
     */
    public static String findLongestPalindromePrefix(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // 定义一个变量 maxPrefix，用于记录最长的回文前缀
        String maxPrefix = "";

        // 遍历字符串的每个字符，以当前字符为中心，向两边扩展检查是否是回文子串
        for (int i = 0; i < s.length(); i++) {
            // 回文子串长度是奇数的情况
            String oddLengthPalindrome = expandPalindrome(s, i, i);
            // 回文子串长度是偶数的情况
            String evenLengthPalindrome = expandPalindrome(s, i, i + 1);

            // 更新 maxPrefix 为找到的最长回文前缀
            if (oddLengthPalindrome.length() > maxPrefix.length()) {
                maxPrefix = oddLengthPalindrome;
            }
            if (evenLengthPalindrome.length() > maxPrefix.length()) {
                maxPrefix = evenLengthPalindrome;
            }
        }

        return maxPrefix;
    }

    /**
     * 从给定的中心位置向两边扩展，找到最长的回文子串
     */
    private static String expandPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回找到的回文子串
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        String s = "abacdc";
        String longestPalindromePrefix = findLongestPalindromePrefix(s);
        // 输出结果：Longest palindrome prefix: aba
        System.out.println("Longest palindrome prefix: " + longestPalindromePrefix);
    }
}
