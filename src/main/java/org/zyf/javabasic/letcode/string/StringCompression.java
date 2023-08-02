package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 编写一个算法来压缩字符串，将连续出现的字符替换为字符和出现次数的组合。如果压缩后的字符串没有变短，则返回原始字符串。
 * 示例 1：
 * 输入: "aabcccccaaa"
 * 输出: "a2b1c5a3"
 * 示例 2：
 * 输入: "abcd"
 * 输出: "abcd"
 * 解释：压缩后的字符串 "a2b1c5a3" 比原始字符串 "aabcccccaaa" 更短，返回压缩后的字符串。
 * @date 2020/5/2  23:25
 */
public class StringCompression {

    /**
     * 最优解法是使用双指针来遍历字符串，同时在遍历过程中进行压缩字符的操作。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 创建一个 StringBuilder，用于存储压缩后的字符。
     * * 使用两个指针 i 和 j 分别表示当前遍历的字符位置和连续出现字符的起始位置，初始值为 0。
     * * 在循环中，让指针 j 向右移动直到找到不同的字符为止，此时 j 指向连续出现字符的结束位置。
     * * 计算连续出现字符的个数 count，将字符和个数的组合添加到 StringBuilder 中。
     * * 更新指针 i 为 j + 1，准备处理下一个字符。
     * * 最后，如果压缩后的字符串没有变短，则返回原始字符串，否则返回压缩后的字符串。
     * 这种解法只需遍历一次字符串，通过双指针来统计连续出现字符的个数，并将字符和个数的组合添加到 StringBuilder 中，最终得到压缩后的字符串。
     * 这种解法的空间复杂度是 O(1)，因为只使用了常数级别的额外空间。
     * 综上所述，使用双指针遍历字符串进行压缩字符的操作是最优解法，具有高效和节省空间的特点。
     */
    public static String compressString(String s) {
        int n = s.length();
        StringBuilder compressedString = new StringBuilder();

        // 指针 i 表示当前遍历的字符位置
        int i = 0;
        // 指针 j 表示连续出现字符的起始位置
        int j = 0;

        while (i < n) {
            // 让指针 j 向右移动直到找到不同的字符为止
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            // 计算连续出现字符的个数
            int count = j - i;
            compressedString.append(s.charAt(i));
            compressedString.append(count);

            // 更新指针 i 为 j，准备处理下一个字符
            i = j;
        }

        // 如果压缩后的字符串没有变短，则返回原始字符串，否则返回压缩后的字符串
        return compressedString.length() >= n ? s : compressedString.toString();
    }

    public static void main(String[] args) {
        String s1 = "aabcccccaaa";
        String s2 = "abcd";

        // 输出结果：Compressed String for "aabcccccaaa": a2b1c5a3
        System.out.println("Compressed String for \"" + s1 + "\": " + compressString(s1));
        // 输出结果：Compressed String for "abcd": abcd
        System.out.println("Compressed String for \"" + s2 + "\": " + compressString(s2));
    }
}
