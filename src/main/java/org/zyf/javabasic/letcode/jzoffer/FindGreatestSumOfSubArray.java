package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为 O(n)。
 * @date 2023/6/4  22:39
 */
public class FindGreatestSumOfSubArray {
    /**
     * 这是一个经典的动态规划问题。我们定义两个变量：maxSum 用于记录当前的最大子数组和，curSum 用于记录当前的子数组和。
     * 我们遍历数组，对于每个元素，有两种情况：
     * 1.	如果 curSum 加上当前元素后仍然大于当前元素本身，说明当前元素属于当前的子数组，我们更新 curSum。
     * 2.	如果 curSum 加上当前元素后小于当前元素本身，说明当前元素是新的子数组的起点，
     * 我们将 curSum 更新为当前元素，并比较 curSum 和 maxSum，更新 maxSum。
     * 最后返回 maxSum 即可得到最大子数组的和。
     * 【注意】需要注意处理数组为空的情况，可以在开始时将 maxSum 和 curSum 初始化为数组的第一个元素。
     */
    public int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        // 记录当前的最大子数组和
        int maxSum = array[0];
        // 记录当前的子数组和
        int curSum = array[0];

        for (int i = 1; i < array.length; i++) {
            if (curSum + array[i] > array[i]) {
                // 如果当前子数组和加上当前元素后仍然大于当前元素本身，说明当前元素属于当前的子数组
                curSum += array[i];
            } else {
                // 如果当前子数组和加上当前元素后小于当前元素本身，说明当前元素是新的子数组的起点
                // 更新当前子数组和为当前元素，并比较更新最大子数组和
                curSum = array[i];
            }

            if (curSum > maxSum) {
                // 比较更新最大子数组和
                maxSum = curSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        FindGreatestSumOfSubArray solution = new FindGreatestSumOfSubArray();

        int[] array1 = {1, -2, 3, 10, -4, 7, 2, -5};
        int result1 = solution.findGreatestSumOfSubArray(array1);
        // Expected output: 18
        System.out.println("Result 1: " + result1);

        int[] array2 = {-2, -3, -1, -4, -6};
        int result2 = solution.findGreatestSumOfSubArray(array2);
        // Expected output: -1
        System.out.println("Result 2: " + result2);

        int[] array3 = {1, 2, 3, 4, 5};
        int result3 = solution.findGreatestSumOfSubArray(array3);
        // Expected output: 15
        System.out.println("Result 3: " + result3);
    }
}