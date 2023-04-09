package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个字符串 s ，找到 s 中第一个不重复的字符，并返回它的索引。
 * 如果不存在，则返回 -1。
 * @date 2023/4/9  21:00
 */
public class FirstUniqueCharacter {
    /**
     * 使用哈希表（HashMap）来解决。
     * 我们可以先遍历一次字符串 s，统计每个字符出现的次数，并将其存储在哈希表中。
     * 然后再遍历一次字符串 s，找到第一个在哈希表中出现次数为 1 的字符，
     * 返回该字符在字符串 s 中的位置。
     */
    public int firstUniqChar(String s) {
        /*创建哈希表，用于存储每个字符出现的次数*/
        Map<Character, Integer> map = new HashMap<>();
        /*遍历字符串，统计每个字符出现的次数*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            /*如果哈希表中已经有该字符，则将其对应的值加 1*/
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            /*否则，在哈希表中添加该字符，并将其对应的值设为 1*/
            else {
                map.put(c, 1);
            }
        }
        /*再次遍历字符串，找到第一个出现次数为 1 的字符*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }
        /*如果所有字符都不止出现一次，则返回 -1*/
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        /*xpected output: 0*/
        System.out.println(new FirstUniqueCharacter().firstUniqChar(s1));

        String s2 = "loveleetcode";
        /*xpected output: 2*/
        System.out.println(new FirstUniqueCharacter().firstUniqChar(s2));

        String s3 = "abcabc";
        /*xpected output: -1*/
        System.out.println(new FirstUniqueCharacter().firstUniqChar(s3));
    }

}
