package org.zyf.javabasic.letcode.hash;

import java.util.*;

/**
 * @author yanfengzhang
 * @description 给定一个字符串数组 strs，将由相同字母异位词组成的组合在一起，返回这些组合。可以按任意顺序返回答案。
 * 字母异位词指字母相同，但排列不同的字符串。
 * @date 2023/4/9  18:35
 */
public class GroupAnagrams {

    /**
     * 使用哈希表思路
     * 将每个字符串排序，相同排序后的字符串即为字母异位词，将其分到同一组中。
     * 具体实现时，可以使用哈希表来存储每个排序后的字符串对应的原始字符串集合。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            /*将字符串排序后作为键*/
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            /*将原字符串添加到对应的值列表中*/
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);
        }
        /*返回哈希表中所有的值列表*/
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = new GroupAnagrams().groupAnagrams(strs);
        /*[[eat, tea, ate], [tan, nat], [bat]]*/
        System.out.println(res);
    }

}
