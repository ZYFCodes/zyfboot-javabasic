package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2)。
 * @date 2023/4/1  22:19
 */
public class ThreeSumClosest {
    /**
     * 最优解法是先将数组排序，然后利用双指针扫描数组。具体思路如下：
     * 1 将数组排序。
     * 2 遍历数组，以当前元素为基准，使用双指针扫描基准元素之后的数组。
     * 3 双指针指向的两个元素和基准元素相加，计算它们和target的差值。
     * 4 如果差值小于当前最小差值（初始值可以设置为无穷大），则更新当前最小差值和结果。
     * 5 根据差值与0的关系移动左右指针。
     * 最后得到的结果即为最接近target的三数之和。
     */
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        /*首先将数组排序*/
        Arrays.sort(nums);
        int best = 10000000;

        /*枚举 a*/
        for (int i = 0; i < n; ++i) {
            /*保证和上一次枚举的数不相等*/
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            /*使用双指针枚举 b 和 c*/
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                /*如果和为 target 直接返回答案*/
                if (sum == target) {
                    return target;
                }
                /*根据差值的绝对值来更新答案*/
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    /*如果和大于 target，移动 c 对应的指针*/
                    int k0 = k - 1;
                    /*移动到下一个不相等的元素*/
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    /*如果和小于 target，移动 b 对应的指针*/
                    int j0 = j + 1;
                    /*移动到下一个不相等的元素*/
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
    }

}
