package org.zyf.javabasic.test.worddeal;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 合并所有结果
 * @author: zhangyanfeng
 * @create: 2019-11-19 23:22
 **/
public class RedisStorage {
    private static final String REDIS_HOST = "localhost"; // Redis 主机地址
    private static final int REDIS_PORT = 6379; // Redis 端口

    // 方法：将单词统计结果存储到 Redis 中
    public static void storeWordCount(Map<String, Integer> wordCountMap) {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            // 使用 Redis 的 Hash 数据结构存储单词频率
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                jedis.hincrBy("word_count", entry.getKey(), entry.getValue()); // 自增操作，避免重复
            }
        }
    }

    // 主方法：示范将统计结果存储到 Redis
    public static void main(String[] args) {
        Map<String, Integer> globalWordCount = new HashMap<>();
        // 假设 globalWordCount 已经包含了最终的单词统计结果

        // 将统计结果存储到 Redis
        storeWordCount(globalWordCount);
    }
}
