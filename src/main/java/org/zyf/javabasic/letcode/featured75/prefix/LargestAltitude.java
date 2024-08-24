package org.zyf.javabasic.letcode.featured75.prefix;

/**
 * @program: zyfboot-javabasic
 * @description: 找到最高海拔
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:54
 **/
public class LargestAltitude {
    public int largestAltitude(int[] gain) {
        int currentAltitude = 0; // 当前海拔高度，初始为 0
        int maxAltitude = 0; // 最高海拔高度，初始为 0

        // 遍历 gain 数组
        for (int g : gain) {
            // 更新当前海拔高度
            currentAltitude += g;
            // 更新最高海拔高度
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }

        return maxAltitude; // 返回最高海拔高度
    }

    public static void main(String[] args) {
        LargestAltitude solution = new LargestAltitude();

        // 测试用例 1
        int[] gain1 = {-5, 1, 5, 0, -7};
        System.out.println("Test Case 1: " + (solution.largestAltitude(gain1) == 1 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] gain2 = {-4, -3, -2, -1, 4, 3, 2};
        System.out.println("Test Case 2: " + (solution.largestAltitude(gain2) == 0 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] gain3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3: " + (solution.largestAltitude(gain3) == 15 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] gain4 = {-1, -2, -3, -4};
        System.out.println("Test Case 4: " + (solution.largestAltitude(gain4) == 0 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] gain5 = {10, -5, -1, 2, 6};
        System.out.println("Test Case 5: " + (solution.largestAltitude(gain5) == 12 ? "Passed" : "Failed"));
    }
}
