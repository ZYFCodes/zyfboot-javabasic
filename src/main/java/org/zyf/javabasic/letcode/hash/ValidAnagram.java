package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * @date 2023/4/9  18:46
 */
public class ValidAnagram {
    /**
     * 这道题可以使用哈希表来解决。具体实现步骤如下：
     * <p>
     * 创建一个哈希表，用于记录每个字符出现的次数。
     * 遍历字符串 s，将其中的每个字符及其出现次数加入哈希表。
     * 遍历字符串 t，对于其中的每个字符，在哈希表中将其出现次数减一。
     * 如果在哈希表中对应字符的出现次数已经为 0，说明字符串 t 中出现了一个字符串 s 中不存在的字符，直接返回 false。
     * 如果遍历字符串 t 后没有出现不相等的字符，返回 true
     */
    public boolean isAnagram(String s, String t) {
        /*如果两个字符串的长度不相等，直接返回 false*/
        if (s.length() != t.length()) {
            return false;
        }
        /*创建一个哈希表，用于记录每个字符出现的次数*/
        Map<Character, Integer> map = new HashMap<>();
        /*遍历字符串 s，将其中的每个字符及其出现次数加入哈希表*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        /*遍历字符串 t，对于其中的每个字符，在哈希表中将其出现次数减一*/
        /*如果在哈希表中对应字符的出现次数已经为 0，说明字符串 t 中出现了一个字符串 s 中不存在的字符，直接返回 false*/
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            if (count == 0) {
                return false;
            }
            map.put(c, count - 1);
        }
        /*如果遍历字符串 t 后没有出现不相等的字符，返回 true*/
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        /*输出 true*/
        System.out.println(new ValidAnagram().isAnagram(s, t));

        String s2 = "rat";
        String t2 = "car";
        /*输出 false*/
        System.out.println(new ValidAnagram().isAnagram(s2, t2));
    }

}
