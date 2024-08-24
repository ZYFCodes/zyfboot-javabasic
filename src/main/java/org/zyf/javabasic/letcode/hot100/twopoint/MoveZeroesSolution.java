package org.zyf.javabasic.letcode.hot100.twopoint;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 移动零
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:44
 **/
public class MoveZeroesSolution {
    public void moveZeroes(int[] nums) {
        int j = 0; // j指针用于记录下一个非零元素的位置

        // 遍历数组，将所有非零元素按顺序移动到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        // 将剩下的位置全部填充为0
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZeroesSolution solution = new MoveZeroesSolution();

        int[] nums1 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1)); // 输出: [1, 3, 12, 0, 0]

        int[] nums2 = {0};
        solution.moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2)); // 输出: [0]
    }
}
