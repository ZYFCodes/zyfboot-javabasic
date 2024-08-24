package org.zyf.javabasic.letcode.featured75.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 独一无二的出现次数
 * @author: zhangyanfeng
 * @create: 2024-08-24 00:12
 **/
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        // 统计每个数的出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 统计出现次数的频率
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int count : countMap.values()) {
            freqMap.put(count, freqMap.getOrDefault(count, 0) + 1);
        }

        // 检查出现次数的频率是否唯一
        for (int freq : freqMap.values()) {
            if (freq > 1) {
                return false; // 存在相同的出现次数
            }
        }

        return true; // 所有出现次数都是唯一的
    }

    public static void main(String[] args) {
        UniqueOccurrences solution = new UniqueOccurrences();

        // 测试用例 1
        int[] arr1 = {1, 2, 2, 1, 1, 3};
        System.out.println("Test Case 1: " + (solution.uniqueOccurrences(arr1) ? "Passed" : "Failed")); // 应输出 true

        // 测试用例 2
        int[] arr2 = {1, 2};
        System.out.println("Test Case 2: " + (solution.uniqueOccurrences(arr2) ? "Passed" : "Failed")); // 应输出 false

        // 测试用例 3
        int[] arr3 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println("Test Case 3: " + (solution.uniqueOccurrences(arr3) ? "Passed" : "Failed")); // 应输出 true

        // 测试用例 4
        int[] arr4 = {1, 1, 1, 2, 2, 3, 3, 3, 3};
        System.out.println("Test Case 4: " + (solution.uniqueOccurrences(arr4) ? "Passed" : "Failed")); // 应输出 false

        // 测试用例 5
        int[] arr5 = {1, 2, 2, 3, 3, 3};
        System.out.println("Test Case 5: " + (solution.uniqueOccurrences(arr5) ? "Passed" : "Failed")); // 应输出 false
    }
}
