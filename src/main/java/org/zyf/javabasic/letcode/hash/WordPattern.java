package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一种规律 pattern 和一个字符串 str，判断 str 是否遵循相同的规律。
 * 这里的遵循指完全匹配，
 * 例如，pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * @date 2023/4/9  20:44
 */
public class WordPattern {

    /**
     * 使用两个哈希表，一个用于存储 pattern 中每个字符对应的字符串，
     * 另一个用于存储字符串对应的 pattern 中的字符。
     * 在遍历 pattern 和 words 数组时，
     * 对于每个字符和字符串，都在两个哈希表中进行查找，如果存在冲突，则返回 false。
     * 如果没有出现冲突，则返回 true。
     */
    public boolean wordPattern(String pattern, String s) {
        /*将字符串 s 按照空格分隔为字符串数组*/
        String[] words = s.split(" ");
        /*如果字符串 pattern 和字符串数组 words 的长度不相等，则返回 false*/
        if (pattern.length() != words.length) {
            return false;
        }
        /*创建两个哈希表，一个用于存储 pattern 中每个字符对应的字符串，另一个用于存储字符串对应的 pattern 中的字符*/
        Map<Character, String> charToString = new HashMap<>();
        Map<String, Character> stringToChar = new HashMap<>();
        /*遍历 pattern 和 words 数组*/
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            /*如果哈希表 charToString 中已经包含了该字符，但是对应的字符串不是当前的 word，说明出现了冲突，返回 false*/
            if (charToString.containsKey(c) && !charToString.get(c).equals(word)) {
                return false;
            }
            /*如果哈希表 stringToChar 中已经包含了该字符串，但是对应的字符不是当前的 c，说明出现了冲突，返回 false*/
            if (stringToChar.containsKey(word) && stringToChar.get(word) != c) {
                return false;
            }
            /*将当前的字符和字符串加入两个哈希表中*/
            charToString.put(c, word);
            stringToChar.put(word, c);
        }
        /*如果没有出现冲突，则返回 true*/
        return true;
    }

    public static void main(String[] args) {
        String pattern1 = "abba", str1 = "dog cat cat dog";
        boolean res1 = new WordPattern().wordPattern(pattern1, str1);
        /*输出 true*/
        System.out.println(res1);

        String pattern2 = "abba", str2 = "dog cat cat fish";
        boolean res2 = new WordPattern().wordPattern(pattern2, str2);
        /*输出 false*/
        System.out.println(res2);

        String pattern3 = "aaaa", str3 = "dog cat cat dog";
        boolean res3 = new WordPattern().wordPattern(pattern3, str3);
        /*输出 false*/
        System.out.println(res3);

        String pattern4 = "abba", str4 = "dog dog dog dog";
        boolean res4 = new WordPattern().wordPattern(pattern4, str4);
        /*输出 false*/
        System.out.println(res4);
    }

}
