package org.zyf.javabasic.letcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 找到一个字符串中重复出现次数最多的字符及其出现次数。
 * 例如，对于字符串 "abcaabbcdd"，重复出现次数最多的字符是 'a' 和 'b'，它们分别出现了 3 次。
 * @date 2021/3/21  23:31
 */
public class MostRepeatedCharacter {

    /**
     * 最优解法是使用哈希表来统计字符串中每个字符出现的次数，然后找到出现次数最多的字符及其出现次数。
     * 这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 创建一个哈希表 charCount，用于记录每个字符出现的次数。
     * * 遍历字符串，对于每个字符，将其在哈希表中对应的计数值加一。
     * * 遍历哈希表，找到出现次数最多的字符及其出现次数。
     * 这种解法的核心思想是利用哈希表记录每个字符出现的次数，然后找到出现次数最多的字符及其出现次数。
     * 由于这个解法只需要遍历一次原始字符串，并且在遍历过程中只进行简单的哈希表操作，因此时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 另外，由于哈希表中存储的键值对数量与字符集大小有关，但在本问题中字符集是有限的，所以哈希表的空间复杂度是 O(1)。
     * 综上所述，使用哈希表来统计字符串中每个字符出现的次数，然后找到出现次数最多的字符及其出现次数是最优解法，具有高效的时间复杂度和固定的空间复杂度。
     */
    public static void findMostRepeatedCharacter(String s) {
        // 创建一个哈希表 charCount，用于记录每个字符出现的次数
        Map<Character, Integer> charCount = new HashMap<>();

        // 遍历字符串，对于每个字符，将其在哈希表中对应的计数值加一
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // 初始化重复出现次数最多的字符和出现次数
        char mostRepeatedChar = '\0';
        int maxCount = 0;

        // 遍历哈希表，找到出现次数最多的字符及其出现次数
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                mostRepeatedChar = c;
                maxCount = count;
            }
        }

        // 输出结果
        System.out.println("Most repeated character: " + mostRepeatedChar);
        System.out.println("Occurrences: " + maxCount);
    }

    public static void main(String[] args) {
        String s = "abcaabbcdd";
        // 输出结果：Most repeated character: a, Occurrences: 3
        findMostRepeatedCharacter(s);
    }
}
