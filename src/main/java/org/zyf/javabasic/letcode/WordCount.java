package org.zyf.javabasic.letcode;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author yanfengzhang
 * @description 统计一篇超过10G的文章中每个单词出现的次数
 * @date 2023/4/9  12:24
 */
public class WordCount {
    /*线程数*/
    private static final int NUM_THREADS = 4;
    /*单词的正则表达式*/
    private static final Pattern WORD_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();

        String filePath = "/Users/yanfengzhang/Downloads/zyfTestData.txt";

        /*读取文件并分割成多个子任务*/
        Map<String, Integer> wordCounts = new HashMap<>();
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),
                1000000000,
                0.01);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = WORD_PATTERN.split(line);
                for (String word : words) {
                    if (!word.isEmpty()) {
                        String lowerCaseWord = word.toLowerCase();
                        if (!bloomFilter.mightContain(lowerCaseWord)) {
                            bloomFilter.put(lowerCaseWord);
                            wordCounts.put(lowerCaseWord, wordCounts.getOrDefault(lowerCaseWord, 0) + 1);
                        }
                    }
                }
            }
        }

        /*按照线程数分割成多个任务并发执行*/
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int numWordsPerThread = wordCounts.size() / NUM_THREADS;
        int startIndex = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            int endIndex = (i == NUM_THREADS - 1) ? wordCounts.size() : (startIndex + numWordsPerThread);
            executor.submit(new WordCounter(wordCounts, bloomFilter, startIndex, endIndex));
            startIndex = endIndex;
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        long endTime = System.currentTimeMillis();

        /*输出结果*/
        wordCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> b - a))
                .limit(100)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("Elapsed time: " + (endTime - startTime) / 1000.0 + "s");
    }

    /**
     * 子任务：统计指定区间的单词出现次数
     */
    private static class WordCounter implements Runnable {
        private final Map<String, Integer> wordCounts;
        private final BloomFilter<CharSequence> bloomFilter;
        private final int startIndex;
        private final int endIndex;

        public WordCounter(Map<String, Integer> wordCounts, BloomFilter<CharSequence> bloomFilter, int startIndex, int endIndex) {
            this.wordCounts = wordCounts;
            this.bloomFilter = bloomFilter;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            int i = 0;
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                if (i >= startIndex && i < endIndex) {
                    String word = entry.getKey();
                    if (!bloomFilter.mightContain(word)) {
                        /*如果布隆过滤器中不存在该单词，则跳过*/
                        continue;
                    }
                    int count = entry.getValue();
                    wordCounts.put(word, count);
                }
                i++;
            }
        }
    }

}
