package org.zyf.javabasic.guava.cache;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 * @program: zyfboot-javabasic
 * @description: 布隆过滤器的基本思路和使用方法
 * 基本思路：
 *
 * 初始化一个大小为 size 的 int 数组作为位数组。
 * 写入数据时，使用 numHashFunctions 个哈希函数进行哈希运算，并将对应位置置为 1。
 * 查询数据时，同样使用 numHashFunctions 个哈希函数进行哈希运算，如果对应位置有一个值为 0，则认为数据不存在。
 * @author: zhangyanfeng
 * @create: 2017-03-23 22:33
 **/
public class ZYFBloomFilter {
    /**
     * 位数组的大小
     */
    private final int size;

    /**
     * 用于存储布隆过滤器的位数组
     */
    private final BitSet bitSet;

    /**
     * 哈希函数的数量
     */
    private final int numHashFunctions;

    /**
     * 构造方法，创建一个指定大小和哈希函数数量的布隆过滤器
     *
     * @param size 位数组的大小
     * @param numHashFunctions 哈希函数的数量
     */
    public ZYFBloomFilter(int size, int numHashFunctions) {
        if (size <= 0 || numHashFunctions <= 0) {
            throw new IllegalArgumentException("位数组大小和哈希函数数量必须为正数。");
        }
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        this.bitSet = new BitSet(size);
    }

    /**
     * 添加一个键到布隆过滤器中
     *
     * @param key 要添加的键
     */
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为null。");
        }
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = computeHash(bytes, i);
            bitSet.set(Math.abs(hash % size), true);
        }
    }

    /**
     * 检查一个键是否可能存在于布隆过滤器中
     *
     * @param key 要检查的键
     * @return 如果键可能存在于布隆过滤器中，则返回true；如果键肯定不存在，则返回false
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为null。");
        }
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = computeHash(bytes, i);
            if (!bitSet.get(Math.abs(hash % size))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算给定数据和种子的哈希值
     *
     * @param data 要哈希的数据
     * @param seed 哈希函数的种子
     * @return 计算出的哈希值
     */
    private int computeHash(byte[] data, int seed) {
        int hash = 0;
        for (byte b : data) {
            hash = 31 * hash + b;
        }
        return hash ^ seed;
    }

    public static void main(String[] args) {
        // 初始化布隆过滤器，位数组大小为1000，使用3个哈希函数
        ZYFBloomFilter bloomFilter = new ZYFBloomFilter(1000, 3);

        // 添加数据到布隆过滤器
        bloomFilter.add("hello");

        // 检查数据是否可能存在
        System.out.println(bloomFilter.contains("hello"));  // true
        System.out.println(bloomFilter.contains("world"));  // false
    }
}
