package org.zyf.javabasic.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanfengzhang
 * @description
 * @date 2019/5/28  22:38
 */
public class ABATest {
    static class Stack {

        private AtomicReference<Node> top = new AtomicReference<>();

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

            do {
                oldTop = top.get();
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
            } while (!top.compareAndSet(oldTop, newTop));

            return oldTop;
        }

        public void push(Node node) {
            Node oldTop;
            do {
                oldTop = top.get();
                node.next = oldTop;
            } while (!top.compareAndSet(oldTop, node));
        }

        public AtomicReference<Node> getTop() {
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
                stack.pop(2);
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
        //
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5);

        Stack.Node top = stack.getTop().get();
        do {
            System.out.println(top.value);
            top = top.next;
        } while (top != null);

    }
}
