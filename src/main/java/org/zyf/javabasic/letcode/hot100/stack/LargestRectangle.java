package org.zyf.javabasic.letcode.hot100.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 柱状图中最大的矩形（困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:45
 **/
public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangle lr = new LargestRectangle();
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        int[] heights2 = {2, 4};

        System.out.println(lr.largestRectangleArea(heights1)); // 输出: 10
        System.out.println(lr.largestRectangleArea(heights2)); // 输出: 4
    }
}
