package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 移动零 (Move Zeroes) 题目描述：
 * 给定一个数组 nums，编写一个函数将所有0移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例：
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * @date 2023/3/30  23:04
 */
public class MoveZeroes {

    /**
     * 双指针法：
     * 使用两个指针 left 和 right，其中 left 指向第一个 0 的位置，right 指向第一个不为 0 的位置，
     * 然后将 right 指向的数赋值给 left 指向的位置，同时将 right 指针后移，left 指针也后移，如此循环，直到 right 指针超出数组范围。
     * 最后，将 left 指针之后的所有元素置为 0，即可得到移动零后的数组。
     * <p>
     * 这种解法时间复杂度为 O(n)，空间复杂度为 O(1)，是最优解法。
     * 因为它只需要遍历一遍数组，时间复杂度为 O(n)，同时不需要额外的空间，空间复杂度为 O(1)。
     * 相比于其他解法，如暴力遍历或是双指针交换，该算法可以更快地完成任务。同时，这个算法也可以保持非零元素的相对顺序，不会影响数组的其他元素。
     *
     * @param nums 输入数组
     */
    public void moveZeroes(int[] nums) {
        /*边界条件检查*/
        if (nums == null || nums.length == 0) {
            return;
        }

        /*定义双指针 left 和 right，分别指向第一个 0 的位置和第一个非 0 的位置*/
        int left = 0, right = 0;
        /*当 right 指针未到达数组末尾时*/
        while (right < nums.length) {
            /*如果 right 指针指向的数不为 0*/
            if (nums[right] != 0) {
                /*将 right 指针指向的数赋值给 left 指针指向的位置*/
                nums[left] = nums[right];
                /*left 指针后移*/
                left++;
            }
            /*right 指针后移*/
            right++;
        }
        /*将 left 指针之后的所有元素置为 0*/
        while (left < nums.length) {
            nums[left] = 0;
            left++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new MoveZeroes().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
