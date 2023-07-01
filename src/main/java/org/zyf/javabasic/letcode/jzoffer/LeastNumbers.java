package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;

/**
 * @author yanfengzhang
 * @description 输入一个整数数组，找出其中最小的K个数。
 * 例如，输入数组为[4,5,1,6,2,7,3,8]，则最小的4个数为[1,2,3,4]。
 * @date 2023/6/12  23:29
 */
public class LeastNumbers {
    /**
     * 可以使用堆排序或者快速选择算法来解决该问题。
     * 堆排序思路：
     * •	构建一个最大堆，堆中存放数组的前K个元素。
     * •	遍历数组的剩余元素，如果当前元素比堆顶元素小，则将堆顶元素替换为当前元素，并进行堆调整。
     * •	遍历完数组后，堆中的元素即为最小的K个数。
     * <p>
     * 快速选择思路：
     * •	使用快速排序的思想进行选择。
     * •	随机选择一个枢轴元素，将数组分为两部分，左边部分小于等于枢轴元素，右边部分大于枢轴元素。
     * •	如果枢轴元素的下标恰好等于K-1，则左边部分的元素即为最小的K个数。
     * •	如果枢轴元素的下标大于K-1，则在左边部分继续快速选择。
     * •	如果枢轴元素的下标小于K-1，则在右边部分继续快速选择
     */
    public ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            return result;
        }

        // 使用快速选择算法
        quickSelect(input, 0, input.length - 1, k);

        // 将最小的K个数加入结果列表
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }

        return result;
    }

    private void quickSelect(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == k - 1) {
            return;
        } else if (pivotIndex > k - 1) {
            quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1;
        int j = right;

        while (true) {
            while (i <= j && nums[i] < pivot) {
                i++;
            }

            while (i <= j && nums[j] > pivot) {
                j--;
            }

            if (i > j) {
                break;
            }

            swap(nums, i, j);
            i++;
            j--;
        }

        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LeastNumbers solution = new LeastNumbers();

        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        int k = 4;

        ArrayList<Integer> result = solution.getLeastNumbers(input, k);
        System.out.println(result);
    }
}
