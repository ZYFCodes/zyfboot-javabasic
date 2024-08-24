package org.zyf.javabasic.letcode.featured75.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 小行星碰撞
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:21
 **/
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        // 初始化栈，用于存放剩余的小行星
        Stack<Integer> stack = new Stack<>();

        // 遍历所有小行星
        for (int asteroid : asteroids) {
            boolean isDestroyed = false;
            // 处理向左移动的小行星
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                // 比较栈顶和当前小行星的大小
                if (stack.peek() < -asteroid) {
                    // 栈顶小行星较小，被摧毁
                    stack.pop();
                    continue;
                } else if (stack.peek() == -asteroid) {
                    // 两颗小行星大小相同，双双毁灭
                    stack.pop();
                }
                // 当前小行星被摧毁
                isDestroyed = true;
                break;
            }
            // 当前小行星未被摧毁，压入栈中
            if (!isDestroyed) {
                stack.push(asteroid);
            }
        }

        // 将栈中的小行星转换为数组
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        AsteroidCollision solution = new AsteroidCollision();

        // 测试用例 1
        int[] asteroids1 = {5, 10, -5};
        int[] result1 = solution.asteroidCollision(asteroids1);
        System.out.println(java.util.Arrays.toString(result1)); // 输出: [5, 10]

        // 测试用例 2
        int[] asteroids2 = {8, -8};
        int[] result2 = solution.asteroidCollision(asteroids2);
        System.out.println(java.util.Arrays.toString(result2)); // 输出: []

        // 测试用例 3
        int[] asteroids3 = {10, 2, -5};
        int[] result3 = solution.asteroidCollision(asteroids3);
        System.out.println(java.util.Arrays.toString(result3)); // 输出: [10]
    }
}
