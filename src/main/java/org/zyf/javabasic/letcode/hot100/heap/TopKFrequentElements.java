package org.zyf.javabasic.letcode.hot100.heap;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 前 K 个高频元素（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:56
 **/
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计每个元素出现的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. 创建桶数组，频率最高为nums.length
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // 3. 从后向前遍历桶数组，收集频率最高的 k 个元素
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }

        // 转换结果为数组
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        TopKFrequentElements solver = new TopKFrequentElements();
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println(Arrays.toString(solver.topKFrequent(nums1, k1))); // 输出: [1, 2]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(solver.topKFrequent(nums2, k2))); // 输出: [1]
    }
}
