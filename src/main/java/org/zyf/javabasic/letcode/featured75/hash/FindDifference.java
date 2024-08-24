package org.zyf.javabasic.letcode.featured75.hash;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 找出两数组的不同
 * @author: zhangyanfeng
 * @create: 2024-08-24 00:05
 **/
public class FindDifference {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // 创建两个集合用于存储 nums1 和 nums2 中的不同整数
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // 填充 set1
        for (int num : nums1) {
            set1.add(num);
        }

        // 填充 set2
        for (int num : nums2) {
            set2.add(num);
        }

        // 找到 nums1 中不在 nums2 中的不同整数
        Set<Integer> uniqueToNums1 = new HashSet<>(set1);
        uniqueToNums1.removeAll(set2);

        // 找到 nums2 中不在 nums1 中的不同整数
        Set<Integer> uniqueToNums2 = new HashSet<>(set2);
        uniqueToNums2.removeAll(set1);

        // 转化为列表并返回结果
        List<Integer> result1 = new ArrayList<>(uniqueToNums1);
        List<Integer> result2 = new ArrayList<>(uniqueToNums2);

        List<List<Integer>> result = new ArrayList<>();
        result.add(result1);
        result.add(result2);

        return result;
    }

    public static void main(String[] args) {
        FindDifference solution = new FindDifference();

        // 测试用例 1
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4, 6};
        System.out.println("Test Case 1: " + (solution.findDifference(nums1, nums2).equals(Arrays.asList(Arrays.asList(1, 3), Arrays.asList(4, 6))) ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums3 = {1, 2, 3, 3};
        int[] nums4 = {1, 1, 2, 2};
        System.out.println("Test Case 2: " + (solution.findDifference(nums3, nums4).equals(Arrays.asList(Arrays.asList(3), Collections.emptyList())) ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums5 = {4, 5, 6};
        int[] nums6 = {7, 8, 9};
        System.out.println("Test Case 3: " + (solution.findDifference(nums5, nums6).equals(Arrays.asList(Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9))) ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums7 = {1, 1, 1};
        int[] nums8 = {1, 1, 1};
        System.out.println("Test Case 4: " + (solution.findDifference(nums7, nums8).equals(Arrays.asList(Collections.emptyList(), Collections.emptyList())) ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums9 = {1, 2, 3, 4, 5};
        int[] nums10 = {5, 6, 7, 8, 9};
        System.out.println("Test Case 5: " + (solution.findDifference(nums9, nums10).equals(Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(6, 7, 8, 9))) ? "Passed" : "Failed"));
    }
}
