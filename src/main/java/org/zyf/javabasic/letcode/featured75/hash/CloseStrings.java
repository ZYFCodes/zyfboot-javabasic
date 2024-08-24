package org.zyf.javabasic.letcode.featured75.hash;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 确定两个字符串是否接近
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:02
 **/
public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        // 如果长度不同，直接返回 false
        if (word1.length() != word2.length()) {
            return false;
        }

        // 初始化两个数组用于记录两个字符串中每个字符的频率
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // 遍历 word1 并记录频率
        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }

        // 遍历 word2 并记录频率
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }

        // 检查两个字符串的字符集合是否相同
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] > 0) || (freq2[i] == 0 && freq1[i] > 0)) {
                return false; // 字符集合不同，返回 false
            }
        }

        // 对频率数组进行排序
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        // 如果频率分布不同，返回 false
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        // 如果通过以上检查，则返回 true
        return true;
    }

    public static void main(String[] args) {
        CloseStrings solution = new CloseStrings();

        // 测试用例 1
        String word1 = "abc";
        String word2 = "bca";
        System.out.println("Test Case 1: " + (solution.closeStrings(word1, word2) ? "Passed" : "Failed")); // 应输出 true

        // 测试用例 2
        word1 = "a";
        word2 = "aa";
        System.out.println("Test Case 2: " + (solution.closeStrings(word1, word2) ? "Passed" : "Failed")); // 应输出 false

        // 测试用例 3
        word1 = "cabbba";
        word2 = "abbccc";
        System.out.println("Test Case 3: " + (solution.closeStrings(word1, word2) ? "Passed" : "Failed")); // 应输出 true

        // 测试用例 4
        word1 = "uio";
        word2 = "oiu";
        System.out.println("Test Case 4: " + (solution.closeStrings(word1, word2) ? "Passed" : "Failed")); // 应输出 true

        // 测试用例 5
        word1 = "abcd";
        word2 = "dcba";
        System.out.println("Test Case 5: " + (solution.closeStrings(word1, word2) ? "Passed" : "Failed")); // 应输出 true
    }
}
