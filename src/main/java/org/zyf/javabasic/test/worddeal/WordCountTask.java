package org.zyf.javabasic.test.worddeal;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 每台计算机处理一个小文件，单词统计
 * @author: zhangyanfeng
 * @create: 2019-11-19 23:16
 **/
public class WordCountTask implements Callable<Map<String, Integer>> {
    private String filePath; // 小文件路径

    // 构造方法，传入文件路径
    public WordCountTask(String filePath) {
        this.filePath = filePath;
    }

    // 实现 Callable 接口的 call 方法，用于单词统计
    @Override
    public Map<String, Integer> call() throws Exception {
        // 创建一个 HashMap 用于存储单词和它们的出现次数
        Map<String, Integer> wordCountMap = new HashMap<>();

        // 使用 BufferedReader 逐行读取文件内容
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 按非字母字符分割单词
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase(); // 将单词转换为小写
                        // 更新单词的计数
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        // 返回统计结果
        return wordCountMap;
    }
}
