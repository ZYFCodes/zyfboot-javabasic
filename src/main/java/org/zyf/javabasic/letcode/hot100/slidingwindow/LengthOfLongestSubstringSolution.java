package org.zyf.javabasic.letcode.hot100.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 无重复字符的最长子串   ​
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:18
 **/
public class LengthOfLongestSubstringSolution {
    public int lengthOfLongestSubstring(String s) {
        // 使用哈希集来存储当前窗口内的字符
        Set<Character> set = new HashSet<>();

        // 初始化左右指针和最大长度
        int left = 0, right = 0;
        int maxLength = 0;

        // 开始滑动窗口遍历字符串
        while (right < s.length()) {
            // 如果当前字符不在哈希集中，说明没有重复，加入哈希集并移动右指针
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                // 更新最大长度
                maxLength = Math.max(maxLength, right - left);
            } else {
                // 如果当前字符已经在哈希集中，说明有重复，移除左指针的字符并移动左指针
                set.remove(s.charAt(left));
                left++;
            }
        }

        // 返回记录的最大长度
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstringSolution solution = new LengthOfLongestSubstringSolution();

        // 测试用例 1
        String s1 = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring(s1)); // 输出: 3

        // 测试用例 2
        String s2 = "bbbbb";
        System.out.println(solution.lengthOfLongestSubstring(s2)); // 输出: 1

        // 测试用例 3
        String s3 = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s3)); // 输出: 3
    }
}
