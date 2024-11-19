package org.zyf.javabasic.test.worddeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: zyfboot-javabasic
 * @description: 启动任务并合并结果
 * @author: zhangyanfeng
 * @create: 2019-11-19 23:17
 **/
public class DistributedWordCount {
    // 创建一个线程池，最大线程数为 4（可以根据实际情况调整）
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    // 主方法：启动任务并合并结果
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 假设已经切割好了 4 个小文件，文件路径如下：
        String[] filePaths = {"chunks/chunk_0.txt", "chunks/chunk_1.txt", "chunks/chunk_2.txt", "chunks/chunk_3.txt"};

        // 创建一个列表，用于存储所有任务的 Future 对象
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        // 将每个文件的处理任务提交给线程池
        for (String filePath : filePaths) {
            WordCountTask task = new WordCountTask(filePath);
            futures.add(executorService.submit(task)); // 提交任务并保存 Future 对象
        }

        // 创建一个全局 HashMap，用于存储所有小文件的统计结果
        Map<String, Integer> globalWordCount = new HashMap<>();

        // 等待所有任务完成并合并结果
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> localCount = future.get(); // 获取每个线程的统计结果
            // 将每个小文件的统计结果合并到全局结果中
            for (Map.Entry<String, Integer> entry : localCount.entrySet()) {
                globalWordCount.put(entry.getKey(), globalWordCount.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        // 关闭线程池
        executorService.shutdown();

        // 输出全局统计结果
        globalWordCount.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
