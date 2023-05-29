package org.zyf.javabasic.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yanfengzhang
 * @description
 * @date 2019/5/28  22:40
 */
public class DealABATest {
    static class Stack {

        // 使用带有版本号的引用
        private AtomicStampedReference<Node> top = new AtomicStampedReference<>(null, 0);

        static class Node {
            String value;
            Node next;

            public Node(String value) {
                this.value = value;
            }
        }

        //出栈
        public Node pop(int time) {
            // 这里的time用来模拟线程执行时间
            Node newTop;
            Node oldTop;
            int v;
            do {
                v = top.getStamp();
                oldTop = top.getReference();
                if (oldTop == null) {
                    return null;
                }
                newTop = oldTop.next;
                try {
                    //休眠一段时间，模拟ABA问题
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!top.compareAndSet(oldTop, newTop, v, v + 1));

            return oldTop;
        }

        public void push(Node node) {
            Node oldTop;
            int v;
            do {
                v = top.getStamp();
                oldTop = top.getReference();
                node.next = oldTop;
            } while (!top.compareAndSet(oldTop, node, v, v + 1));
        }

        public AtomicStampedReference<Node> getTop() {
            return top;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Stack stack = new Stack();
        Stack.Node a = new Stack.Node("A");
        Stack.Node b = new Stack.Node("B");
        // 初始化栈结构
        stack.push(b);
        stack.push(a);

        // ABA 测试
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stack.pop(3);
            }
        });
        Stack.Node c = new Stack.Node("C");
        Stack.Node d = new Stack.Node("D");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stack.pop(0);
                stack.pop(0);
                stack.push(d);
                stack.push(c);
                stack.push(a);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Stack.Node top = stack.getTop().getReference();
        do {
            System.out.println(top.value);
            top = top.next;
        } while (top != null);

    }
}
