package org.zyf.javabasic.letcode.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * @date 2023/4/9  19:01
 */
public class FindAllAnagrams {

    /**
     * 该问题可以通过哈希表来解决。具体步骤如下：
     * <p>
     * 创建一个哈希表，用于存储每个字符在目标字符串 p 中出现的次数。
     * 初始化窗口指针 left 和 right，分别指向字符串 s 的开头。
     * 进入循环，不断移动右指针，每次将右指针指向的字符在哈希表中的计数器减 1。
     * 当发现某个字符在哈希表中的计数器等于 0 时，说明该字符已经在窗口中出现了足够多的次数，可以将左指针向右移动，缩小窗口的大小。
     * 如果发现当前窗口的大小等于 p 的长度，说明找到了一个字母异位词，将左指针加入结果集中。
     * 重复步骤 3 到 5，直到右指针移动到字符串 s 的末尾。
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }

        /*创建哈希表，记录 p 中每个字符出现的次数*/
        int[] hash = new int[26];
        for (char c : p.toCharArray()) {
            hash[c - 'a']++;
        }

        /*初始化窗口指针*/
        int left = 0;
        int right = 0;

        /*记录窗口中未匹配的字符数量*/
        int count = p.length();

        /*进入循环，不断移动右指针*/
        while (right < s.length()) {
            char c = s.charAt(right);

            /*如果 s 中的字符在 p 中出现过，将 hash 值减 1，并且 count 减 1*/
            if (hash[c - 'a'] > 0) {
                count--;
            }
            hash[c - 'a']--;

            /*如果窗口大小等于 p 的长度，说明找到了一个字母异位词*/
            if (right - left + 1 == p.length()) {
                if (count == 0) {
                    result.add(left);
                }

                /*将左指针向右移动，缩小窗口的大小*/
                char leftChar = s.charAt(left);
                if (hash[leftChar - 'a'] >= 0) {
                    count++;
                }
                hash[leftChar - 'a']++;
                left++;
            }

            /*右指针向右移动*/
            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = new FindAllAnagrams().findAnagrams(s, p);
        /*expects [0, 6]*/
        System.out.println(result);
    }

}
