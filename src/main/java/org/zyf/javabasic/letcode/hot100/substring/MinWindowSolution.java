package org.zyf.javabasic.letcode.hot100.substring;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 最小覆盖子串
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:55
 **/
public class MinWindowSolution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // 记录t中每个字符的出现次数
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }

        // 定义窗口计数器
        Map<Character, Integer> windowCount = new HashMap<>();
        int left = 0, right = 0, matchCount = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            char cRight = s.charAt(right);
            if (targetCount.containsKey(cRight)) {
                windowCount.put(cRight, windowCount.getOrDefault(cRight, 0) + 1);
                if (windowCount.get(cRight).intValue() == targetCount.get(cRight).intValue()) {
                    matchCount++;
                }
            }
            right++;

            // 当窗口包含所有t中的字符后，开始收缩窗口
            while (matchCount == targetCount.size()) {
                // 更新最小窗口
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }

                char cLeft = s.charAt(left);
                if (targetCount.containsKey(cLeft)) {
                    windowCount.put(cLeft, windowCount.get(cLeft) - 1);
                    if (windowCount.get(cLeft) < targetCount.get(cLeft)) {
                        matchCount--;
                    }
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinWindowSolution solution = new MinWindowSolution();
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println(solution.minWindow(s1, t1));  // 输出: "BANC"

        String s2 = "a";
        String t2 = "a";
        System.out.println(solution.minWindow(s2, t2));  // 输出: "a"

        String s3 = "a";
        String t3 = "aa";
        System.out.println(solution.minWindow(s3, t3));  // 输出: ""
    }
}
