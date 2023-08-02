package org.zyf.javabasic.letcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个字符串，统计其中每个字符出现的频次。
 * 示例：
 * 输入: "hello"
 * 输出: {'h': 1, 'e': 1, 'l': 2, 'o': 1}
 * @date 2021/2/2  23:57
 */
public class CharacterFrequency {

    /**
     * 最优解法是使用哈希表来统计字符串中每个字符出现的频次。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 创建一个哈希表 freqMap，用于记录每个字符及其对应的出现频次。
     * * 遍历字符串的每个字符，将字符作为键，出现的次数作为值，更新哈希表 freqMap。
     * * 最后，freqMap 中的键值对即为字符串中每个字符出现的频次。
     * 这种解法只需遍历一次字符串，通过哈希表来统计字符的频次，从而得到每个字符出现的频次。
     * 这种解法的空间复杂度是 O(k)，其中 k 是字符串中不同字符的个数。在最坏情况下，每个字符都不相同，哈希表的大小为字符串的长度，即空间复杂度为 O(n)。
     * 综上所述，使用哈希表来统计字符串中每个字符出现的频次是最优解法，具有高效和节省空间的特点。
     */
    public static Map<Character, Integer> getCharacterFrequency(String str) {
        // 创建哈希表 freqMap，用于记录每个字符及其对应的出现频次
        Map<Character, Integer> freqMap = new HashMap<>();

        // 遍历字符串的每个字符，更新哈希表 freqMap
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // 返回结果，即字符频次统计的哈希表 freqMap
        return freqMap;
    }

    public static void main(String[] args) {
        String str = "hello";
        Map<Character, Integer> frequencyMap = getCharacterFrequency(str);
        // 输出结果：Character Frequency for "hello": {h=1, e=1, l=2, o=1}
        System.out.println("Character Frequency for \"" + str + "\": " + frequencyMap);

    }
}
