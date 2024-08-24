package org.zyf.javabasic.letcode.featured75.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 爱吃香蕉的珂珂
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:38
 **/
public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canEatAll(piles, h, mid)) {
                right = mid; // 尝试更小的速度
            } else {
                left = mid + 1; // 速度太小，需要增加
            }
        }

        return left;
    }

    // 辅助函数：获取 piles 中的最大值
    private int getMax(int[] piles) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }

    // 辅助函数：判断速度 k 是否能在 h 小时内吃完所有香蕉
    private boolean canEatAll(int[] piles, int h, int k) {
        int hoursNeeded = 0;
        for (int pile : piles) {
            hoursNeeded += (pile + k - 1) / k; // 向上取整
        }
        return hoursNeeded <= h;
    }

    public static void main(String[] args) {
        MinEatingSpeed solution = new MinEatingSpeed();

        // 测试用例 1
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        int result1 = solution.minEatingSpeed(piles1, h1);
        System.out.println("Test Case 1: " + result1); // 预期输出: 4

        // 测试用例 2
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        int result2 = solution.minEatingSpeed(piles2, h2);
        System.out.println("Test Case 2: " + result2); // 预期输出: 30

        // 测试用例 3
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        int result3 = solution.minEatingSpeed(piles3, h3);
        System.out.println("Test Case 3: " + result3); // 预期输出: 23

        // 测试用例 4：边界条件
        int[] piles4 = {1, 1, 1, 1, 1};
        int h4 = 5;
        int result4 = solution.minEatingSpeed(piles4, h4);
        System.out.println("Test Case 4: " + result4); // 预期输出: 1

        // 测试用例 5：较大的输入
        int[] piles5 = {1000000000, 1000000000, 1000000000};
        int h5 = 2;
        int result5 = solution.minEatingSpeed(piles5, h5);
        System.out.println("Test Case 5: " + result5); // 预期输出: 1000000000
    }
}
