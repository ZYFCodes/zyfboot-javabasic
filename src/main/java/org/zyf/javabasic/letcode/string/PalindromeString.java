package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 编写一个算法来判断一个给定的字符串是否是回文字符串（Palindrome），即正着读和倒着读都相同。
 * 示例：
 * 输入: "racecar"
 * 输出: true
 * @date 2021/1/21  23:57
 */
public class PalindromeString {

    /**
     * 对于判断一个字符串是否是回文字符串的问题，最优解法是使用双指针法。
     * 双指针法的思想是，使用两个指针分别从字符串的开头和结尾开始，向中间移动，同时比较对应位置的字符是否相同。
     * 如果对应字符都相同，继续移动指针，直到两个指针相遇或交叉，说明整个字符串都是回文字符串。
     * 这种解法的时间复杂度是 O(n/2) = O(n)，其中 n 是字符串的长度。由于只需要进行一次遍历，因此时间复杂度是线性的，非常高效。
     * 此外，这种解法不需要额外的空间，只需要常数级别的额外空间来存储两个指针，所以空间复杂度是 O(1)，也是非常优秀的。
     * 因此，使用双指针法是判断一个字符串是否是回文字符串的最优解法，具有高效和节省空间的特点。
     */
    public static boolean isPalindrome(String s) {
        // 将字符串转换为全小写，去除非字母和数字字符
        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        // 双指针法，一个从字符串开头，一个从结尾
        int left = 0;
        int right = s.length() - 1;

        // 比较对应位置的字符是否相同，直到两个指针相遇或交叉
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "racecar";
        String str2 = "hello";
        String str3 = "A man, a plan, a canal, Panama!";

        // 输出结果：Is "racecar" a palindrome? true
        System.out.println("Is \"" + str1 + "\" a palindrome? " + isPalindrome(str1));
        // 输出结果：Is "hello" a palindrome? false
        System.out.println("Is \"" + str2 + "\" a palindrome? " + isPalindrome(str2));
        // 输出结果：Is "A man, a plan, a canal, Panama!" a palindrome? true
        System.out.println("Is \"" + str3 + "\" a palindrome? " + isPalindrome(str3));
    }

}
