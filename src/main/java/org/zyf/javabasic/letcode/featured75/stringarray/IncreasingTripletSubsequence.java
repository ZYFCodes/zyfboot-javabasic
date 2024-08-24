package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 递增的三元子序列
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:00
 **/
public class IncreasingTripletSubsequence {
    public static boolean increasingTriplet(int[] nums) {
        // 初始化两个变量，first 表示最小值，second 表示第二小值
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        // 遍历数组
        for (int num : nums) {
            if (num <= first) {
                // 如果当前元素比 first 小或相等，更新 first
                first = num;
            } else if (num <= second) {
                // 如果当前元素比 second 小或相等，更新 second
                second = num;
            } else {
                // 如果当前元素大于 second，则找到递增三元组，返回 true
                return true;
            }
        }

        // 如果遍历完数组没有找到符合条件的三元组，返回 false
        return false;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        System.out.println(increasingTriplet(nums1)); // 输出: true

        int[] nums2 = {5, 4, 3, 2, 1};
        System.out.println(increasingTriplet(nums2)); // 输出: false

        int[] nums3 = {2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(nums3)); // 输出: true
    }
}
