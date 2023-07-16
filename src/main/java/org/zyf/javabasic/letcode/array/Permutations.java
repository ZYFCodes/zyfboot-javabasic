package org.zyf.javabasic.letcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个数组，要求返回这个数组的所有可能的全排列。
 * 全排列是一种将数组中的元素重新排列的方式，使得每个元素在排列结果中都出现一次，并且顺序不同即可。
 * 换句话说，对于长度为 n 的数组，全排列共有 n! 种可能。
 * 例如，对于数组 [1, 2, 3]，它的全排列包括 [1, 2, 3]、[1, 3, 2]、[2, 1, 3]、[2, 3, 1]、[3, 1, 2] 和 [3, 2, 1]。
 * 请问如何解决这个问题呢？
 * @date 2023/7/14  23:43
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    /**
     * 该问题的最优解题思路是使用回溯算法来生成全排列。
     * 回溯算法是一种通过尝试不同的选择来解决问题的算法。在生成全排列的过程中，我们可以通过交换数组中的元素来产生不同的排列。
     * 具体步骤如下：
     * 1. 定义一个递归函数 backtrack，该函数接收当前要生成排列的位置 index、原始数组 nums、当前已生成的排列 tempList，以及最终的结果列表 result。
     * 2. 如果当前位置 index 等于数组的长度，表示已经生成了一个全排列，将 tempList 添加到 result 中。
     * 3. 否则，从当前位置 index 开始，遍历数组 nums。将当前位置的元素与 index 位置的元素交换，相当于固定了一个元素，然后递归调用 backtrack，继续生成下一个位置的排列。
     * 4. 递归调用结束后，要恢复交换前的状态，以便进行下一轮的遍历。将当前位置的元素与 index 位置的元素交换回来。
     * 5. 回溯过程结束后，返回最终的结果列表 result。
     * 这种方法的时间复杂度是 O(n!)，其中 n 是数组的长度。因为全排列的数量是 n!，而生成每个全排列的时间复杂度是 O(n)。因此，整体的时间复杂度是 O(n!)。
     */
    private static void backtrack(int[] nums, int index, List<List<Integer>> result) {
        // 如果当前位置 index 等于数组的长度，表示已经生成了一个全排列，将 tempList 添加到 result 中
        if (index == nums.length) {
            List<Integer> tempList = new ArrayList<>();
            for (int num : nums) {
                tempList.add(num);
            }
            result.add(tempList);
        } else {
            for (int i = index; i < nums.length; i++) {
                // 交换当前位置的元素和 index 位置的元素，固定一个元素
                swap(nums, i, index);
                // 递归调用，生成下一个位置的排列
                backtrack(nums, index + 1, result);
                // 恢复交换前的状态，以便进行下一轮的遍历
                swap(nums, i, index);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6};
        List<List<Integer>> permutations = permute(nums);
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

}
