package org.zyf.javabasic.letcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * @date 2023/4/9  20:52
 */
public class TopKFrequentElements {

    /**
     * 该问题可以使用哈希表和桶排序来解决，具体思路如下：
     * 遍历数组，统计每个元素出现的次数，可以使用哈希表来实现，键为元素值，值为元素出现的次数。
     * 将哈希表中的元素放入桶中，桶的下标为元素出现的次数，桶中存放的是出现次数相同的元素值。
     * 从桶的末尾开始遍历，依次取出前 k 个出现频率最高的元素。
     */
    public int[] topKFrequent(int[] nums, int k) {
        /*第 1 步：使用哈希表统计每个元素出现的频率*/
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        /*第 2 步：使用桶排序，将出现频率作为桶的下标*/
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        /*第 3 步：从后向前遍历桶，并将桶中的元素加入结果集中*/
        int[] result = new int[k];
        int index = 0;
        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {
            if (bucket[i] == null) {
                continue;
            }
            for (int num : bucket[i]) {
                result[index++] = num;
                if (index == k) {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] res = new TopKFrequentElements().topKFrequent(nums, k);
        /*[1, 2]*/
        System.out.println(Arrays.toString(res));
    }
}
