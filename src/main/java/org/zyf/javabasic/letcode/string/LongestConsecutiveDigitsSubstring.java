package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 找到一个字符串中最长的连续数字字符的子串。
 * 例如，对于字符串 "ab123cd456ef789"，最长的连续数字字符子串是 "789"，它的长度是 3。
 * @date 2021/3/15  23:35
 */
public class LongestConsecutiveDigitsSubstring {

    /**
     * 最优解法是使用双指针的方法来找到一个字符串中最长的连续数字字符子串。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 定义两个指针 start 和 end，分别表示当前连续数字字符子串的起始位置和结束位置。
     * * 定义一个变量 maxLength，用于记录最长的连续数字字符子串的长度。
     * * 遍历字符串，当遇到数字字符时，将 end 指针向后移动，直到不是数字字符为止，记录当前连续数字字符子串的长度。
     * * 更新 maxLength 和对应的起始位置 start，以保持记录最长的连续数字字符子串的信息。
     * * 继续遍历字符串直到结束。
     * 这种解法的核心思想是利用双指针来定位每个连续数字字符子串的起始位置和结束位置，然后计算其长度，并更新最长的连续数字字符子串的信息。
     * 由于这个解法只需要遍历一次字符串，并且在遍历过程中只进行简单的双指针操作，因此时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 另外，除了存储原始字符串和记录最长子串的信息外，这个解法只需要使用常数大小的额外空间，因此空间复杂度是 O(1)。
     * 综上所述，使用双指针的方法来找到一个字符串中最长的连续数字字符子串是最优解法，具有高效的时间复杂度和节省空间的特点。
     */
    public static String findLongestConsecutiveDigitsSubstring(String s) {
        // 定义两个指针 start 和 end，分别表示当前连续数字字符子串的起始位置和结束位置
        int start = 0;
        int end = 0;
        // 定义变量 maxLength，记录最长的连续数字字符子串的长度
        int maxLength = 0;

        // 遍历字符串，用指针 end 定位每个连续数字字符子串的结束位置
        while (end < s.length()) {
            // 如果当前字符是数字字符，指针 end 向后移动，直到不是数字字符为止
            while (end < s.length() && Character.isDigit(s.charAt(end))) {
                end++;
            }

            // 计算当前连续数字字符子串的长度
            int currentLength = end - start;

            // 更新最长的连续数字字符子串的信息
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }

            // 将指针 start 更新为下一个连续数字字符子串的起始位置
            start = end + 1;
            // 将指针 end 移动到下一个字符的位置
            end++;
        }

        // 根据最长的连续数字字符子串的长度和起始位置，得到结果子串
        String result = s.substring(start - 1, start + maxLength - 1);

        return result;
    }

    public static void main(String[] args) {
        String s = "ab123cd456ef789";
        String longestSubstring = findLongestConsecutiveDigitsSubstring(s);
        // 输出结果：Longest consecutive digits substring: 789
        System.out.println("Longest consecutive digits substring: " + longestSubstring);
    }
}
