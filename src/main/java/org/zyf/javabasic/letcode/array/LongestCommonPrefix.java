package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:输入: ["flower","flow","flight"] 输出: "fl"
 * 示例 2:输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * @date 2023/4/2  19:02
 */
public class LongestCommonPrefix {

    /**
     * 可以结合分治法的思想来求解最长公共前缀。
     * 分治法的基本思路是将问题分解为更小的子问题，然后将子问题的结果合并以得到原始问题的解。
     * 对于最长公共前缀问题，可以将字符串数组划分为两个子数组，
     * 分别求解每个子数组的最长公共前缀，
     * 然后将两个子问题的解合并，得到原始问题的解。
     * <p>
     * 这种分治法的时间复杂度也是O(m*n)，其中m是字符串数组中字符串的平均长度，n是字符串数组的长度。
     * 需要注意的是，对于最长公共前缀问题，没有明确的最优解法，而是根据具体情况和数据特点来选择适合的算法。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }

        int mid = (left + right) / 2;
        String leftPrefix = longestCommonPrefix(strs, left, mid);
        String rightPrefix = longestCommonPrefix(strs, mid + 1, right);
        return commonPrefix(leftPrefix, rightPrefix);
    }

    private String commonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int i = 0;
        while (i < length && str1.charAt(i) == str2.charAt(i)) {
            i++;
        }
        return str1.substring(0, i);
    }


    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs1));

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs2));

        String[] strs3 = {"dofggxcfd", "dofggxcfdracecar", "dofggxcfdcar"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs3));
    }

}
