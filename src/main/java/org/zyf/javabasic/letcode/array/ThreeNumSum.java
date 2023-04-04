package org.zyf.javabasic.letcode.array;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 在一个数组中找到三个数，使得它们的和等于0。
 * @date 2023/3/30  23:29
 */
public class ThreeNumSum {

    /**
     * 目前时间复杂度最优的三数之和解法是基于双指针的方法
     * 具体思路是先对数组进行排序，然后从小到大枚举第一个数，再用双指针分别指向第一个数后面的数组首尾两端，向中间逼近寻找满足条件的三元组。
     * <p>
     * 该算法的时间复杂度为 O(n^2)，与暴力枚举法相比，具有更好的性能。
     * 该算法的空间复杂度为 O(1)，因为仅使用了常数个额外的变量用于存储一些临时值，不会随着输入规模的增加而增加空间消耗。
     * 因此，在时间复杂度与空间复杂度均较为敏感的场景中，基于双指针的三数之和解法是一种较为优秀的选择。
     *
     * @param nums 输入数组
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /*边界条件检查*/
        if (nums == null || nums.length < 3) {
            return Lists.newArrayList();
        }

        /*先排序*/
        Arrays.sort(nums);
        List<List<Integer>> res = Lists.newArrayList();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                /*如果当前数已经大于0，后面的数不可能加起来等于0*/
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                /*避免重复计算*/
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    /*避免重复计算*/
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new ThreeNumSum().threeSum(nums);
        System.out.println(res);
    }
}
