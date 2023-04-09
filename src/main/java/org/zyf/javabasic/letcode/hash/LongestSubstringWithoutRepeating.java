package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @date 2023/4/9  17:58
 */
public class LongestSubstringWithoutRepeating {

    /**
     * 具体实现思路如下：
     * 初始化 left 和 right 为 0，用一个哈希表来记录每个字符出现的位置。
     * 从 left 开始向右遍历字符串，遍历过程中维护一个当前无重复字符子串的哈希表，并记录当前子串的最大长度。
     * 如果当前字符 s[right] 在子串中出现过，则需要更新 left 的位置。具体来说，我们将 left 移动到 s[right] 上一次出现的位置的下一个位置。
     * 在遍历过程中更新最大长度，并将 s[right] 的位置记录到哈希表中。
     * 遍历完成后，返回最大长度即可。
     * 该方法的时间复杂度为 O(n)，空间复杂度为 O(min(m,n))，其中 m 表示字符集大小，n 表示字符串长度。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        /*最大长度*/
        int maxLen = 0;
        /*左指针*/
        int left = 0;
        /*哈希表，记录每个字符的位置*/
        Map<Character, Integer> map = new HashMap<>();

        /*右指针从 0 开始向右遍历字符串*/
        for (int right = 0; right < n; right++) {
            /*获取当前字符*/
            char c = s.charAt(right);
            /*如果当前字符在子串中出现过，则需要更新左指针的位置*/
            if (map.containsKey(c)) {
                /*左指针移动到当前字符上一次出现的位置的下一个位置*/
                left = Math.max(left, map.get(c) + 1);
            }
            /*记录当前字符的位置*/
            map.put(c, right);
            /*更新最大长度*/
            maxLen = Math.max(maxLen, right - left + 1);
        }
        /*返回最大长度*/
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int res = new LongestSubstringWithoutRepeating().lengthOfLongestSubstring(s);
            /*该代码输出的结果应该为3，因为在字符串 "abcabcbb" 中，
            无重复字符的最长子串为 "abc"，长度为3。*/
        System.out.println(res);
    }


}
