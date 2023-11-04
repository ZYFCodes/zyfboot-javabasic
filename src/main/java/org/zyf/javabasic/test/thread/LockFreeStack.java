package org.zyf.javabasic.test.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: zyfboot-javabasic
 * @description: lock-free stack的一种可能实现方式
 * @author: zhangyanfeng
 * @create: 2023-10-21 22:52
 **/
public class LockFreeStack<T> {
    private AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        while (true) {
            Node<T> currentHead = head.get();
            newNode.next = currentHead;
            if (head.compareAndSet(currentHead, newNode)) {
                return;
            }
        }
    }

    public T pop() {
        while (true) {
            Node<T> currentHead = head.get();
            if (currentHead == null) {
                return null; // 栈为空
            }
            Node<T> newHead = currentHead.next;
            if (head.compareAndSet(currentHead, newHead)) {
                return currentHead.data;
            }
        }
    }

    public boolean isEmpty() {
        return head.get() == null;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
