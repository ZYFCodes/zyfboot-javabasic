package org.zyf.javabasic.letcode.stack;

/**
 * @author yanfengzhang
 * @description 设计一个栈数据结构，使用数组来实现。
 * 需要支持入栈、出栈、获取栈顶元素和判断栈是否为空的操作。
 * <p>
 * 解题思路：
 * 1.	创建一个数组 stack 用于存储栈中的元素。
 * 2.	维护一个变量 top 表示栈顶的索引位置，初始值为 -1。
 * 3.	入栈操作：将要入栈的元素放入 stack 数组中 top+1 的位置，然后将 top 的值加 1。
 * 4.	出栈操作：从 stack 数组中取出 top 位置的元素，然后将 top 的值减 1。
 * 5.	获取栈顶元素操作：返回 stack 数组中 top 位置的元素。
 * 6.	判断栈是否为空操作：当 top 的值为 -1 时，表示栈为空。
 * @date 2023/6/14  23:28
 */
public class ImplementStackUseArray {
    private int[] stack;
    private int top;

    public ImplementStackUseArray(int capacity) {
        stack = new int[capacity];
        /*将 top 初始化为 -1*/
        top = -1;
    }

    /**
     * push(int element) 方法用于将元素入栈，
     * 它首先检查栈是否已满，
     * 然后将元素放入 top+1 的位置，
     * 最后将 top 的值加 1。
     *
     * @param element
     */
    public void push(int element) {
        if (top == stack.length - 1) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = element;
    }

    /**
     * pop() 方法用于出栈，
     * 它首先检查栈是否为空，
     * 然后返回 top 位置的元素，
     * 最后将 top 的值减 1。
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top--];
    }

    /**
     * peek() 方法用于获取栈顶元素，
     * 它首先检查栈是否为空，然后返回 top 位置的元素。
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    /**
     * isEmpty() 方法用于判断栈是否为空，
     * 它通过判断 top 的值是否为 -1 来确定
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        ImplementStackUseArray stack = new ImplementStackUseArray(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        // 输出 Top element: 30
        System.out.println("Top element: " + stack.peek());

        // 输出 Popped element: 30
        System.out.println("Popped element: " + stack.pop());

        // 输出 Top element after pop: 20
        System.out.println("Top element after pop: " + stack.peek());

        // 输出 Is stack empty? false
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
