package org.zyf.javabasic.letcode.featured75.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 定长子串中元音的最大数目
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:39
 **/
public class MaxVowels {
    public int maxVowels(String s, int k) {
        // 元音字母集合
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        // 计算初始窗口中元音字母的数量
        int maxVowelsCount = 0;
        int currentVowelsCount = 0;

        // 初始化窗口的元音字母数量
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                currentVowelsCount++;
            }
        }

        // 设置初始的最大元音字母数量
        maxVowelsCount = currentVowelsCount;

        // 滑动窗口，更新元音字母数量
        for (int i = k; i < s.length(); i++) {
            // 移出窗口左边的字符
            if (vowels.contains(s.charAt(i - k))) {
                currentVowelsCount--;
            }
            // 添加窗口右边的字符
            if (vowels.contains(s.charAt(i))) {
                currentVowelsCount++;
            }
            // 更新最大元音字母数量
            maxVowelsCount = Math.max(maxVowelsCount, currentVowelsCount);
        }

        return maxVowelsCount;
    }

    public static void main(String[] args) {
        MaxVowels solution = new MaxVowels();

        // 测试用例 1
        String s1 = "abciiidef";
        int k1 = 3;
        System.out.println("Test Case 1: " + (solution.maxVowels(s1, k1) == 3 ? "Passed" : "Failed"));

        // 测试用例 2
        String s2 = "aeiou";
        int k2 = 2;
        System.out.println("Test Case 2: " + (solution.maxVowels(s2, k2) == 2 ? "Passed" : "Failed"));

        // 测试用例 3
        String s3 = "leetcode";
        int k3 = 3;
        System.out.println("Test Case 3: " + (solution.maxVowels(s3, k3) == 2 ? "Passed" : "Failed"));

        // 测试用例 4
        String s4 = "rhythms";
        int k4 = 4;
        System.out.println("Test Case 4: " + (solution.maxVowels(s4, k4) == 0 ? "Passed" : "Failed"));

        // 测试用例 5
        String s5 = "tryhard";
        int k5 = 4;
        System.out.println("Test Case 5: " + (solution.maxVowels(s5, k5) == 1 ? "Passed" : "Failed"));
    }
}
