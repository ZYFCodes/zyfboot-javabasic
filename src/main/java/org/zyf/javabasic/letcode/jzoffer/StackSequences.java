package org.zyf.javabasic.letcode.jzoffer;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定两个整数数组 pushed 和 popped，分别表示压栈序列和弹出序列。
 * 判断该弹出序列是否可能是压栈序列的弹出顺序。
 * @date 2023/6/7  22:10
 */
public class StackSequences {
    /**
     * 可以利用一个辅助栈来模拟压栈和弹出的过程。遍历压栈序列，依次将元素压入辅助栈中。
     * 每次压入后，都检查辅助栈的栈顶元素是否与弹出序列的当前元素相同。
     * 如果相同，则将栈顶元素弹出，并将弹出序列的指针向后移动一位。
     * 最后，检查辅助栈是否为空，若为空则说明弹出序列是合法的。
     * 详细的解题步骤如下
     * 	1.	初始化一个辅助栈和一个指向弹出序列的指针popIndex，并将其初始值设为0。
     * 	2.	遍历压栈序列，依次将元素压入辅助栈中。
     * 	3.	每次压入后，判断辅助栈的栈顶元素是否与弹出序列中的当前元素相等。
     * 	•	如果相等，则将栈顶元素弹出，并将popIndex指针后移一位。
     * 	•	如果不相等，则继续压入下一个元素。
     * 	4.	遍历完压栈序列后，检查辅助栈是否为空。如果为空，则说明弹出序列是合法的，返回true；否则返回false。
     * 这种解法的时间复杂度是O(N)，其中N是压栈序列的长度。
     * 请注意，题目中未明确规定压栈序列和弹出序列的长度是否相等或者存在重复元素，因此在实际解题时需要考虑这些情况。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        // 弹出序列的指针
        int popIndex = 0;

        for (int num : pushed) {
            // 将元素压入辅助栈
            stack.push(num);

            // 检查辅助栈的栈顶元素是否与弹出序列中的当前元素相等
            while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                // 弹出栈顶元素
                stack.pop();
                // 弹出序列的指针后移一位
                popIndex++;
            }
        }

        // 检查辅助栈是否为空，如果为空则说明弹出序列是合法的
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StackSequences solution = new StackSequences();

        // 验证样例1
        int[] pushed1 = {1, 2, 3, 4, 5};
        int[] popped1 = {4, 5, 3, 2, 1};
        boolean result1 = solution.validateStackSequences(pushed1, popped1);
        // 输出: true
        System.out.println(result1);

        // 验证样例2
        int[] pushed2 = {1, 2, 3, 4, 5};
        int[] popped2 = {4, 3, 5, 1, 2};
        boolean result2 = solution.validateStackSequences(pushed2, popped2);
        // 输出: false
        System.out.println(result2);
    }
}
