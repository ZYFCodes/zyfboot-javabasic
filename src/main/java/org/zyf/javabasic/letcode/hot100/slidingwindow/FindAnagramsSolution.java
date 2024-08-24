package org.zyf.javabasic.letcode.hot100.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 找到字符串中所有字母异位词
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:26
 **/
public class FindAnagramsSolution {
    public List<Integer> findAnagrams(String s, String p) {
        // 结果列表
        List<Integer> result = new ArrayList<>();

        // 特殊情况处理
        if (s.length() < p.length()) {
            return result;
        }

        // 统计字符串 p 中每个字符的频率
        int[] pFreq = new int[26];
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }

        // 滑动窗口的字符频率
        int[] sFreq = new int[26];

        // 初始化滑动窗口
        int left = 0, right = 0;

        while (right < s.length()) {
            // 将当前字符加入窗口的频率统计
            sFreq[s.charAt(right) - 'a']++;

            // 当窗口大小达到 p 的长度时，开始检查
            if (right - left + 1 == p.length()) {
                // 检查当前窗口是否为异位词
                if (matches(sFreq, pFreq)) {
                    result.add(left);
                }

                // 移动左指针，缩小窗口，更新频率表
                sFreq[s.charAt(left) - 'a']--;
                left++;
            }

            // 右指针继续向右扩展窗口
            right++;
        }

        return result;
    }

    // 辅助函数：检查两个频率数组是否相同
    private boolean matches(int[] sFreq, int[] pFreq) {
        for (int i = 0; i < 26; i++) {
            if (sFreq[i] != pFreq[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FindAnagramsSolution solution = new FindAnagramsSolution();

        // 测试用例 1
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(solution.findAnagrams(s1, p1)); // 输出: [0, 6]

        // 测试用例 2
        String s2 = "abab";
        String p2 = "ab";
        System.out.println(solution.findAnagrams(s2, p2)); // 输出: [0, 1, 2]
    }
}
