package org.zyf.javabasic.letcode.hot100.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 每日温度（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:40
 **/
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 当前温度比栈顶温度高，计算差值
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            // 压入当前温度的下标
            stack.push(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] result1 = dt.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        int[] result2 = dt.dailyTemperatures(new int[]{30, 40, 50, 60});
        int[] result3 = dt.dailyTemperatures(new int[]{30, 60, 90});

        // 打印结果
        System.out.println(java.util.Arrays.toString(result1)); // [1, 1, 4, 2, 1, 1, 0, 0]
        System.out.println(java.util.Arrays.toString(result2)); // [1, 1, 1, 0]
        System.out.println(java.util.Arrays.toString(result3)); // [1, 1, 0]
    }
}
