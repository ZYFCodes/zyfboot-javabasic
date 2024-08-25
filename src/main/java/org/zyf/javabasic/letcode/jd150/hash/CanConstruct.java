package org.zyf.javabasic.letcode.jd150.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 赎金信
 * @author: zhangyanfeng
 * @create: 2024-08-25 11:56
 **/
public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 使用 HashMap 统计 magazine 中每个字符的频率
        Map<Character, Integer> magazineCount = new HashMap<>();

        // 遍历 magazine 统计每个字符的出现次数
        for (char c : magazine.toCharArray()) {
            magazineCount.put(c, magazineCount.getOrDefault(c, 0) + 1);
        }

        // 遍历 ransomNote 检查是否有足够的字符
        for (char c : ransomNote.toCharArray()) {
            // 如果 magazine 中没有字符 c，或字符 c 的数量不足
            if (!magazineCount.containsKey(c) || magazineCount.get(c) == 0) {
                return false; // 无法构造 ransomNote
            }
            // 使用一个字符 c，减少其在 magazine 中的频率
            magazineCount.put(c, magazineCount.get(c) - 1);
        }

        // 如果遍历完 ransomNote 中的所有字符后，没有问题，则返回 true
        return true;
    }
}
