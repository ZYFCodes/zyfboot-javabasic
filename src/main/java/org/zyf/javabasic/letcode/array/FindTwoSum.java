package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个升序数组 arr 和一个整数 sum，
 * 判断该数组中是否存在两个数，它们的和等于 sum。
 * @date 2023/6/14  23:58
 */
public class FindTwoSum {
    /**
     * 由于数组是升序排列的，我们可以使用双指针的方法来解决该问题。具体步骤如下：
     *
     * 	1.	初始化两个指针 left 和 right，分别指向数组的起始位置和结束位置。
     * 	2.	循环遍历数组，直到 left 大于等于 right：
     * 	•	计算当前指针所指元素的和 currSum = arr[left] + arr[right]。
     * 	•	如果 currSum 等于 sum，则说明找到了满足条件的两个数，返回 true。
     * 	•	如果 currSum 小于 sum，说明当前和太小，我们需要增大和，将 left 指针右移一位。
     * 	•	如果 currSum 大于 sum，说明当前和太大，我们需要减小和，将 right 指针左移一位。
     * 	3.	如果循环结束后仍未找到满足条件的两个数，则说明数组中不存在这样的两个数，返回 false。
     *
     * 通过上述步骤，我们可以判断数组中是否存在两个数的和等于给定的 sum。该算法的时间复杂度为 O(n)，其中 n 是数组的长度。
     * @param arr
     * @param sum
     * @return
     */
    public boolean findTwoSum(int[] arr, int sum) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currSum = arr[left] + arr[right];

            if (currSum == sum) {
                return true;
            } else if (currSum < sum) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int sum = 10;

        FindTwoSum solution = new FindTwoSum();
        boolean hasTwoSum = solution.findTwoSum(arr, sum);
        // 输出 Has Two Sum: true
        System.out.println("Has Two Sum: " + hasTwoSum);
    }
}
