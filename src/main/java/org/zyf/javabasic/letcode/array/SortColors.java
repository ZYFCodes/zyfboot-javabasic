package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个包含红、白、蓝且长度为 n 的数组，
 * 将数组元素进行分类使得相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。
 * 我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
 * 注意: 不能使用代码库中的排序函数来解决这个问题。
 * 示例： 输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2]
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。 首先，迭代计算出 0、1 和 2 元素的个数，然后按照 0、1、2 的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @date 2023/4/2  19:50
 */
public class SortColors {

    /**
     * 我们定义三个指针，分别为p0、p2和当前遍历的元素指针i。p0和p2分别指向数组的左右两端，而i则是当前遍历到的元素。
     * <p>
     * 当遍历到的元素为0时，我们交换它和p0位置上的元素，并将p0向右移动一位。
     * 当遍历到的元素为2时，我们交换它和p2位置上的元素，并将p2向左移动一位。
     * 由于当前遍历到的元素是通过交换到当前位置的，我们需要再次检查一下该位置，因此将i减1。
     * 如果遍历到的元素为1，则不需要任何操作，继续向右遍历。
     * 当i指针遍历到p2指针的位置时，遍历完成。经过上述操作后，数组中的元素按照0、1、2的顺序排列。
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        /*初始化左右指针*/
        int p0 = 0, p2 = n - 1;
        /*遍历数组*/
        for (int i = 0; i <= p2; i++) {
            /*当前元素为0，移动到左侧区域*/
            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            } else if (nums[i] == 2) {
                /*当前元素为2，移动到右侧区域*/
                swap(nums, i, p2);
                p2--;
                /*由于i位置上的元素是经过交换到当前位置的，需要再次检查一下该位置*/
                i--;
            }
        }
    }

    /**
     * 数组元素交换方法
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {2, 0, 1};
        new SortColors().sortColors(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {0};
        new SortColors().sortColors(nums3);
        System.out.println(Arrays.toString(nums3));
    }

}
