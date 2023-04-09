package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
 * 计算按此排列的柱子，下雨之后能够捕获多少雨水
 * @date 2023/4/8  23:48
 */
public class TrappingRainWater {

    /**
     * 在这个实现中，我们首先定义了一个空栈和一个雨水总量的变量 water。
     * 然后，我们遍历高度数组。对于每个高度，我们在栈不为空且当前高度大于栈顶元素高度的情况下，执行以下操作：
     * 取出栈顶元素，作为坑洼的底部。
     * 如果此时栈为空，则无法存储雨水，跳出循环。
     * 计算坑洼的左右两侧边界。
     * 根据左右两侧边界以及底部高度计算雨水量，并将其加入 water 变量中。
     * <p>
     * 在每次处理完高度后，我们将当前下标压入栈中，以便后续计算。最后，我们返回 water 变量即可。
     */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                /*取出栈顶元素作为坑洼的底部*/
                int bottom = stack.pop();
                /*如果栈为空，那么无法存储雨水，跳出循环*/
                if (stack.isEmpty()) {
                    break;
                }
                /*计算坑洼的左右两侧边界*/
                int left = stack.peek();
                int right = i;
                /*根据左右两侧边界以及底部高度计算雨水量*/
                int width = right - left - 1;
                int heightDiff = Math.min(height[left], height[right]) - height[bottom];
                water += width * heightDiff;
            }
            /*将当前下标压入栈中*/
            stack.push(i);
        }
        return water;
    }

    /**
     * 在这个示例中，我们将给定的高度数组设置为 height = {0,1,0,2,1,0,1,3,2,1,2,1} ，
     * 期望的结果是能够计算出接到的雨水的总量。
     * 调用 trap(height) 方法并将返回结果打印到控制台上，预期输出结果应该是 6。
     */
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res = new TrappingRainWater().trap(height);
        /*输出结果应为6*/
        System.out.println(res);
    }


}
