package org.zyf.javabasic.letcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 将一个字符串拆分成若干回文子串，返回所有可能的拆分方案（Palindrome Partitioning）。
 * 回文子串是指正着读和倒着读都相同的连续字符序列。
 * 示例：
 * 输入: "aab"
 * 输出: [["a","a","b"],["aa","b"]]
 * @date 2020/4/2  22:20
 */
public class PalindromePartitionings {

    /**
     * 这个问题可以通过回溯算法来解决。
     * 回溯算法的思路是：
     * * 创建一个结果集 List<List<String>>，用于存储所有的拆分方案。
     * * 创建一个临时列表 List<String>，用于记录当前拆分方案。
     * * 从字符串的第一个字符开始，遍历字符串的所有子串。
     * * 对于每个子串，判断是否是回文子串。如果是回文子串，则将其加入临时列表，然后递归处理剩余子串。
     * * 递归处理剩余子串的过程中，重复步骤 3 和步骤 4，直到遍历完整个字符串。
     * * 如果临时列表中的字符串组成了一个有效的拆分方案，将其加入结果集。
     * * 回溯到上一层，尝试其他的拆分方案。
     * 这样，我们就可以得到所有可能的拆分方案。
     */
    public static List<List<String>> partition(String s) {
        // 结果集，用于存储所有的拆分方案
        List<List<String>> result = new ArrayList<>();
        // 临时列表，用于记录当前拆分方案
        List<String> current = new ArrayList<>();
        // 使用回溯算法得到所有可能的拆分方案
        backtrack(s, 0, current, result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        // 如果起始位置等于字符串的长度，说明已经遍历完整个字符串，将当前拆分方案加入结果集
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 从起始位置开始，遍历字符串的所有子串
        for (int end = start + 1; end <= s.length(); end++) {
            // 当前子串
            String substring = s.substring(start, end);

            // 判断当前子串是否是回文子串
            if (isPalindrome(substring)) {
                // 如果是回文子串，将其加入临时列表
                current.add(substring);
                // 递归处理剩余子串
                backtrack(s, end, current, result);
                // 回溯到上一层，尝试其他的拆分方案
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * 判断字符串是否是回文子串
     */
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
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
        String s = "aab";
        List<List<String>> result = partition(s);
        // 输出结果：All Palindromic Partitions: [[a, a, b], [aa, b]]
        System.out.println("All Palindromic Partitions: " + result);
    }

}
