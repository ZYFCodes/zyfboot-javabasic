package org.zyf.javabasic.letcode.jd150.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: findSubstring
 * @author: zhangyanfeng
 * @create: 2024-08-25 11:05
 **/
public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>(); // 结果列表
        int m = words.length; // 单词数量
        int n = words[0].length(); // 每个单词的长度
        int ls = s.length(); // 字符串 s 的长度

        // 遍历所有可能的起始位置
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break; // 如果剩余长度不足以容纳所有单词，则结束
            }

            // 记录当前窗口内的单词及其计数
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n); // 当前窗口中的单词
                differ.put(word, differ.getOrDefault(word, 0) + 1); // 计数
            }

            // 从 words 中移除所有单词的计数，准备验证
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word); // 移除计数为零的单词
                }
            }

            // 滑动窗口
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    // 更新窗口：添加新单词，移除旧单词
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }

                // 检查是否所有单词都匹配
                if (differ.isEmpty()) {
                    res.add(start); // 记录符合条件的起始位置
                }
            }
        }

        return res; // 返回结果
    }
}
