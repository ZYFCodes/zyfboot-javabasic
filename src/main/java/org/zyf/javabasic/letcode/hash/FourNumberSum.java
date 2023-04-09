package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定四个包含整数的数组列表 A , B , C , D ,
 * 计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * @date 2023/4/9  21:07
 */
public class FourNumberSum {

    /**
     * 四数相加 II 问题可以通过将其分解为两个两数相加的问题来解决。具体步骤如下：
     * 创建两个哈希表，分别用于存储 A 数组和 B 数组中所有两个数之和的情况。
     * 遍历 A 数组和 B 数组，统计每个数字对应的出现次数，并将其加入哈希表中。
     * 遍历 C 数组和 D 数组，对于每个数字，在哈希表中查找其相反数的出现次数，并将计数器累加至结果中。
     * 返回结果。
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*创建两个哈希表，分别用于存储 A 数组和 B 数组中所有两个数之和的情况*/
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        /*遍历 A 数组和 B 数组，统计每个数字对应的出现次数，并将其加入哈希表中*/
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                map1.put(sum, map1.getOrDefault(sum, 0) + 1);
            }
        }

        /*遍历 C 数组和 D 数组，对于每个数字，在哈希表中查找其相反数的出现次数，并将计数器累加至结果中*/
        int count = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                if (map1.containsKey(-sum)) {
                    count += map1.get(-sum);
                }
            }
        }

        /*返回结果*/
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};

        int count = new FourNumberSum().fourSumCount(A, B, C, D);
        /*输出 2*/
        System.out.println(count);
    }
}
