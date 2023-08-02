package org.zyf.javabasic.letcode.string;

import java.util.HashSet;

/**
 * @author yanfengzhang
 * @description 判断一个字符串是否能够通过重新排列成回文字符串（Palindrome Permutation）。
 * 回文字符串是指正着读和倒着读都相同的字符串。
 * 示例：
 * 输入: "code"
 * 输出: false
 * @date 2021/1/23  23:08
 */
public class PalindromePermutation {

    /**
     * 最优解法是使用一个 HashSet 来记录字符串中出现的字符，当遍历字符串时，将字符添加到 HashSet 中。
     * 如果字符已经在 HashSet 中存在，则说明这个字符出现了偶数次，我们可以将它从 HashSet 中删除。
     * 如果字符不在 HashSet 中，说明这个字符第一次出现，我们可以将它添加到 HashSet 中。
     * 最后，判断 HashSet 的大小，如果大小小于等于 1，则说明字符串中的字符都是出现偶数次或者只有一个字符出现奇数次（中心字符），返回 true，否则返回 false。
     * 这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。在一次遍历中，我们就可以完成字符出现次数的统计，因此是非常高效的。
     * 此外，这种解法只需要常数级别的额外空间来存储 HashSet，所以空间复杂度是 O(1)，也是非常优秀的。
     * 综上所述，使用 HashSet 来判断一个字符串是否能够通过重新排列成回文字符串是最优解法，具有高效和节省空间的特点。
     */
    public static boolean canPermutePalindrome(String s) {
        HashSet<Character> charSet = new HashSet<>();

        // 遍历字符串，统计每个字符的出现次数
        for (char c : s.toCharArray()) {
            // 如果字符已经在 HashSet 中存在，则从 HashSet 中删除
            if (charSet.contains(c)) {
                charSet.remove(c);
            } else {
                // 否则将字符添加到 HashSet 中
                charSet.add(c);
            }
        }

        // 判断 HashSet 的大小，如果大小小于等于 1，则返回 true，否则返回 false
        return charSet.size() <= 1;
    }

    public static void main(String[] args) {
        String str1 = "code";
        String str2 = "aab";
        String str3 = "carerac";

        // 输出结果：Can "code" be permuted to a palindrome? false
        System.out.println("Can \"" + str1 + "\" be permuted to a palindrome? " + canPermutePalindrome(str1));
        // 输出结果：Can "aab" be permuted to a palindrome? true
        System.out.println("Can \"" + str2 + "\" be permuted to a palindrome? " + canPermutePalindrome(str2));
        // 输出结果：Can "carerac" be permuted to a palindrome? true
        System.out.println("Can \"" + str3 + "\" be permuted to a palindrome? " + canPermutePalindrome(str3));
    }
}
