package org.zyf.javabasic.letcode.hot100.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 最小栈（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:29
 **/
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * Initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Push element val onto stack.
     */
    public void push(int val) {
        stack.push(val);
        // Push the new minimum value onto minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Removes the element on the top of the stack.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            int top = stack.pop();
            // If the popped element is the same as the top of minStack, pop from minStack as well
            if (top == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    /**
     * Get the top element of the stack.
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieve the minimum element in the stack.
     */
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Returns -3
        minStack.pop();
        System.out.println(minStack.top());    // Returns 0
        System.out.println(minStack.getMin()); // Returns -2
    }
}
