package org.zyf.javabasic.letcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 重复的DNA序列是指长度为10的DNA序列中出现次数大于1的子序列。
 * 给定一个长度为 n 的字符串 s，查找其中所有长度为10的子序列中出现次数大于1的子序列并返回。
 * @date 2023/4/9  19:30
 */
public class RepeatedDNASequences {

    /**
     * 本题可以使用哈希表（HashMap）来解决。
     * 我们可以遍历字符串 s，每次取出长度为 10 的子串，判断该子串在哈希表中是否已经存在。
     * 如果已经存在，则将该子串加入结果集；否则，在哈希表中将该子串加入，并将对应的值设为 1。
     * <p>
     * 具体实现步骤如下：
     * 创建哈希表（HashMap），用于存储子串以及子串出现的次数。
     * 遍历字符串 s，每次取出长度为 10 的子串。
     * 判断子串是否在哈希表中已经存在，如果已经存在，则将该子串加入结果集；否则，在哈希表中将该子串加入，并将对应的值设为 1。
     * 重复步骤 2 和 3，直到遍历完整个字符串 s。
     */
    public List<String> findRepeatedDnaSequences(String s) {
        /*初始化哈希表和结果集*/
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        /*遍历字符串 s，每次取出长度为 10 的子串*/
        for (int i = 0; i <= s.length() - 10; i++) {
            String substr = s.substring(i, i + 10);
            /*判断子串是否在哈希表中已经存在*/
            if (map.containsKey(substr)) {
                /*如果已经存在，则将该子串加入结果集*/
                if (map.get(substr) == 1) {
                    res.add(substr);
                }
                /*将对应的值加 1*/
                map.put(substr, map.get(substr) + 1);
            } else {
                /*如果不存在，则将该子串加入哈希表，并将对应的值设为 1*/
                map.put(substr, 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        /*Output: [AAAAACCCCC, CCCCCAAAAA]*/
        System.out.println(new RepeatedDNASequences().findRepeatedDnaSequences(s1));

        String s2 = "AAAAAAAAAAAA";
        /*Output: [AAAAAAAAAA]*/
        System.out.println(new RepeatedDNASequences().findRepeatedDnaSequences(s2));

        String s3 = "AAAAAAAAAAA";
        /*Output: [AAAAAAAAAA]*/
        System.out.println(new RepeatedDNASequences().findRepeatedDnaSequences(s3));

        String s4 = "GAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGAGA";
        /*Output: [GAGAGAGAGA]*/
        System.out.println(new RepeatedDNASequences().findRepeatedDnaSequences(s4));
    }

}
