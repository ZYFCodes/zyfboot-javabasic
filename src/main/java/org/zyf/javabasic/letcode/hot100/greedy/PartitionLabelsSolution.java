package org.zyf.javabasic.letcode.hot100.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 划分字母区间（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:13
 **/
public class PartitionLabelsSolution {
    public List<Integer> partitionLabels(String s) {
        // 存储每个字母在字符串中的最后一次出现位置
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0; // 记录片段的起始位置
        int end = 0;   // 记录当前片段的最远结束位置

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']); // 更新当前片段的最远结束位置
            if (i == end) { // 当 i 达到当前片段的最远结束位置时，划分一个片段
                result.add(end - start + 1); // 记录片段长度
                start = i + 1; // 更新片段起始位置为下一个字符
            }
        }

        return result; // 返回所有片段的长度
    }

    public static void main(String[] args) {
        PartitionLabelsSolution solution = new PartitionLabelsSolution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij")); // 输出: [9, 7, 8]
        System.out.println(solution.partitionLabels("eccbbbbdec")); // 输出: [10]
    }
}
