package org.zyf.javabasic.letcode.hot100.skills;

/**
 * @program: zyfboot-javabasic
 * @description: 颜色分类（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 21:15
 **/
public class SortColors {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // 将 0 移动到红色区域
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 是白色，直接移动
                mid++;
            } else {
                // 将 2 移动到蓝色区域
                swap(nums, mid, high);
                high--;
            }
        }
    }

    // 交换数组中的两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortColors sc = new SortColors();

        // 测试用例1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sc.sortColors(nums1);
        System.out.println("测试用例1结果: " + java.util.Arrays.toString(nums1)); // 输出：[0, 0, 1, 1, 2, 2]

        // 测试用例2
        int[] nums2 = {2, 0, 1};
        sc.sortColors(nums2);
        System.out.println("测试用例2结果: " + java.util.Arrays.toString(nums2)); // 输出：[0, 1, 2]
    }
}
