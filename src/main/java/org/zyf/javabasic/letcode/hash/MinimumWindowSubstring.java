package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * @date 2023/4/9  18:21
 */
public class MinimumWindowSubstring {

    /**
     * 1.创建两个哈希表：need 和 window，其中 need 用于存储 t 中包含的字符以及每个字符的个数，window 用于存储当前窗口中包含的字符以及每个字符的个数。
     * 2.使用 left 和 right 两个指针表示窗口的左右边界。right 向右移动，直到窗口包含了 t 中所有的字符。此时记录窗口的大小和位置。
     * 3.移动 left 指针，尝试缩小窗口的大小，直到窗口中的字符串不再完全包含 t 中的所有字符。在每次移动 left 指针时，需要将 window 中对应字符的个数减去 1，并更新窗口的大小和位置。
     * 4.重复步骤 2 和 3，直到 right 指针到达 s 的末尾。
     */
    public String minWindow(String s, String t) {
        /*need 哈希表用于存储 t 中包含的字符以及每个字符的个数*/
        Map<Character, Integer> need = new HashMap<>();
        /*window 哈希表用于存储当前窗口中包含的字符以及每个字符的个数*/
        Map<Character, Integer> window = new HashMap<>();
        /*初始化 need 哈希表*/
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        /*左右指针*/
        int left = 0, right = 0;
        /*valid 表示当前窗口中已经包含了 t 中的字符个数*/
        int valid = 0;
        /*记录最小覆盖子串的起始位置和长度*/
        int start = 0, len = Integer.MAX_VALUE;
        /*当右指针未到达 s 的末尾时循环*/
        while (right < s.length()) {
            /*取出当前窗口的右边界字符*/
            char c = s.charAt(right);
            /*右指针右移*/
            right++;
            /*如果 c 是 t 中的字符*/
            if (need.containsKey(c)) {
                /*将 c 加入到 window 哈希表中*/
                window.put(c, window.getOrDefault(c, 0) + 1);
                /*如果 window 中 c 的数量等于 need 中 c 的数量*/
                if (window.get(c).equals(need.get(c))) {
                    /*更新 valid 值*/
                    valid++;
                }
            }
            /*当前窗口已经包含了 t 中的所有字符时*/
            while (valid == need.size()) {
                /*更新最小覆盖子串的起始位置和长度*/
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                /*取出当前窗口的左边界字符*/
                char d = s.charAt(left);
                /*左指针右移*/
                left++;
                /*如果 d 是 t 中的字符*/
                if (need.containsKey(d)) {
                    /*如果 window 中 d 的数量等于 need 中 d 的数量*/
                    if (window.get(d).equals(need.get(d))) {
                        /*更新 valid 值*/
                        valid--;
                    }
                    /*将 d 从 window 中减少一个*/
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        /*返回最小覆盖子串*/
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = new MinimumWindowSubstring().minWindow(s, t);
        /*输出 "BANC"*/
        System.out.println(res);
    }

}
