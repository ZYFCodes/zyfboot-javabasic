package org.zyf.javabasic.letcode.hot100.hash;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 字母异位词
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:26
 **/
public class GroupAnagramsSolution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 创建一个哈希表，键为排序后的字符串，值为包含该异位词的列表
        Map<String, List<String>> anagramMap = new HashMap<>();

        // 遍历字符串数组
        for (String str : strs) {
            // 将字符串的字符排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // 将排序后的字符串作为键
            if (!anagramMap.containsKey(sortedStr)) {
                // 如果哈希表中不存在这个键，创建一个新的列表
                anagramMap.put(sortedStr, new ArrayList<>());
            }
            // 将原始字符串加入该键对应的列表中
            anagramMap.get(sortedStr).add(str);
        }

        // 返回哈希表中所有的值，即为字母异位词的分组
        return new ArrayList<>(anagramMap.values());
    }

    public static void main(String[] args) {
        GroupAnagramsSolution solution = new GroupAnagramsSolution();

        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs1));
        // 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

        String[] strs2 = {""};
        System.out.println(solution.groupAnagrams(strs2));
        // 输出: [[""]]

        String[] strs3 = {"a"};
        System.out.println(solution.groupAnagrams(strs3));
        // 输出: [["a"]]
    }
}
