package org.zyf.javabasic.letcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列，只要是标准的队列操作即可。
 * @date 2023/4/8  23:30
 */
public class ImplementStackUseQueues {

    static class MyStack {
        private Queue<Integer> q1;
        private Queue<Integer> q2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            q1.offer(x);
            while (!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
            Queue<Integer> tmp = q1;
            q1 = q2;
            q2 = tmp;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return q2.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return q2.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q2.isEmpty();
        }
    }


    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        /*3 */
        System.out.println(myStack.pop());
        /*2*/
        System.out.println(myStack.top());
        /*false*/
        System.out.println(myStack.empty());

        myStack.pop();
        myStack.pop();
        /*true*/
        System.out.println(myStack.empty());
    }
}
