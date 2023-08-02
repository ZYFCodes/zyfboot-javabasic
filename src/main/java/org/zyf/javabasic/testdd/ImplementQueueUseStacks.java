package org.zyf.javabasic.testdd;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/7/31  19:59
 */
public class ImplementQueueUseStacks {
    static class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }


}
