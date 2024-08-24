package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 分割回文串（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:43
 **/
public class PartitionSolution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        backtrack(s, 0, currentList, result);
        return result;
    }

    private void backtrack(String s, int start, List<String> currentList, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (isPalindrome(substring)) {
                currentList.add(substring);
                backtrack(s, end, currentList, result);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
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
        PartitionSolution solution = new PartitionSolution();

        // 示例 1
        String s1 = "aab";
        System.out.println(solution.partition(s1));
        // 输出: [["a","a","b"],["aa","b"]]

        // 示例 2
        String s2 = "a";
        System.out.println(solution.partition(s2));
        // 输出: [["a"]]
    }
}
