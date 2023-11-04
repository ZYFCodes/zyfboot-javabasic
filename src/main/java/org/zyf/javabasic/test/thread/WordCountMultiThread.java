package org.zyf.javabasic.test.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 编写一个程序，用于统计一批文本文件的单词出现次数，程序的输入是文件名列表，输出一个单词到次数的映射。
 * @author: zhangyanfeng
 * @create: 2023-10-21 20:56
 **/
public class WordCountMultiThread {
    public static void main(String[] args) {
        String[] fileNames = {"file1.txt", "file2.txt", "file3.txt", "file4.txt"};
        AtomicInteger filesProcessed = new AtomicInteger(0);
        Map<String, AtomicInteger> wordCount = new ConcurrentHashMap<>();

        for (String fileName : fileNames) {
            new Thread(() -> {
                Map<String, AtomicInteger> localWordCount = new ConcurrentHashMap<>();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] words = line.split("\\s+");
                        for (String word : words) {
                            localWordCount.computeIfAbsent(word, k -> new AtomicInteger(0)).incrementAndGet();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (Map.Entry<String, AtomicInteger> entry : localWordCount.entrySet()) {
                    wordCount.merge(entry.getKey(), entry.getValue(), (v1, v2) -> new AtomicInteger(v1.get() + v2.get()));
                }
                if (filesProcessed.incrementAndGet() == fileNames.length) {
                    // All threads have processed their files, print the final word count
                    wordCount.forEach((key, value) -> System.out.println(key + ": " + value));
                }
            }).start();
        }
    }
}
