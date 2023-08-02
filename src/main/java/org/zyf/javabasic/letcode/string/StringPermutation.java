package org.zyf.javabasic.letcode.string;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定两个字符串 s1 和 s2，判断 s2 是否包含 s1 的排列组合。
 * 排列组合是指通过改变字符串中字符的顺序，得到新的字符串。例如，"abc" 的排列组合有 "abc"、"acb"、"bac"、"bca"、"cab" 和 "cba"。
 * 示例 1：
 * 输入: s1 = "abc", s2 = "eidbacooo"
 * 输出: true
 * 解释: s2 包含 s1 的排列组合 "bac"。
 * 示例 2：
 * 输入: s1 = "ab", s2 = "eidboaoo"
 * 输出: false
 * @date 2021/1/26  22:49
 */
public class StringPermutation {

    /**
     * 最优解法是使用滑动窗口和计数数组来解决字符串排列组合问题。这种解法的时间复杂度是 O(n)，其中 n 是字符串 s2 的长度。
     * 具体的最优解法步骤如下：
     * * 创建两个长度为 26 的计数数组 count1 和 count2，用于分别统计字符串 s1 和 s2 中字符出现的次数。
     * * 遍历字符串 s1，统计其中每个字符出现的次数，并更新计数数组 count1。
     * * 使用滑动窗口，遍历字符串 s2，首先统计窗口大小为 s1.length() 的字符个数，并更新计数数组 count2。
     * * 然后，将计数数组 count2 与 count1 进行比较，如果相等，则说明 s2 包含 s1 的排列组合。
     * * 移动滑动窗口，继续比较下一个窗口。
     * 这种解法只需遍历一次字符串 s2，通过滑动窗口和计数数组来统计字符出现的次数，从而判断 s2 是否包含 s1 的排列组合。
     * 这种解法的空间复杂度是 O(1)，因为计数数组的长度为固定的 26。
     * 综上所述，使用滑动窗口和计数数组解决字符串排列组合问题是最优解法，具有高效和节省空间的特点。
     */
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        // 计数数组用于统计字符串 s1 中字符出现的次数
        int[] count1 = new int[26];
        // 计数数组用于统计字符串 s2 中字符出现的次数
        int[] count2 = new int[26];

        // 统计字符串 s1 中每个字符出现的次数，更新计数数组 count1
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        // 使用滑动窗口，遍历字符串 s2
        for (int i = 0; i < m; i++) {
            // 窗口的大小为字符串 s1 的长度，统计窗口中字符出现的次数，更新计数数组 count2
            count2[s2.charAt(i) - 'a']++;

            // 当窗口的大小超过字符串 s1 的长度时，左边界向右移动一个位置，缩小窗口
            if (i >= n) {
                count2[s2.charAt(i - n) - 'a']--;
            }

            // 将计数数组 count2 与 count1 进行比较，如果相等，则说明 s2 包含 s1 的排列组合
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "eidbacooo";

        boolean result = checkInclusion(s1, s2);
        // 输出结果：Is "eidbacooo" contains permutation of "abc": true
        System.out.println("Is \"" + s2 + "\" contains permutation of \"" + s1 + "\": " + result);
    }
}
