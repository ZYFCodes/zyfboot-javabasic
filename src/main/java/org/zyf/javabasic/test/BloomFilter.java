package org.zyf.javabasic.test;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 布隆过滤器
 * @author: zhangyanfeng
 * @create: 2021-08-10 22:44
 **/
public class BloomFilter {
    private BitSet bitSet;
    private int bitSize;
    private int[] hashSeeds;
    private int hashFunctionCount;

    public BloomFilter(int expectedInsertions, double falsePositiveProbability) {
        // 计算位数组大小和哈希函数数量
        this.bitSize = (int) (-expectedInsertions * Math.log(falsePositiveProbability) / (Math.log(2) * Math.log(2)));
        this.hashFunctionCount = (int) (bitSize / expectedInsertions * Math.log(2));
        this.bitSet = new BitSet(bitSize);
        this.hashSeeds = new int[hashFunctionCount];

        Random random = new Random();
        for (int i = 0; i < hashFunctionCount; i++) {
            hashSeeds[i] = random.nextInt();
        }
    }

    public void add(String url) {
        byte[] bytes = url.getBytes(StandardCharsets.UTF_8);
        for (int seed : hashSeeds) {
            int hash = hash(bytes, seed);
            bitSet.set(Math.abs(hash % bitSize));
        }
    }

    public boolean contains(String url) {
        byte[] bytes = url.getBytes(StandardCharsets.UTF_8);
        for (int seed : hashSeeds) {
            int hash = hash(bytes, seed);
            if (!bitSet.get(Math.abs(hash % bitSize))) {
                return false;
            }
        }
        return true;
    }

    private int hash(byte[] bytes, int seed) {
        int result = 0;
        for (byte b : bytes) {
            result = result * seed + b;
        }
        return result;
    }

    public static void main(String[] args) {
        int expectedInsertions = 2000000000; // 20亿
        double falsePositiveProbability = 0.01; // 1% 的误判率
        BloomFilter bloomFilter = new BloomFilter(expectedInsertions, falsePositiveProbability);

        // 插入URL
        bloomFilter.add("http://example.com");

        // 查询URL
        System.out.println(bloomFilter.contains("http://example.com")); // true
        System.out.println(bloomFilter.contains("http://example.org")); // false
    }
}
